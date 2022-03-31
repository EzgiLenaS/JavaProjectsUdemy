import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Bullet{
    private int x;
    private int y;

    public Bullet(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}
public class Game extends JPanel implements KeyListener, ActionListener{
//To get the keyword, KeyListener is used.
//To get the mouse, ActionListener is used (Action performed)

    //swing timer
    Timer timer = new Timer(5, this);
    private int passedTime = 0;
    private int spentBullet = 0;
    private BufferedImage image;

    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();

    private int bulletdirY = 1;
    private int ballX = 0;
    private int balldirX = 2;
    private int spaceshipX = 0;
    private int dirSpaceX = 20;

    public boolean check()
    {
        for(Bullet bullet: bullets)
        {
            if(new Rectangle(bullet.getX(), bullet.getY(), 10, 20).intersects(new Rectangle(ballX, 0, 20,20)))
            {
                return true;
            }
        }
        return false;
    }

    public Game()
    {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("spaceship1.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setBackground(Color.BLACK); 
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);

        passedTime += 5;

        g.setColor(Color.red);
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(image, spaceshipX , 490, image.getWidth() / 10, image.getHeight() / 10, this);
        for(Bullet bullet : bullets)
        {
            if(bullet.getY() < 0)
            {
                bullets.remove(bullet);
            }
        }

        g.setColor(Color.BLUE);
        for(Bullet bullet: bullets)
        {
            g.fillRect(bullet.getX(), bullet.getY(), 10, 20);
        }

        if(check())
        {
            timer.stop();
            String message = "You Win :)\n"
                            + "Spent Bullet: " + spentBullet
                            + "\nPassed Time: " + passedTime / 1000.0 + " sec.";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    //it should be written for the games
    @Override
    public void repaint() {
        // TODO Auto-generated method stub
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        for(Bullet bullet: bullets)
        {
            bullet.setY(bullet.getY() - bulletdirY);
        }
        ballX = ballX + balldirX;
        if(ballX >= 750)
        {
            balldirX = -balldirX;
        }
        if(ballX <= 0)
        {
            balldirX = -balldirX;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int c = e.getKeyCode(); //press left/right
        if(c == KeyEvent.VK_LEFT)
        {
            if(spaceshipX <= 0)
            {
                spaceshipX = 0;
            }
            else
            { 
                spaceshipX -= dirSpaceX; 
            }
        }
        else if(c == KeyEvent.VK_RIGHT)
        {
            if(spaceshipX >= 750)
            {
                spaceshipX = 750;
            }
            else
            {
                spaceshipX += dirSpaceX;
            }
        }  
        else if(c == KeyEvent.VK_CONTROL)
        {
            bullets.add(new Bullet(spaceshipX + 15, 490));
            spentBullet++;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
