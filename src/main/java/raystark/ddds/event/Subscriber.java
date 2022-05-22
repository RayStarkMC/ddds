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
