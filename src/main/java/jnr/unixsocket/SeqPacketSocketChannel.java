package jnr.unixsocket;

import java.io.IOException;
import jnr.constants.platform.ProtocolFamily;
import jnr.constants.platform.Sock;


public class SeqPacketSocketChannel extends UnixSocketChannel {

    public SeqPacketSocketChannel() throws IOException {
        super(Native.socket(ProtocolFamily.PF_UNIX, Sock.SOCK_SEQPACKET, 0));
    }
}
