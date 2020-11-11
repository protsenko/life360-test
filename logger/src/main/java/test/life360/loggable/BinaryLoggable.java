package test.life360.loggable;

import java.io.IOException;

public interface BinaryLoggable {

    byte[] toBytes() throws IOException;

    void fromBytes(byte[] rawBytes) throws IOException;
}
