package test.life360.appender.impl;

import test.life360.appender.BinaryAppender;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class FileSizeBinaryAppender implements BinaryAppender {

    private static final int INT_SIZE = 4;

    private final AtomicInteger partNumber = new AtomicInteger(0);
    private final String dataFileName;
    private final long dataFileMaxSize;

    private FileOutputStream fos;
    private DataOutputStream dos;

    public FileSizeBinaryAppender(String dataFileName, long dataFileMaxSize) throws FileNotFoundException {
        this.dataFileName = dataFileName;
        this.dataFileMaxSize = dataFileMaxSize;
        File file = new File(builderFileName());
        initInternalStructs(file);
    }

    @Override
    public void append(byte[] data) throws IOException {
        int dataLength = data.length;
        if (!isEnoughFileSize(dataLength)) {
            recreateStreams();
        }
        dos.writeInt(dataLength);
        dos.write(data);
        dos.flush();
    }

    boolean isEnoughFileSize(int dataLength) {
        return this.dos.size() + INT_SIZE + dataLength <= this.dataFileMaxSize;
    }

    private void recreateStreams() throws IOException {
        this.fos.close();
        this.dos.close();

        File newFile = new File(builderFileName());
        initInternalStructs(newFile);
    }

    private void initInternalStructs(File file) throws FileNotFoundException {
        this.fos = new FileOutputStream(file);
        this.dos = new DataOutputStream(this.fos);
    }

    private String builderFileName() {
        return String.format("%s.%s", this.dataFileName, partNumber.incrementAndGet());
    }
}
