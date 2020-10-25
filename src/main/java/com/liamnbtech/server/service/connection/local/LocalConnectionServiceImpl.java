package com.liamnbtech.server.service.connection.local;

import com.liamnbtech.server.service.file.LocalFileService;
import jnr.unixsocket.SeqPacketSocketChannel;
import jnr.unixsocket.UnixSocketAddress;
import jnr.unixsocket.UnixSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class LocalConnectionServiceImpl implements LocalConnectionService{

    private static final int SOCKET_CONNECT_RETRIES = 5;

    private final Logger LOG = LoggerFactory.getLogger(LocalConnectionServiceImpl.class);
    private final LocalFileService localFileService;

    @Override
    public UnixSocketChannel createConnection(String udsFilePath) throws LocalConnectionCreationException {
        Exception connectionError = null;
        File udsFile;
        UnixSocketAddress socketAddress;
        UnixSocketChannel socketChannel;

        for (int retry = 0; retry < SOCKET_CONNECT_RETRIES; retry++) {
            try {
                udsFile = localFileService.getFile(udsFilePath);
                socketAddress = new UnixSocketAddress(udsFile);
                socketChannel = new SeqPacketSocketChannel();
                socketChannel.connect(socketAddress);

                LOG.info("Established connection to " + socketChannel.getRemoteSocketAddress().humanReadablePath());

                return socketChannel;
            } catch (IOException e) {
                connectionError = e;
            }
        }

        throw new LocalConnectionCreationException(
                "Could not create connection to local process via UDS.  Address " + udsFilePath,
                connectionError);

    }

    public LocalConnectionServiceImpl(LocalFileService localFileService) {
        this.localFileService = localFileService;
    }
}
