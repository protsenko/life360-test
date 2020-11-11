package test.life360;

import test.life360.domain.Event;
import test.life360.domain.EventBuilder;
import test.life360.logger.BinaryLogger;
import test.life360.logger.impl.BinaryLoggerImpl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Main {

    private static final String FILE_NAME = "events.bin";
    private static final long MAX_FILE_SIZE = 10_000L;

    public static void main(String[] args) throws IOException {
        BinaryLogger<Event> logger = new BinaryLoggerImpl(FILE_NAME, MAX_FILE_SIZE);

        for (Event event : EventBuilder.buildEvents(100)) {
            logger.write(event);
        }

        readFile(logger, FILE_NAME + ".1");
        readFile(logger, FILE_NAME + ".2");
    }

    private static void readFile(BinaryLogger<Event> logger, String fileName) throws IOException {
        Iterator<Event> iterator = logger.read(new File(fileName), Event.class);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("");
    }
}
