package raystark.ddds.event;

/**
 * イベント出版用のインターフェース。
 *
 * @implSpec 実装クラスは自身へのSubscriberの登録方法を定義する必要があります。
 * @see Subscriber
 */
public interface Publisher {

    /**
     * イベントを購読するサブスクライバに引き渡します。
     *
     * <p>このメソッドは副作用を生成します。
     *
     * @implSpec 実装クラスはイベント処理がどのスレッドで行われるのか定義する必要があります。
     * @param event イベント
     */
    void publish(Event event);
}
