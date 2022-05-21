package raystark.ddds.event;

import java.time.Instant;

/**
 * イベントの基底型。
 */
public interface Event {
    /**
     * このイベントが発生した時点のInstant
     * @return イベント発生時点
     */
    Instant occurredOn();
}
