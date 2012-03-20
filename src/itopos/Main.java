package itopos;

import java.awt.Frame;

/**
 *
 * @author yasuhiro-i,shinya-m
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ItoPosFrame ips = new ItoPosFrame();
        ips.setExtendedState(Frame.MAXIMIZED_BOTH);
        ips.setVisible(true);
    }

}
