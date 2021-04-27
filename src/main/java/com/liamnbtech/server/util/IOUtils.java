package com.liamnbtech.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class IOUtils {

    /**
     * Transfers all available data from the source stream to the dest stream in a buffered fashion, such that
     * no more than bufsize bytes of data is stored in memory at any given time.
     *
     * @param source The stream providing the data
     * @param dest The stream receiving the data.
     */
    public static void bufferedStreamTransfer(InputStream source, OutputStream dest, int bufSize) throws IOException {
        byte[] buf = new byte[bufSize];

        while(true) {
            int bytesRead = source.read(buf, 0, bufSize);

            if (bytesRead < 0) {
                return;
            } else {
                dest.write(buf, 0, bytesRead);
            }
        }
    }

    public static int readAllAvailableBytes(SocketChannel socketChannel, ByteBuffer byteBuffer) throws IOException {
        int totalBytesRead = 0;

        while(true) {
            int bytesRead = socketChannel.read(byteBuffer);
            totalBytesRead += bytesRead;

            if (bytesRead <= 0) {
                return totalBytesRead;
            }
        }
    }

    public static void writeAllBytesToChannel(SocketChannel socketChannel, ByteBuffer writeBuffer) throws IOException {
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        while(writeBuffer.hasRemaining()) {
            socketChannel.write(writeBuffer);
        }
        writeBuffer.clear();
    }
}
