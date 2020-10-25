package com.liamnbtech.server.service.connection;

/**
 * Exception indicating that a connection could not be created.
 */
public class ConnectionCreationException extends Exception {

    /**
     * Creates a new RemoteConnectionCreationException.
     *
     * @param msg The exception message
     */
    public ConnectionCreationException(String msg) {
        super(msg);
    }

    /**
     * Creates a new RemoteConnectionCreationException.
     *
     * @param msg The exception message
     * @param cause The Throwable which caused this exception
     */
    public ConnectionCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
