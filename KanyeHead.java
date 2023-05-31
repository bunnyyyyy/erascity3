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
    
    public KanyeHead(int direction, int x, int y) {
        
        this.x = x;
        this.y = y;
        this.direction = direction;
        random = new Random();
        kanyeImage = new JLabel(new ImageIcon(new ImageIcon("kanye_west.jpeg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        

        if (direction == 1) {
            super.add(kanyeImage, random.nextInt(950), 0, 50, 50);

        }
        else if (direction == 2) {
            super.add(kanyeImage, 1000, random.nextInt(750), 50, 50);
        }
        else if (direction == 3) {
            super.add(kanyeImage, random.nextInt(950), 800, 50, 50);
        }
        else if (direction == 4) {
            super.add(kanyeImage, 0, random.nextInt(750), 50, 50);
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
            x += 5;
            if (getX() >= 800) {
                outOfBounds = true;
            }
        }
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

}
