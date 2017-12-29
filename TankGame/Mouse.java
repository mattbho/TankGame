/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import TankGame.GameFrame.State;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Mouse implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        int x =e.getX();
        int y = e.getY();
        
        if(x>= TankMain.getX()/2-100 && x <= TankMain.getX()/2+100)
            if(y >= 450 && y <=500)
                GameFrame.state = State.Game;
                    
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
