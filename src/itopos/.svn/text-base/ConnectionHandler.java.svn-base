/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itopos;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ConnectionHandler implements Runnable, Colleague {

    DatagramSocket ds;
    DatagramPacket dp;
    private Mediator mediator;
    private UdpFelica Server;

    ConnectionHandler(DatagramSocket socket, DatagramPacket packet, UdpFelica udpf) {
        this.ds = socket;
        this.dp = packet;
        Server = udpf;
    }

    public void handlePacket(DatagramSocket ds, DatagramPacket dp) {
        byte[] buffer = dp.getData();
        int offset = dp.getOffset();
        int length = dp.getLength();
        int a = 0;
        try {
            byte[] buf = new byte[16];
            System.arraycopy(buffer, 0, buf, 0, buf.length);
            String mid = new String(buf, "US-ASCII");
            System.out.println(mid);
            Server.setMID(mid);
            this.mediator.collegueChanged("GETPACKET");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ConnectionHandler(DatagramSocket dsock, DatagramPacket dpacket) {
        this.ds = dsock;
        this.dp = dpacket;
    }

    public void run() {
        handlePacket(ds, dp);
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setCollegueEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
