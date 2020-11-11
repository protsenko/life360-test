package test.life360.reader;

import test.life360.loggable.BinaryLoggable;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public interface BinaryReader<T extends BinaryLoggable> {

    Iterator<T> read(File file, Class<T> clazz) throws IOException;
}
