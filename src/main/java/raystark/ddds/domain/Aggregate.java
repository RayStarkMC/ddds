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
package raystark.ddds.domain;

/**
 * 戦術的設計の構成要素である集約(ルート)の基底インターフェースです。
 *
 * <p>集約はトランザクション整合性の境界となります。リポジトリは集約単位での永続化を抽象化します。
 * uowパッケージで定義されるUnit of Work APIでは各トランザクションで1つの集約のみの更新を許可します。
 *
 * @param <ID> 識別子の型
 */
public interface Aggregate<ID> extends Entity<ID> {}
