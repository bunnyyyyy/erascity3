import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class KanyeHead extends MyJFrame {
    private int x;
    private int y;
    private int direction;
    private JLabel kanyeImage;
    private boolean outOfBounds;
    private Random random;
    
    public KanyeHead(int direction) {
        

        this.direction = direction;
        random = new Random();
        kanyeImage = new JLabel(new ImageIcon(new ImageIcon("kanye_west.jpeg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        

        if (direction == 1) {
            x = random.nextInt(950);
            super.add(kanyeImage, x, 0, 50, 50);
            y = 0;
        }

        else if (direction == 2) {
            y = random.nextInt(750);
            super.add(kanyeImage, 1000, y, 50, 50);
            x = 1000;
            
        }

        else if (direction == 3) {
            x = random.nextInt(950);
            super.add(kanyeImage, random.nextInt(950), 800, 50, 50);
            y = 800;
        }

        else if (direction == 4) {
            y = random.nextInt(750);
            super.add(kanyeImage, 0, random.nextInt(750), 50, 50);
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
            y += 5;

            if (getY() >= 800) {
                outOfBounds = true;
            }
        }

        else if (direction == 2) {
            x -= 5;
            if (getX() <= -50) {
                outOfBounds = true;
            }
        }

        else if (direction == 3) {
            y -= 5;
            if (getY() <= -50) {
                outOfBounds = true;
            }
        }

        else if (direction == 4) {
            x += 5;
            if (getX() >= 1000) {
                outOfBounds = true;
            }
        }

        super.move(kanyeImage, getX(), getY());

        


    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

}
