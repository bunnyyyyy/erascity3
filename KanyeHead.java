import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class KanyeHead extends MyJFrame implements Runnable {
    private int x;
    private int y;
    private int direction;
    public JLabel kanyeImage;
    private boolean outOfBounds;
    private Random random;
    private JFrame frame;
    public static int STEP = 5;
    
    public KanyeHead(JFrame frame, int direction) {
        
        this.frame = frame;
        this.direction = direction;
        random = new Random();
        kanyeImage = new JLabel(new ImageIcon(new ImageIcon("kanye_west.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        

        if (direction == 1) {
            x = random.nextInt(950);
            add(kanyeImage, x, 20, 50, 50);
            //ut.println("YAYAYAY");
            y = 20;
        }

        else if (direction == 2) {
            y = random.nextInt(750);
            add(kanyeImage, 950, y, 50, 50);
            x = 1000;
            
        }

        else if (direction == 3) {
            x = random.nextInt(950);
            add(kanyeImage, random.nextInt(950), 750, 50, 50);
            y = 800;
        }

        else if (direction == 4) {
            y = random.nextInt(750);
            add(kanyeImage, 10, random.nextInt(750), 50, 50);
            x = 0;

        }

       

        outOfBounds = false;

    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {

        if (direction == 1) {
            y += STEP;

            if (getY() >= 800) {
                outOfBounds = true;
            }
        }

        else if (direction == 2) {
            x -= STEP;
            if (getX() <= -50) {
                outOfBounds = true;
            }
        }

        else if (direction == 3) {
            y -= STEP;
            if (getY() <= -50) {
                outOfBounds = true;
            }
        }

        else if (direction == 4) {
            x += STEP;
            if (getX() >= 1000) {
                outOfBounds = true;
            }
        }

        super.move(kanyeImage, getX(), getY());

        


    }


    @Override
    public void run() {
        while (!isOutOfBounds()) {
            move();
            

            SwingUtilities.invokeLater(() -> {
                //ut.println("moving");
                super.move(kanyeImage, getX(), getY());
            });

            try {
                Thread.sleep(100); 
            } 
            
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public void add(JLabel label, int x, int y, int width, int height) {

        label.setBounds(x, y, width, height);
        label.setVisible(true);
        frame.getContentPane().add(label);

    }

    public void remove(JLabel label) {
        frame.getContentPane().remove(label);
    }


    public boolean isOutOfBounds() {
        return outOfBounds;
    }

}
