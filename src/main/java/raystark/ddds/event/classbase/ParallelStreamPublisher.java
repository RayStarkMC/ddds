package raystark.ddds.event.classbase;


import io.vavr.API;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import raystark.ddds.event.Event;
import raystark.ddds.event.Subscriber;

import static java.util.Objects.requireNonNull;

/**
 * 並列ストリームによりイベント処理を行うClassBasePublisher実装。
 */
public final class ParallelStreamPublisher implements ClassBasePublisher {
    private final Map<Class<? extends Event>, List<Subscriber<?>>> subscribersMap;

    private ParallelStreamPublisher(Map<Class<? extends Event>, List<Subscriber<?>>> subscribersMap) {
        this.subscribersMap = subscribersMap;
    }

    /**
     * Publisherを生成します。
     *
     * <p>生成されたPublisherにはSubscriberが登録されていません。
     * @return 新規生成されたPublisher
     */
    public static ClassBasePublisher create() {
        return new ParallelStreamPublisher(API.Map());
    }

    /**
     * イベントを購読するサブスクライバに引き渡します。
     *
     * <p>どのSubscriberの処理もStreamライブラリが選択した任意のスレッドで実行される可能性があります。
     * またSubscriberが共有状態を更新する場合その可視性は保証されないため、同期の責任はSubscriberにあります。
     *
     * @param event イベント
     */
    @Override
    public void publish(Event event) {
        requireNonNull(event);

        subscribersMap
            .get(event.getClass())
            .getOrElse(List.empty())
            .toJavaParallelStream()
            .forEach(subscriber ->
                castUnsafe(subscriber).handle(this, event) //Classにより型が証明されているためキャストは安全
            );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends Event> ClassBasePublisher subscribe(Class<E> clazz, Subscriber<E> subscriber) {
        requireNonNull(clazz);
        requireNonNull(subscriber);

        var newList = subscribersMap
            .getOrElse(clazz, List.empty())
            .append(subscriber);

        return new ParallelStreamPublisher(
            subscribersMap.put(
                clazz,
                newList
            )
        );
    }

    @SuppressWarnings("unchecked")
    private <E extends Event> Subscriber<E> castUnsafe(Subscriber<?> subscriber) {
        return (Subscriber<E>)subscriber;
    }
}
