package test.life360.logger;

import test.life360.loggable.BinaryLoggable;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public interface BinaryLogger<T extends BinaryLoggable> {

    void write(T loggable) throws IOException;

    Iterator<T> read(File file, Class<T> clazz) throws IOException;
}
