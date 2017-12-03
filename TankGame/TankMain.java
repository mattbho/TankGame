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
    private static final GameFrame frame = new GameFrame();
    public static void main(String arg[]){

        frame.init();

        JFrame f = new JFrame("TankGame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", frame);
        f.pack();
        f.setSize(new Dimension(960, 800));
        f.setVisible(true);
        f.setResizable(false);
        
        frame.start();
    }

    public static GameFrame getFrame() {
        return frame;
    }
}
