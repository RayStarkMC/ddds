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

package raystark.ddds.util.typemap;

import io.vavr.API;
import io.vavr.collection.Map;
import io.vavr.control.Option;

import static java.util.Objects.requireNonNull;
import static raystark.ddds.util.Unsafe.unsafeCast;

/**
 * 型安全な異種コンテナです。
 *
 * <p>TypeMapはあらゆる型に対するMapとして機能します。putメソッドによりgetメソッドの型安全性が証明されているため、
 * getメソッドは値をTypeKeyで指定された型に安全にキャストして取り出します。
 *
 * <p>TypeMapは不変です。そのため無条件にスレッドセーフです。
 */
public final class TypeMap {
    private final Map<TypeKey<?>, Object> mapping;

    /**
     * TypeMapを生成します。
     *
     * <p>生成されるTypeMapは空です。
     */
    public TypeMap() {
        this.mapping = API.Map();
    }

    private TypeMap(Map<TypeKey<?>, Object> mapping) {
        this.mapping = mapping;
    }

    /**
     * keyで指定された型とその値を対応付けます。
     *
     * <p>既に対応が存在した場合は上書きされます。
     *
     * <p>putとメソッドではkeyの型引数とvalueの値が一致する事を要求します。
     * そのため、keyの型引数とvalueの型が一致しない対応は存在しないことが証明されます。
     *
     * @param key 値の型を指定するキー
     * @param value 値
     * @return 更新後のTypeMap
     * @param <T> 値の型
     */
    public <T> TypeMap put(TypeKey<T> key, T value) {
        requireNonNull(key);
        requireNonNull(value);

        return new TypeMap(mapping.put(key, value));
    }

    /**
     * keyに対応付けられた値を取り出します。
     *
     * <p>取り出された値はkeyの型引数で指定された型にキャストされています。
     *
     * @param key 値の型を指定するキー
     * @return keyと対応付けされた型
     * @param <T> 値の型
     */
    public <T> Option<T> get(TypeKey<T> key) {
        requireNonNull(key);

        return unsafeCast(mapping.get(key));
        /*
         * 型安全性の証明
         *
         * mapping.putメソッドはputメソッドからしか呼ばれていない
         * putメソッドの引数ではkeyの型引数とvalueの型が一致する
         * よってmapping.getメソッドはkeyの型引数と一致する型の値を返す
         */
    }
}
