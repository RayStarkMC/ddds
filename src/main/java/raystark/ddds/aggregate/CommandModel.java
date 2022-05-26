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

import io.vavr.collection.Queue;

/**
 * コマンドを持つ集約のインターフェースです。
 *
 * <p>コマンドモデルは集約に対するコマンドを定義します。コマンドとは集約を生成、又はその状態を更新するメソッドです。
 * コマンドモデルはイベントキューとバージョンを持っています。コマンド実行が成功した場合、
 * 発生したコマンドイベントがイベントキューにキューイングされ、バージョンが上がります。
 *
 * <p>コマンドモデルは自身が論理削除された事を示すwasDeleted属性を持ちます。論理削除とは、
 * データストアに対してその集約を将来のある時点で消去する事を許可する事を表します。
 * 実際にデータストアが論理削除された集約に関連するデータを消去する事を物理削除と呼びます。
 * 物理削除がトランザクション整合性として実装されるか、結果整合性として実装されるかはリポジトリやアプリケーションの実装に依存します。
 * 論理削除は明らかにユビキタス言語の一部ではなく、アプリケーションやインフラストラクチャに依存する概念です。
 * 集約に関連するユビキタス言語を洞察し、論理削除が妥当な場合はコマンドメソッドで集約の状態を更新してください。
 * 論理削除された集約はコマンドメソッドの呼び出しを許可するべきではありません。
 *
 * <p>コマンドメソッドは任意の引数をとり、更新後の集約を返します。
 * コマンドメソッドの処理は失敗する可能性があります。失敗の通知方法は実装依存です。
 * 例として例外をスローしたり、失敗系モナド、Validationを返す実装が考えられます。
 * コマンドモデルが新規生成、又はリポジトリから再構築された時点ではoccurredEventsは空のキューを返します。
 * コマンドメソッドの戻り値のインスタンスに対するoccurredEvents呼び出しの結果得られるキューは、
 * それまでのコマンドメソッド呼び出しで発生したイベントが発生順にキューイングされています。
 *
 * @param <A_ID> 集約の識別子の型
 * @param <E_ID> コマンドイベントの識別子の型
 * @param <E> コマンドイベントの型
 */
public interface CommandModel<A_ID, E_ID, E extends CommandEvent<E_ID, A_ID>> extends Aggregate<A_ID> {

    /**
     * この集約で発生したコマンドイベントのキューを返します。
     *
     * <p>この集約が新規生成、又はリポジトリから再構築されてから最後に呼び出されたコマンド操作までの間発生したイベントを保持します。
     *
     * @return コマンド操作で発生したイベント
     */
    Queue<E> occurredEvents();

    /**
     * この集約のバージョンを返します。
     *
     * <p>新規生成直後のバージョンは0、その後コマンドの呼び出し毎に1増加します。
     *
     * @return この集約のバージョン
     */
    long version();

    /**
     * この集約が論理削除された事を示します。
     *
     * @return この集約が論理削除された場合true
     */
    boolean wasDeleted();
}