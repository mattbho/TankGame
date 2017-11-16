/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import TankGame.Objects.Tanks;
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


/**
 *
 * @author jack
 */
public class GameFrame extends JApplet implements Runnable{
    private static final int width = 960;
    private static final int length = 840;
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
        this.setFocusable(true);
        try{
            floor = ImageIO.read(new File("TankGame/Resource/Background.bmp"));
            tank1 = ImageIO.read(GameFrame.class.getResource("Resource/Tank_blue.png"));
            tank2 = ImageIO.read(GameFrame.class.getResource("Resource/Tank_red.png"));
            wall = ImageIO.read(new File("TankGame/Resource/Wall1.gif"));
            weapon = ImageIO.read(new File("TankGame/Resource/Weapon.gif"));
            rocket = ImageIO.read(new File("TankGame/Resource/Rocket.gif"));
            
            P1 = new Tanks(tank1, 0, 0, 5, 100, 3, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_C);
            P2 = new Tanks(tank2, 700, 500, 5, 100, 3, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,KeyEvent.VK_PAGE_DOWN);
            gameEvents = new GameEvents();
            gameEvents.addObserver(P1);
            gameEvents.addObserver(P2);
            Controls key = new Controls(this.gameEvents);
            addKeyListener(key);
            
        }catch(IOException e){}
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
        P1.updateMove();
        P2.draw(this, g2);
        P2.updateMove();
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
                thread.sleep(35);
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
