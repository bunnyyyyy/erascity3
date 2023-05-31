import java.awt.Image;

import javax.swing.ImageIcon;

public class KanyeHead extends MyJFrame {
    private int x;
    private int y;
    private Image kanyeImage;
    
    public KanyeHead(int x, int y) {
        
        this.x = x;
        this.y = y;
        kanyeImage = new ImageIcon("kanye_west.jpeg").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        y += 5;
    }

}
