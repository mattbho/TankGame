package TankGame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */
public class TankMain {
    public static void main(String arg[]){
        GameFrame frame = new GameFrame();
        frame.init();

        JFrame f = new JFrame("TankGame");
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", frame);
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        frame.start();
    }
}
