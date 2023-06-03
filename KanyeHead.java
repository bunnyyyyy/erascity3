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
    private JLabel kanyeImage;
    private boolean outOfBounds;
    private Random random;
    private JFrame frame;
    private static int STEP = 5;
    
    /**
     * Initializes the frame/direction and displays the kanye head on a random side.
     * 
     * @param frame frame that kanye heads are going on
     * @param direction 1 - kanye comes from top
     * 2 - kanye comes from right
     * 3 - kanye comes from bottom
     * 4 - kanye comes from left
     */
    public KanyeHead(JFrame frame, int direction) {
        
        this.frame = frame;
        this.direction = direction;
        random = new Random();
        kanyeImage = new JLabel(new ImageIcon(new ImageIcon("pictures/kanye_west.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        

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


    /**
     * Retuns x pos of Kanye.
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Retuns y pos of Kanye.
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Moves kanye a length of STEP and checks if it is out of bounds.
     */
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


    /**
     * Moves Kanye one step every 100ms if Kanye is still in bounds. 
     */
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



    /**
     * Adds a label to the frame.
     * 
     * @param label label to be added
     * @param x x-coordinate
     * @param y y-coordinatee
     * @param width width of label
     * @param height height of label
     */
    public void add(JLabel label, int x, int y, int width, int height) {

        label.setBounds(x, y, width, height);
        label.setVisible(true);
        frame.getContentPane().add(label);

    }

    /**
     * Removes the label from a frame.
     * @param label label to remove
     */
    public void remove(JLabel label) {
        frame.getContentPane().remove(label);
    }


    /**
     * Returns if Kanye is still on frame.
     * @return true if kanye is out of bounds; false otherwise
     */
    public boolean isOutOfBounds() {
        return outOfBounds;
    }

}
