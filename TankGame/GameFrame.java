/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import TankGame.Objects.Shell;
import TankGame.Objects.Tanks;
import TankGame.Objects.Wall;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;


/**
 *
 * @author jack
 */
public class GameFrame extends JApplet implements Runnable{
    private final int width = 960;
    private final int length = 740;
    private Tanks P1, P2;
    //private mapdesign walls, breakWall;
    Image tank1, tank2, wall, bwall, weapon, rocket, floor, shell;
    Graphics2D g2;
    //int w= 500, h=500, move=0, speed=5;
    private BufferedImage bimg;
    private Thread thread;
    GameEvents gameEvents;
    int w=100,h=0;
    private InputStream map;
    private ArrayList<Wall> solidwall= new ArrayList<Wall>();
    private ArrayList<Wall> breakwall= new ArrayList<>();
    static ArrayList<Shell> shells = new ArrayList<>();
    
    public void init(){
        setBackground(Color.BLACK);
        this.setFocusable(true);
        try{
            floor = ImageIO.read(new File("TankGame/Resource/Background.bmp"));
            tank1 = ImageIO.read(GameFrame.class.getResource("Resource/Tank_blue.png"));
            tank2 = ImageIO.read(GameFrame.class.getResource("Resource/Tank_red.png"));
            wall = ImageIO.read(GameFrame.class.getResource("Resource/Wall1.png"));
            bwall = ImageIO.read(new File("TankGame/Resource/Wall1.gif"));
            weapon = ImageIO.read(new File("TankGame/Resource/Weapon.gif"));
            rocket = ImageIO.read(new File("TankGame/Resource/Rocket.gif"));
            map=this.getClass().getClassLoader().getResourceAsStream("TankGame/Resource/mapDesign.txt");
            P1 = new Tanks(tank1, 0, 0, 5, 100, 3,width, length, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_C);
            P2 = new Tanks(tank2, 700, 500, 5, 100, 3,width, length, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,KeyEvent.VK_PAGE_DOWN);

        }catch(Exception e){}    
            gameEvents = new GameEvents();
            gameEvents.addObserver(P1);
            gameEvents.addObserver(P2);
            Controls key = new Controls(this.gameEvents);
            addKeyListener(key);
            Mapdesign();
        
    }
    
    public void Mapdesign(){
        BufferedReader line = new BufferedReader(new InputStreamReader(map));
        String number;
        
        int position=0;
        try{
            while((number = line.readLine()) != null){
                for(int i= 0; i<number.length(); i++){
                    if(number.charAt(i)=='1')
                        this.solidwall.add(new Wall(wall, (position % 30) * 32, (position/ 30) * 32, false));
                    position++;
                }
            }
        }catch(Exception e){}
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
        if (!solidwall.isEmpty()) {
            for (int i = 0; i <= solidwall.size() - 1; i++)
		(solidwall.get(i)).draw(this, g2);
        }

        P1.draw(this, g2);
        P1.updateMove();
        P2.draw(this, g2);
        P2.updateMove();
        for(int i = 0; i <P1.getBulletList().size(); i++){
            if(P1.getBulletList().get(i).getShow()){
                P1.getBulletList().get(i).draw(this,g2);
                P1.getBulletList().get(i).update();
            }
            else{
                P1.getBulletList().remove(i);
            }
        }
        for(int i = 0; i <P2.getBulletList().size(); i++){
            if(P2.getBulletList().get(i).getShow()){
                P2.getBulletList().get(i).draw(this,g2);
                P2.getBulletList().get(i).update();
            }
            else{
                P2.getBulletList().remove(i);
            }
        }

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
