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

/**
 * TypeMapのキーとして使われる、型と参照の対応です。
 *
 * <p>インスタンスの等価性は参照等価性と同値です。同じ(静的)型のインスタンス同士であっても参照が異なれば異なるキーとして区別されます。
 *
 * @param <T> TypeMapに登録する型
 */
public final class TypeKey<T> {}
