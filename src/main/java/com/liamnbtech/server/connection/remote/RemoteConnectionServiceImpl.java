package com.liamnbtech.server.connection.remote;

import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * RemoteConnectionService implementation allowing the creation of secured and unsecured connections to remote servers.
 */
@Service
public class RemoteConnectionServiceImpl implements RemoteConnectionService {

    private final SSLSocketFactory factory;

    private static final int SOCKET_CONNECT_TIMEOUT = 3000;
    private static final int SOCKET_CONNECT_RETRIES = 5;

    @Override
    public Socket createConnection(Socket unconnectedSocket, SocketAddress endpoint)
            throws RemoteConnectionCreationException {

        Exception connectionError = null;

        for (int retry = 0; retry < SOCKET_CONNECT_RETRIES; retry++) {
            try {
                unconnectedSocket.connect(endpoint, SOCKET_CONNECT_TIMEOUT);

                return unconnectedSocket;
            } catch (IOException e) {
                connectionError = e;
            }
        }

        throw new RemoteConnectionCreationException(
                "Could not create connection to remote host.  Address " + endpoint,
                connectionError);
    }

    @Override
    public Socket createSSLConnection(Socket connectedSocket) throws RemoteConnectionCreationException {

        Exception socketCreationError = null;

        for (int retry = 0; retry < SOCKET_CONNECT_RETRIES; retry++) {
            try {
                return factory.createSocket(
                        connectedSocket,
                        connectedSocket.getInetAddress().getHostName(),
                        connectedSocket.getPort(),
                        true);
            } catch (IOException e) {
                socketCreationError = e;
            }
        }

        throw new RemoteConnectionCreationException(
                "Could not create SSL connection to remote host.  Address " + connectedSocket.getInetAddress(),
                socketCreationError);
    }

    public RemoteConnectionServiceImpl(SSLSocketFactory factory) {
        this.factory = factory;
    }
}
