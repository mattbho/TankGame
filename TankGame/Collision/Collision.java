/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame.Collision;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author jack
 */
public abstract class Collision implements Observer {
    public abstract boolean collision(int left, int right, int top, int bottom);
    public abstract void update(Observable o, Object arg);  
    
}
