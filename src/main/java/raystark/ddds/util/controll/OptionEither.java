/*
 * Copyright 2022 RayStarkMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package raystark.ddds.util.controll;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Value;
import io.vavr.collection.Iterator;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static io.vavr.Function1.constant;
import static java.util.Objects.requireNonNull;

/**
 * OptionのEitherをラップしたオブジェクトです。
 *
 * @param <L> EitherのLeftの型
 * @param <R> Optionの値の型
 */
public final class OptionEither<L, R> implements Value<R> {
    private final Either<L, Option<R>> delegate;

    private OptionEither(Either<L, Option<R>> delegate) {
        this.delegate = delegate;
    }

    /**
     * Rightとして指定した値のOptionを保持したOptionEitherを生成します。
     *
     * @param right 保持する値
     * @return valueを保持したOptionEither
     * @param <L> EitherのLeftの型
     * @param <R> 保持する値の型
     */
    public static <L, R> OptionEither<L, R> pure(R right) {
        requireNonNull(right);
        return right(Option.some(right));
    }

    /**
     * RightとしてOption値を保持したOptionEitherを生成します。
     *
     * @param optRight Option値
     * @return Option値を保持したOptionEither
     * @param <L> EitherのLeftの型
     * @param <R> 保持する値の型
     */
    public static <L, R> OptionEither<L, R> right(Option<R> optRight) {
        requireNonNull(optRight);
        return wrap(Either.right(optRight));
    }

    /**
     * Leftとして指定した値を保持したOptionEitherを生成します。
     *
     * @param left Leftとして保持する値
     * @return leftを保持したOptionEither
     * @param <L> EitherのLeftの型
     * @param <R> 保持する値の型
     */
    public static <L, R> OptionEither<L, R> left(L left) {
        requireNonNull(left);
        return wrap(Either.left(left));
    }

    /**
     * OptionのEitherをラップしてOptionEitherを生成します。
     *
     * @param optionEither OptionのEither
     * @return OptionEither
     * @param <L> EitherのLeftの型
     * @param <R> Optionの値の型
     */
    public static <L, R> OptionEither<L, R> wrap(Either<L, Option<R>> optionEither) {
        requireNonNull(optionEither);
        return new OptionEither<>(optionEither);
    }

    /**
     * ラップされたOptionのEitherを返します。
     *
     * @return OptionのEither
     */
    public Either<L, Option<R>> unWrap() {
        return delegate;
    }

    /**
     * 値が存在する場合、mapper関数によりマップします。
     *
     * @param mapper マッピング関数
     * @return フラットマップされた値
     * @param <U> マッピング後の型
     */
    public <U> OptionEither<L, U> map(Function<? super R, ? extends U> mapper) {
        requireNonNull(mapper);

        return wrap(delegate.map(optA -> optA.map(mapper)));
    }

    /**
     * 値が存在する場合、mapper関数によりマップします。
     *
     * @param mapper マッピング関数
     * @return フラットマップされた値
     * @param <U> マッピング後の型
     */
    public <U> OptionEither<L, U> flatMap(Function<? super R, OptionEither<L, U>> mapper) {
        requireNonNull(mapper);

        return wrap(delegate
            .flatMap(optA -> optA
                .map(mapper).fold(
                    () -> Either.right(Option.none()),
                    OptionEither::unWrap
                )
            )
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionEither<L, R> peek(Consumer<? super R> action) {
        delegate.peek(optA -> optA.peek(action));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R get() {
        return delegate.get().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAsync() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return delegate.isEmpty() || delegate.get().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLazy() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSingleValued() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringPrefix() {
        return "OptionEither";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<R> iterator() {
        return optionEither(
            constant(Iterator.empty()),
            Iterator::empty,
            Iterator::of
        );
    }

    /**
     * 場合分けして適切な関数を適用します。
     *
     * @param leftMapper Leftの場合に適用される関数
     * @param ifNone Rightかつその値がNoneの時に呼び出されるサプライヤ
     * @param ifSome Rightかつその値がSomeの時に適用される関数
     * @return 関数が適用された戻り値
     * @param <U> 戻り値の型
     */
    public <U> U optionEither(
        Function1<? super L, ? extends U> leftMapper,
        Function0<? extends U> ifNone,
        Function1<? super R, ? extends U> ifSome
    ) {
        return delegate.fold(
            leftMapper,
            r -> r.fold(
                ifNone,
                ifSome
            )
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return (obj == this) || (obj instanceof OptionEither<?, ?> o && Objects.equals(delegate, o.delegate));
    }
}
