/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package count;

import itopos.Mediator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author yasuhiro-i
 */
public class countDown extends javax.swing.JLabel implements ActionListener, itopos.Colleague {

    private Timer timer;
    private int count;
    private Mediator mediator;

    public countDown() {
        count = 0;
        this.setText(Integer.toString(count));
    }

    public void setCount(int num) {
        this.count = num;
        if(timer == null){
            timer = new Timer(1000, this);
            timer.start();
        }else{
            timer.restart();
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (count >= 0) {
            this.setText(Integer.toString(count));
            count--;
        } else {
            mediator.collegueChanged("TIME_OUT");
            if(timer != null){
                timer.stop();
            }
            timer = null;
        }
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void setCollegueEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
