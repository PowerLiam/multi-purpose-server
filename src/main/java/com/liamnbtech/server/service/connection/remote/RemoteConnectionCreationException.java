package com.liamnbtech.server.service.connection.remote;

import com.liamnbtech.server.service.connection.ConnectionCreationException;

/**
 * Exception indicating that a remote connection could not be created.
 */
public class RemoteConnectionCreationException extends ConnectionCreationException {
    public RemoteConnectionCreationException(String msg) {
        super(msg);
    }

    public RemoteConnectionCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
