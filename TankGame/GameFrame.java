/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import TankGame.Collision.Tanks;
import javax.swing.JFrame;

/**
 *
 * @author jack
 */
public class GameFrame {
    static final int width = 500;
    static final int length = 500;
    JFrame TankFrame = new JFrame("TankGame");
    public void Frame(){
        Tanks t = new Tanks();
        TankFrame.add(t);
        TankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        TankFrame.setSize(width, length);
        TankFrame.setVisible(true);
        
    }
}
