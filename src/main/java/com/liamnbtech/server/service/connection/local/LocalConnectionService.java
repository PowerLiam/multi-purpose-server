package com.liamnbtech.server.service.connection.local;

import jnr.unixsocket.UnixSocketChannel;

/**
 * Allows the creation of local IPC connections to other processes using unix domain sockets.
 */
public interface LocalConnectionService {

    UnixSocketChannel createConnection(String udsFilePath) throws LocalConnectionCreationException;

}
