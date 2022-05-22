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
 * イベントシステムのサブスクライバ。
 *
 * <p>実装は状態を持つべきではありません。
 * @param <E> 購読するイベントの型
 */
public interface Subscriber<E extends Event> {

    /**
     * 購読したイベントを処理します。
     *
     * <p>このメソッドは副作用を生成します。
     *
     * @param publishedBy このイベントを出版したパブリッシャ
     * @param event イベント
     */
    void handle(Publisher publishedBy, E event);
}
