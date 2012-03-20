/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author yasuhiro-i
 */
public class ImagePanel extends javax.swing.JPanel implements ActionListener {

    private BufferedImage image;
    private int index;
    private String folder;
    Timer timer;
    ArrayList<String> graphicAbsPaths;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
        File f = new File(folder);
        String[] fpaths = f.list();
        for (String p : fpaths) {
            if (p.endsWith("jpg") || p.endsWith("JPG")) {
                graphicAbsPaths.add(folder + p);
            } else if (p.endsWith("png")||p.endsWith("PNG")) {
                graphicAbsPaths.add(folder + p);
            }

        }
        index = 0;
        if (fpaths.length > 0) {
            setImage(folder + fpaths[index]);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        if (graphicAbsPaths.size() < 0 || image == null) {
            super.paintComponent(g);
        } else {

            Graphics2D g2D = (Graphics2D) g;
            double imageWidth = image.getWidth();
            double imageHeight = image.getHeight();
            double panelWidth = this.getWidth();
            double panelHeight = this.getHeight();

            // 画像がコンポーネントの何倍の大きさか計算
            double sx = (panelWidth / imageWidth);
            double sy = (panelHeight / imageHeight);

            // スケーリング
            AffineTransform af = AffineTransform.getScaleInstance(sx, sy);
            //update(g);
            g2D.drawImage(image, af, this);
        }

    }

    public void setImage(String path) {
        try {
            //this.image = ImageIO.read(getClass().getResource(path);
            this.image = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(ImagePanel.class.getName()).log(Level.SEVERE, null, ex);
            this.image = null;
            path = "";
        }
    }

    public ImagePanel() {
        timer = new Timer(3000, this);
        timer.start();
        graphicAbsPaths = new ArrayList<String>();
    }

    public void actionPerformed(ActionEvent e) {
        if (index < graphicAbsPaths.size()) {
            index++;
        }
        if(index == graphicAbsPaths.size()) {
            index = 0;
        }
        setImage(graphicAbsPaths.get(index));
        repaint();
    }
}
