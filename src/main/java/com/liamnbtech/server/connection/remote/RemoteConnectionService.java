package com.liamnbtech.server.connection.remote;

import java.net.Socket;
import java.net.SocketAddress;

/**
 * Interface for a service allowing the creation of secured and unsecured connections to remote servers.
 */
public interface RemoteConnectionService {

    /**
     * Establishes and returns a connection to the remote entity specified by the provided address.
     *
     * @param endpoint Address of the entity to which a connection should be established
     * @param unconnectedSocket The unconnected socket to connect to the specified address
     *
     * @return Socket representing the connection to the remote entity
     *
     * @throws RemoteConnectionCreationException If the connection could not be established for any reason
     */
    Socket createConnection(Socket unconnectedSocket, SocketAddress endpoint) throws RemoteConnectionCreationException;

    /**
     * Establishes and returns a secure connection to the remote host connected to the given socket.
     *
     * @param connectedSocket The insecurely connected socket
     *
     * @return Socket representing the secure connection to the remote host connected to the given socket
     *
     * @throws RemoteConnectionCreationException If the secure connection could not be established for any reason
     */
    Socket createSSLConnection(Socket connectedSocket) throws  RemoteConnectionCreationException;
}
