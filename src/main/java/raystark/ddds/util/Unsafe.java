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

package raystark.ddds.util;

/**
 * 型安全でないキャスト関数を定義するユーティリティクラスです。
 *
 * <p>キャスト関数は型安全性が証明されたキャストに対する簡潔な記述として利用されます。
 * キャスト関数はジェネリックメソッドとして定義されているため、型推論を利用してキャスト後の型の表記を省略することが出来ます。
 */
public final class Unsafe {
    private Unsafe() {}

    /**
     * 型安全ではないキャストを実行します。
     *
     * <p>このメソッドを呼び出す場合は型安全性を証明してください
     *
     * @param t 引数
     * @return 引数と同じ参照
     * @param <T> 引数の型
     * @param <R> 戻り値の型
     */
    @SuppressWarnings("unchecked")
    public static <T, R> R unsafeCast(T t) {
        return (R)t;
    }
}
