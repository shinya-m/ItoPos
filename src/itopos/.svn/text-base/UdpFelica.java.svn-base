/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itopos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//import sound.Sound;

/**
 *
 * @author yasu
 */
public class UdpFelica implements Colleague, Runnable {

    private Mediator mediator;
    DatagramSocket socket;
    DatagramPacket packet;
    byte buffer[];
    private String mID;
    
    public String getMID(){
        return mID;
    }
    public void setMID(String id){
        this.mID = id;
    }
    
    public String getFelicaIdm(){
        return "01100410ef08e907";
        //return "01100310d1088806";
    }
    
    public boolean receive() throws IOException {
        socket.receive(packet);
        //TODO
        this.mediator.collegueChanged("GETPACKET");

        return true;
    }

    public byte[] getMessage() {
        return buffer;
    }

    public UdpFelica(int port) {
        try {
            socket = new DatagramSocket(port);
            buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
        } catch (IOException ex) {
            throw new UnsupportedOperationException("Not supported yet.");

        }
        this.mediator = null;
    }

    public void run() {
        while (true) {
            try {
                socket.receive(packet);
                ConnectionHandler ch = new ConnectionHandler(socket, packet, this);
                //Sound sound = new Sound();
                //sound.PlayWave("auth.wav");
                if(this.mediator != null){
                    ch.setMediator(this.mediator);
                }else{
                    throw new IOException("mediator exception.");
                }
                ch.run();
            } catch (IOException ex) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setCollegueEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
