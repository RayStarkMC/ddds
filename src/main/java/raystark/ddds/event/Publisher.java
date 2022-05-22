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
package raystark.ddds.event;

/**
 * イベント出版用のインターフェース。
 *
 * <p>publishメソッドに渡されたイベントは動的に型検査され、適切なサブスクライバに引き渡されます。
 *
 * <p>実装クラスは不変である必要があります。
 * 自身へのSubscriberの登録方法を定義する必要があります。
 * スレッド安全性を適切にドキュメント化する必要があります。
 * @see Subscriber
 */
public interface Publisher {

    /**
     * イベントを購読するサブスクライバに引き渡します。
     *
     * <p>このメソッドは配下のSubscriberのhandleメソッドを一度呼び出す事で副作用を生成します。
     * 一度のメソッド呼び出しで複数のhandleメソッドが呼び出される可能性がありますが、
     *
     * <p>実装クラスはイベント処理がどのスレッドで行われるのか定義する必要があります。
     * @param event イベント
     */
    void publish(Event event);
}
