package TankGame;

import TankGame.Objects.Tanks;

import java.awt.Rectangle;
import java.util.ArrayList;
public class CollisionDetector {
    GameEvents gameEvent1, gameEvent2;

    public CollisionDetector(GameEvents ge1, GameEvents ge2){
        this.gameEvent1 = ge1;
        this.gameEvent2 = ge2;
    }
    public void playerVsPlayer(Tanks tank1, Tanks tank2){

        Rectangle tankBox1 = new Rectangle(tank1.getX(), tank1.getY(),tank1.getWidth(),tank1.getHeight());
        Rectangle tankBox2 = new Rectangle(tank2.getX(), tank2.getY(),tank2.getWidth(),tank2.getHeight());
        if(tankBox1.intersects(tankBox2)){

        }
    }
}
