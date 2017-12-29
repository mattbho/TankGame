package TankGame;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class TankMain {
    private static final GameFrame frame = new GameFrame();
    private static final int x = 960;
    private static final int y = 800;
    public static void main(String arg[]){
        frame.init();

        JFrame f = new JFrame("TankGame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", frame);
        f.pack();
        f.setSize(new Dimension(x, y));
        f.setVisible(true);
        f.setResizable(false);
        
        frame.start();
    }

    public static GameFrame getFrame() {
        return frame;
    }
    public static int getX(){
        return x;
    }
    public static int getY(){
        return y;
    }
}
