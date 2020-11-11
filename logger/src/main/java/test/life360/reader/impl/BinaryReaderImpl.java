package test.life360.reader.impl;

import test.life360.loggable.BinaryLoggable;
import test.life360.reader.BinaryReader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryReaderImpl<T extends BinaryLoggable> implements BinaryReader<T> {

    private static final int INT_SIZE = 4;

    @Override
    public Iterator<T> read(File file, Class<T> clazz) throws IOException {
        List<T> result = new ArrayList<>();
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long actualSize = randomAccessFile.length();
            int i = 0;
            while (actualSize != 0) {
                int dataSize = randomAccessFile.readInt();
                actualSize = actualSize - 4;
                byte[] bytes = new byte[dataSize];
                randomAccessFile.read(bytes, 0, bytes.length);
                actualSize = actualSize - bytes.length;
                try {
                    T t = clazz.getConstructor().newInstance();
                    t.fromBytes(bytes);
                    result.add(t);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
                randomAccessFile.seek((i + 1) * dataSize + INT_SIZE * (i + 1));
                i++;
            }
        }
        return result.iterator();
    }
}
