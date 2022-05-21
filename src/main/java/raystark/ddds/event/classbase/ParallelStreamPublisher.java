package raystark.ddds.event.classbase;


import io.vavr.API;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import raystark.ddds.event.Event;
import raystark.ddds.event.Subscriber;

import static java.util.Objects.requireNonNull;

public final class ParallelStreamPublisher implements ClassBasePublisher {
    private final Map<Class<? extends Event>, List<Subscriber<?>>> subscribersMap;

    private ParallelStreamPublisher(Map<Class<? extends Event>, List<Subscriber<?>>> subscribersMap) {
        requireNonNull(subscribersMap);

        this.subscribersMap = subscribersMap;
    }

    public static ClassBasePublisher create() {
        return new ParallelStreamPublisher(API.Map());
    }

    @Override
    public void publish(Event event) {
        subscribersMap
            .get(event.getClass())
            .getOrElse(List.empty())
            .toJavaParallelStream()
            .forEach(subscriber ->
                castUnsafe(subscriber).handle(this, event) //Classにより型が証明されているためキャストは安全
            );
    }

    @Override
    public <E extends Event> ClassBasePublisher subscribe(Class<E> clazz, Subscriber<E> subscriber) {
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
