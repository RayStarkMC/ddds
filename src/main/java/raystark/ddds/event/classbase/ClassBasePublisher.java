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
