package test.life360.logger.impl;

import test.life360.appender.BinaryAppender;
import test.life360.appender.impl.FileSizeBinaryAppender;
import test.life360.loggable.BinaryLoggable;
import test.life360.logger.BinaryLogger;
import test.life360.reader.BinaryReader;
import test.life360.reader.impl.BinaryReaderImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BinaryLoggerImpl<T extends BinaryLoggable> implements BinaryLogger<T> {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final BinaryAppender appender;
    private final BinaryReader<T> reader;

    public BinaryLoggerImpl(String fileName, long maxFileSize) throws FileNotFoundException {
        this.appender = new FileSizeBinaryAppender(fileName, maxFileSize);
        this.reader = new BinaryReaderImpl<>();
    }

    @Override
    public void write(T loggable) throws IOException {
        // some filters or processors can be here
        ReentrantReadWriteLock.WriteLock writeLock = this.lock.writeLock();
        writeLock.lock();
        try {
            appender.append(loggable.toBytes());
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Iterator<T> read(File file, Class<T> clazz) throws IOException {
        ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
        readLock.lock();
        try {
            return reader.read(file, clazz);
        } finally {
            readLock.unlock();
        }
    }
}
