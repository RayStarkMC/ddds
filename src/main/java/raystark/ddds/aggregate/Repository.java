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

import io.vavr.control.Option;

/**
 * 戦術的設計の構成要素であるリポジトリの基底インターフェースです。
 *
 * <p>リポジトリは集約の永続化を担当します。ID検索と集約の永続化メソッドを持ちます。
 * リポジトリに対してトランザクションを管理するのはアプリケーションレイヤの責務です。リポジトリは単に永続化のインターフェースを定義します。
 * 単一のトランザクションではsaveメソッド呼び出しが高々一度まで許可されます。
 * 2度以上の呼び出しは「1つのトランザクションで1つの集約を更新する」規則に違反します。
 *
 * <p>リポジトリのサブタイプは追加でクエリメソッドを持つことができますが、
 * CQRSを採用する場合クエリメソッドはクエリサービスの責務となり、リポジトリはコマンドモデルのI/Oのみを担当します。
 *
 * @param <A_ID> 集約の識別子の型
 * @param <A> 集約の型
 */
public interface Repository<A_ID, A extends Aggregate<A_ID>> {

    /**
     * 指定したIDの集約を取得します。
     *
     * <p>集約が存在しない場合空のOptionを返します。
     *
     * @param id ID値
     * @return 集約が見つかった場合その集約、無ければ空のOption
     */
    Option<A> findByID(A_ID id);

    /**
     * 集約を映像化します。
     *
     * <p>このリポジトリに指定した集約が存在しない場合、その集約を新規に永続化します。
     * このリポジトリに指定した集約が存在し、かつ指定した集約が論理削除されていない場合、集約の更新を永続化します。
     * このリポジトリに指定した集約が存在し、かつ指定した集約が論理削除されている場合、
     * 集約の物理削除をトランザクション整合性とするか結果整合性とするかは実装が選択できます。
     *
     * <p>実装は物理削除がトランザクション整合性か結果整合性かどちらを持つかを明示する必要があります。
     *
     * @param aggregate 永続化する集約
     */
    void save(A aggregate);
}
