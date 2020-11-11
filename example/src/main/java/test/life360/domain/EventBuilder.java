package test.life360.domain;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EventBuilder {

    private EventBuilder() {
    }

    public static List<Event> buildEvents(int count) {
        return IntStream.range(0, count)
                .mapToObj(EventBuilder::buildEvent)
                .collect(Collectors.toList());
    }

    private static Event buildEvent(int id) {
        Event event = new Event();
        event.setEventId(id);
        event.setEventTime(System.currentTimeMillis());
        event.setEventUUID(UUID.randomUUID().toString());
        return event;
    }
}
