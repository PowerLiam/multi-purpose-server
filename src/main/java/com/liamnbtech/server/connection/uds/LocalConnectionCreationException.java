package com.liamnbtech.server.connection.uds;

import com.liamnbtech.server.connection.ConnectionCreationException;

/**
 * Exception indicating that a local IPC connection could not be created.
 */
public class LocalConnectionCreationException extends ConnectionCreationException {
    public LocalConnectionCreationException(String msg) {
        super(msg);
    }

    public LocalConnectionCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
