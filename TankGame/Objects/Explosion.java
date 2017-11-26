package TankGame.Objects;


import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Observable;

public class Explosion extends GameObject{
    private Image[] img;
    private int count;
    private int delay;
    private boolean finished;
    public Explosion(Image[] img, int x, int y){
        super(img, x, y, 0);
        this.img = img;
        count = 0;
        delay = 3;
        finished = false;
    }
    public void draw(ImageObserver obs, Graphics2D g2){
        if(delay < img.length){
            g2.drawImage(this.img[delay % this.img.length], x, y, obs);
            if(((count++)%10) == 0){
                delay++;
            }
        } else{
            finished = true;
        }


    }
    public void update(Observable obs, Object arg){

    }
    public boolean getFinished(){
        return finished;
    }
}
