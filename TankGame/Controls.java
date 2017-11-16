package TankGame;



import TankGame.GameEvents;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */
public class Controls extends KeyAdapter{
    GameEvents gameEvents;

    public Controls(GameEvents ge){
        this.gameEvents = ge;

    }

    public void keyReleased(KeyEvent e) {
        gameEvents.setValue(e);
    }
    public void keyPressed(KeyEvent e) {
        gameEvents.setValue(e);
    }

}
