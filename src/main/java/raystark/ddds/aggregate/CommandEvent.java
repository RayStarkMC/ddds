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

import raystark.ddds.event.Event;

/**
 * コマンドにより発生するイベントの基底インターフェースです。
 *
 * <p>コマンドイベントはエンティティとして設計されていますが、イベントを永続化するかはアプリケーションの自由です。
 * コマンドイベントは集約のコマンド操作によってのみ発生します。コマンドイベントには連番が振られており、
 * コマンドの永続化を行う場合集約の状態を過去のコマンドイベントから再生出来ます。イベントの発送についてはCommandTargetを参照してください。
 *
 * @param <E_ID> イベントの識別子の型
 * @param <A_ID> 集約の識別子の型
 * @see CommandModel
 */
public interface CommandEvent<E_ID, A_ID> extends Event, Entity<E_ID> {

    /**
     * イベントの識別子を返します。
     *
     * <p>識別子はコマンドイベントの発生順に連番である必要があります。
     *
     * @return イベントの識別子
     */
    @Override
    E_ID id();

    /**
     * このコマンドイベントが発生した集約の識別子を返します。
     *
     * @return このイベントの発生した集約の識別子
     */
    A_ID aggregateID();

    /**
     * イベントが発生した直後の集約のバージョンを返します。
     *
     * <p>集約の生成イベントの直後の集約のバージョンは0であり、その後コマンドメソッドの呼び出し毎に1ずつ上がります。
     *
     * @return イベント発生直後の集約のバージョン
     */
    long aggregateVersion();
}
