package TankGame;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */
public class Controls extends Observable{
    private int x=0, y=0, speed = 5;
    
    
    
    
    public void update(Observable obj, Object arg){
        GameEvents ge = (GameEvents) arg;
            if(ge.type == 1) {
                KeyEvent e = (KeyEvent) ge.event;
                switch (e.getKeyCode()) {    
                    case KeyEvent.VK_LEFT:
                        x -= speed;
	        	break; 
                    case KeyEvent.VK_RIGHT:
                        x += speed;
	        	break;
                    case KeyEvent.VK_UP:
                        y -= speed;
	        	break; 
                    case KeyEvent.VK_DOWN:
                        y += speed;
	        	break;
                    default:
                  if(e.getKeyChar() == ' ') {
                        System.out.println("Fire");  
                  }
                }
            }
            else if(ge.type == 2) {
                String msg = (String)ge.event;
                if(msg.equals("Explosion")) {
                    System.out.println("Explosion! Reduce Health");
        
                }
            }
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
