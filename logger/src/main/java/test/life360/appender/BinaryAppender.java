package test.life360.appender;

import java.io.IOException;

public interface BinaryAppender {

    void append(byte[] data) throws IOException;
}
