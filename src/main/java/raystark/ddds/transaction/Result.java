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

package raystark.ddds.transaction;

import raystark.ddds.aggregate.Aggregate;

import static java.util.Objects.requireNonNull;

/**
 * トランザクションの結果を表すデータ型です。
 *
 * <p>結果にはコミットとロールバックがあります。トランザクションがコミットで終わる場合は操作された集約の値を持ちます。
 *
 * @param <E> 失敗情報の型
 * @param <A> トランザクションで操作された集約の型
 */
public sealed interface Result<E, A extends Aggregate<?>> {

    /**
     * トランザクションが成功した事を表すデータ型です。
     *
     * @param result コミットされる集約
     * @param <E> 失敗情報の型
     * @param <A>コミットされる集約の型
     */
    record Commit<E, A extends Aggregate<?>>(A result) implements Result<E, A> {

        /**
         * 集約をコミットします。
         *
         * @param result コミットされる集約
         */
        public Commit {
            requireNonNull(result);
        }
    }

    /**
     * トランザクションが失敗した事を表すデータ型です。
     *
     * @param failReport 失敗情報
     * @param <E> 失敗情報の型
     * @param <A>トランザクションで操作された集約の型
     */
    record Rollback<E, A extends Aggregate<?>>(E failReport) implements Result<E, A> {

        /**
         * トランザクションをロールバックします。
         *
         * @param failReport 失敗情報
         */
        public Rollback {
            requireNonNull(failReport);
        }
    }
}
