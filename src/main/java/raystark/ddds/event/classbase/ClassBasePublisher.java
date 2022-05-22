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
package raystark.ddds.event.classbase;

import raystark.ddds.event.Event;
import raystark.ddds.event.Publisher;
import raystark.ddds.event.Subscriber;

/**
 * イベントのクラスオブジェクトに対してSubscriberを対応させるPublisher。
 *
 * <p>Eventのクラスオブジェクト1種につき複数のSubscriberが登録することができます。
 */
public interface ClassBasePublisher extends Publisher {

    /**
     * 引数のSubscriberにイベントを購読させます。
     *
     * <p>このメソッドは状態を更新せず、新たなPublisherを生成します。
     * 生成されたPublisherのpublishメソッドに対して型EのEvent渡した場合、
     * このメソッドの引数で指定したSubscriberのhandleメソッドを呼び出します。
     *
     * @param clazz 購読するEventのクラスオブジェクト
     * @param subscriber イベントを購読するSubscriber
     * @return サブスクライバが登録されたPublisher
     * @param <E> Eventの型
     */
    <E extends Event> ClassBasePublisher subscribe(Class<E> clazz, Subscriber<E> subscriber);
}
