/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankGame;

import TankGame.Objects.Explosion;
import TankGame.Objects.Tanks;
import TankGame.Objects.Wall;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JApplet;


/**
 *
 * @author jack
 */
public class GameFrame extends JApplet implements Runnable{
    private final int width = 3008;
    private final int length = 1650;
    private static Tanks P1, P2;
    //private mapdesign walls, breakWall;
    Image tank1, tank2, wall, bwall, weapon, rocket, floor, shell;
    Graphics2D g2;
    //int w= 500, h=500, move=0, speed=5;
    private BufferedImage bimg, p1view, p2view, p1view2, p2view2;
    private Thread thread;
    GameEvents gameEvents;
    //int w=100,h=0;
    private FileReader map;
    private static ArrayList<Wall> solidwall= new ArrayList<Wall>();
    private static ArrayList<Wall> breakwall= new ArrayList<Wall>();
    private static Image[] explosionLarge = new Image[7];
    private static Image[] explosionSmall = new Image[6];
    private static ArrayList<Explosion> explosion = new ArrayList<Explosion>();
    
    public void init(){
        
        setBackground(Color.BLACK);
        this.setFocusable(true);
        try{
            floor = ImageIO.read(new File("TankGame/Resource/Background.bmp"));
            tank1 = ImageIO.read(new File("TankGame/Resource/Tank_blue.png"));
            tank2 = ImageIO.read(new File("TankGame/Resource/Tank_red.png"));
            wall = ImageIO.read(new File("TankGame/Resource/Wall1.gif"));
            bwall = ImageIO.read(new File("TankGame/Resource/Wall2.gif"));
            weapon = ImageIO.read(new File("TankGame/Resource/Weapon.gif"));
            rocket = ImageIO.read(new File("TankGame/Resource/Rocket.gif"));
            explosionLarge[0] = ImageIO.read(new File("TankGame/Resource/explosion2_1.png"));
            explosionLarge[1] = ImageIO.read(new File("TankGame/Resource/explosion2_2.png"));
            explosionLarge[2] = ImageIO.read(new File("TankGame/Resource/explosion2_3.png"));
            explosionLarge[3] = ImageIO.read(new File("TankGame/Resource/explosion2_4.png"));
            explosionLarge[4] = ImageIO.read(new File("TankGame/Resource/explosion2_5.png"));
            explosionLarge[5] = ImageIO.read(new File("TankGame/Resource/explosion2_6.png"));
            explosionLarge[6] = ImageIO.read(new File("TankGame/Resource/explosion2_7.png"));
            explosionSmall[0] = ImageIO.read(new File("TankGame/Resource/explosion1_1.png"));
            explosionSmall[1] = ImageIO.read(new File("TankGame/Resource/explosion1_2.png"));
            explosionSmall[2] = ImageIO.read(new File("TankGame/Resource/explosion1_3.png"));
            explosionSmall[3] = ImageIO.read(new File("TankGame/Resource/explosion1_4.png"));
            explosionSmall[4] = ImageIO.read(new File("TankGame/Resource/explosion1_5.png"));
            explosionSmall[5] = ImageIO.read(new File("TankGame/Resource/explosion1_6.png"));
            map=new FileReader("TankGame/Resource/mapDesign.txt");
        }catch(Exception e){} 
        P1 = new Tanks(tank1, 64, 64, 5 , KeyEvent.VK_W,
                KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_C, 0);
        P2 = new Tanks(tank2, 2890, 1500, 5, KeyEvent.VK_UP,
                KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,KeyEvent.VK_PAGE_DOWN, 30);

        
        gameEvents = new GameEvents();
        gameEvents.addObserver(P1);
        gameEvents.addObserver(P2);
        Controls key = new Controls(this.gameEvents);
        addKeyListener(key);
        Mapdesign();
        SoundPlayer.player("TankGame/Resource/Music.mid", true);
    }
    
    public void Mapdesign(){
        BufferedReader line = new BufferedReader(map);
        String number;
        
        int position=0;
        try{
            while((number = line.readLine()) != null){
                for(int i= 0; i<number.length(); i++){
                    if(number.charAt(i)=='1')
                        this.solidwall.add(new Wall(wall, (position % 94) * 32, (position/ 94) * 32, false));
                    if(number.charAt(i)=='2')
                        this.breakwall.add(new Wall(bwall, (position % 94) * 32, (position/ 94) * 32, true));
                    position++;
                }
            }
        }catch(Exception e){}
    }

    public static Tanks getP1(){
        return P1;
    }
    public static Tanks getP2(){
        return P2;
    }
    public static ArrayList<Wall> getSolidwall(){
        return solidwall;
    }
    public static ArrayList<Wall> getBreakwall(){
        return breakwall;
    }
    public static Image[] getExplosionSmall(){return explosionSmall;}
    public static Image[] getExplosionLarge(){return explosionLarge;}
    public static ArrayList<Explosion> getExplosion(){
        return explosion;
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
        if (!breakwall.isEmpty()) {
            for (int i = 0; i <= breakwall.size() - 1; i++)
                (breakwall.get(i)).draw(this,g2);

        }
        for(int i = 0; i <P1.getBulletList().size(); i++){
            if(P1.getBulletList().get(i).isShowing()){
                P1.getBulletList().get(i).draw(this,g2);

            }
            else if (!P1.getBulletList().get(i).isShowing()){
                P1.getBulletList().remove(i);
            }
        }
        for(int i = 0; i <P2.getBulletList().size(); i++){
            if(P2.getBulletList().get(i).isShowing()){
                P2.getBulletList().get(i).draw(this,g2);

            }
            else if (!P2.getBulletList().get(i).isShowing()){
                P2.getBulletList().remove(i);
            }
        }

        P1.draw(this, g2);
        P1.updateMove();
        P2.draw(this, g2);
        P2.updateMove();

        if(!explosion.isEmpty()){
            for(int i = 0; i <= explosion.size()-1; i++){
                if(explosion.get(i).getFinished()){
                    explosion.remove(i--);
                } else {
                    explosion.get(i).draw(this,g2);
                }
            }
        }

    }
    
    public Graphics2D createGraphics2D(int w, int h) {
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        //g2.setBackground(getBackground());
        //g2.setRenderingHint(RenderingHints.KEY_RENDERING,
          //      RenderingHints.VALUE_RENDER_QUALITY);
        //g2.clearRect(0, 0, w, h);
        return g2;
    }
    
    public void paint(Graphics g) {        
        g2 = createGraphics2D(width,length);
        
        int p1x = this.P1.getX() - TankMain.getX()/4 > 0 ? this.P1.getX() - TankMain.getX()/4 : 0;
        int p1y = this.P1.getY() - TankMain.getY()/2 > 0 ? this.P1.getY() - TankMain.getY()/2 : 0;
        
        if(p1x > width - TankMain.getX()/2){
            p1x = width-TankMain.getX()/2;
        }
        if(p1y > length-TankMain.getY()){
            p1y = length-TankMain.getY();
        }
        
        int p2x = this.P2.getX() - TankMain.getX()/4 > 0 ? this.P2.getX() - TankMain.getX()/4 : 0;
        int p2y = this.P2.getY() - TankMain.getY()/2 > 0 ? this.P2.getY() - TankMain.getY()/2 : 0;
        
        if(p2x > width-TankMain.getX()/2){
            p2x = width-TankMain.getX()/2;
        }
        if(p2y > length-TankMain.getY()){
            p2y = length-TankMain.getY();
        }
        p1view =bimg.getSubimage(p1x, p1y, TankMain.getX()/2, TankMain.getY()-230);
        p2view = bimg.getSubimage(p2x, p2y, TankMain.getX()/2, TankMain.getY()-230);
        p1view2 =bimg.getSubimage(p1x, p1y+TankMain.getY()-230, TankMain.getX()/2-100, 230);
        p2view2 =bimg.getSubimage(p2x+100, p2y+TankMain.getY()-230, TankMain.getX()/2-100, 230);
        drawDemo();
        g.drawImage(p1view, 0, 0, this);
        g.drawImage(p2view, TankMain.getX()/2, 0, this);
        g.drawImage(p1view2, 0, TankMain.getY()-230, this);
        g.drawImage(p2view2, TankMain.getX()/2+100, TankMain.getY()-230, this);
        g.drawRect(TankMain.getX()/2-1, 0, 1, TankMain.getY()-230);
        g.drawImage(bimg, TankMain.getX()/2-100, TankMain.getY()-230, 200, 200, this);
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
}
