
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {
    private Timer timer = new Timer(5, this);
    private int time = 0;
    private int shootCount = 0;
    private BufferedImage image;
    private ArrayList<Fire> fires = new ArrayList<>();

    private int firedirY = 1;
    private int ballX = 0;
    private int balldirX = 2;
    private int rocketX = 0;
    private int dirrocketX = 20;
    public boolean Control(){
        for(Fire fire :fires){
            if (new Rectangle(fire.getX(),fire.getY(),10,20).intersects(new Rectangle(ballX, 0,20, 20))) {
                return true;
            }
            
        }
        return false;
        
    }
    public Game() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("rocket1.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.BLACK);
        
        timer.start();

    }

    @Override
    public void paint(Graphics g) {
        time+=5;
        super.paint(g);
        g.setColor(Color.red);

        g.fillOval(ballX, 0, 20, 20);

        g.drawImage(image, rocketX, 490, image.getWidth()/10, image.getHeight()/10, this);
        
        for(Fire fire:fires){
            if (fire.getY()<0) {
                fires.remove(fire);
                
            }
        }
        g.setColor(Color.red);
        for(Fire fire:fires){
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }
        
        if (Control()) {
            timer.stop();
            String message = "You won!\nTime:"+time/1000+"\nShootCount"+shootCount;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }

    }

    @Override
    public void repaint() {
        super.repaint(); 
    }

    @Override
     

    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Fire fire:fires) {
            fire.setY(fire.getY()-firedirY);
        }
        int c =e.getKeyCode();
        
        if (c ==KeyEvent.VK_LEFT) {
            if (rocketX<=0) {
                rocketX=0;
            }
            else{
                rocketX-=dirrocketX;
            }
        }
        else if (c ==KeyEvent.VK_RIGHT) {
            if (rocketX>=750) {
                rocketX=750;
            }
            else{
                rocketX+=dirrocketX;
            }
        }
        else if(c ==KeyEvent.VK_CONTROL){
            fires.add(new Fire(rocketX+10, 490));
            shootCount++;
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX+=balldirX;
        if (ballX>750) {
            balldirX=-balldirX;
        }
        if (ballX<=0) {
            balldirX=-balldirX;
        }
        repaint();
    }

}
