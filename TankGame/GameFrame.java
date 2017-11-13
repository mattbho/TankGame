/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import TankGame.Collision.GameEvents;
import TankGame.Collision.Tanks;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author jack
 */
public class GameFrame extends JApplet implements Runnable{
    private static final int width = 640;
    private static final int length = 480;
    private Tanks P1, P2;
    Image tank1, tank2, wall, weapon, rocket, floor;
    Graphics2D g2;
    //int w= 500, h=500, move=0, speed=5;
    private BufferedImage bimg;
    private Thread thread;
    GameEvents gameEvents;
    
    JFrame TankFrame = new JFrame("TankGame");
    
    
    public void init(){
        setBackground(Color.BLACK);
        
        try{
            floor = ImageIO.read(new File("TankGame/Resource/Background.bmp"));
            tank1 = ImageIO.read(new File("TankGame/Resource/Tank1.gif"));
            tank2 = ImageIO.read(new File("TankGame/Resource/Tank2.gif"));
            wall = ImageIO.read(new File("TankGame/Resource/Wall1.gif"));
            weapon = ImageIO.read(new File("TankGame/Resource/Weapon.gif"));
            rocket = ImageIO.read(new File("TankGame/Resource/Rocket.gif"));
            
            P1 = new Tanks(tank1, 300, 360, 5);
            
            gameEvents = new GameEvents();
            gameEvents.addObserver(P1);
            //gameEvents.addObserver(P2);
            KeyControl key = new KeyControl();
            addKeyListener(key);
            
        }catch(IOException e){}
    }
   
    public class KeyControl extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            gameEvents.setValue(e);
        }
        
        public void keyReleased(KeyEvent e){
            gameEvents.setValue(e);
        }
    }
    
    public void drawBackGroundWithTileImage() {
        int TileWidth = floor.getWidth(this);
        int TileHeight = floor.getHeight(this);

        int NumberX = (int) (width / TileWidth);
        int NumberY = (int) (length / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(floor, j * TileWidth, i * TileHeight, TileWidth, TileHeight, this);
            }
        }
    }
    
    public void drawDemo(){
        drawBackGroundWithTileImage();
        
        P1.draw(this, g2);
    }
    
    public void paint(Graphics g) {
        if(bimg == null) {
            Dimension windowSize = getSize();
            bimg = (BufferedImage) createImage(windowSize.width, windowSize.height);
            g2 = bimg.createGraphics();
        }
        drawDemo();
        g.drawImage(bimg, 0, 0, this);
    }

    
    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }
    
    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();  
          try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
            
        }
    }
    
    public int getWidth(){
        return width;
    }
    public int getLength(){
        return length;
    }
    
}
