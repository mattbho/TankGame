/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Collision;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author jack
 */
public class Tanks extends JPanel implements KeyListener{
    int x=0, y=0, velY=0,velX=0;
    
    public Tanks() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void addTank(){
        JLabel tank = new JLabel("Resource/Tank1.gif");
        add(tank, BorderLayout.CENTER);
    }
    @Override
    public void keyTyped(KeyEvent e) {        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);
        switch (code) {
            case KeyEvent.VK_DOWN:
                velY = 5;
                velX = 0;
                break;
            case KeyEvent.VK_UP:
                velY = -5;
                velX = 0;
                break;
            case KeyEvent.VK_LEFT:
                velY = 0;
                velX = -5;
                break;
            case KeyEvent.VK_RIGHT:
                velY = 0;
                velX = 5;
                break;
        }
        this.x += this.velX;
        this.y += this.velY;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > HEIGHT - 50) {
            x = HEIGHT - 50;
        }
        if (y > WIDTH - 75) {
            y = WIDTH - 75;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        velY = 0;
        velX = 0;
    }
    
}
