package com.liamnbtech.server.connection.uds;

import jnr.unixsocket.UnixSocketChannel;

/**
 * Allows the creation of local IPC connections to other processes using unix domain sockets.
 */
public interface LocalConnectionService {

    UnixSocketChannel createConnection(String udsFilePath) throws LocalConnectionCreationException;

}
