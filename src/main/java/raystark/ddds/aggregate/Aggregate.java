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
package raystark.ddds.aggregate;

/**
 * 戦術的設計の構成要素である集約(ルート)の基底インターフェースです。
 *
 * <p>集約はトランザクション整合性の境界となります。リポジトリは集約単位での永続化を抽象化します。
 * uowパッケージで定義されるUnit of Work APIでは各トランザクションで1つの集約のみの更新を許可します。
 *
 * <p>集約は、自身が論理削除された事を示すwasDeleted属性を持ちます。論理削除とは、
 * データストアに対してその集約を将来のある時点で消去する事を許可する事を表します。
 * 実際にデータストアが論理削除された集約に関連するデータを消去する事を物理削除と呼びます。
 * 物理削除の実装がトランザクション整合性であるか結果整合性であるかはリポジトリやアプリケーションの実装に依存します。
 * 論理削除は明らかにユビキタス言語の一部ではなく、アプリケーションやインフラストラクチャに依存する概念です。
 * 集約に関連するユビキタス言語を洞察し、論理削除が妥当な場合はコマンドメソッドで集約の状態を更新してください。
 * 論理削除された集約はコマンドメソッドの呼び出しを許可するべきではありません。
 *
 * <p>集約がコマンドメソッドを持つ場合、CommandTargetの実装を検討してください。
 *
 * @param <ID> 識別子の型
 */
public interface Aggregate<ID> extends Entity<ID> {

    /**
     * この集約が論理削除された事を示します。
     *
     * @return この集約が論理削除された場合true
     */
    boolean wasDeleted();
}
