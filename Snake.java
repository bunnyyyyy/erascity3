import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends JPanel implements KeyListener {

    private Image taylorImage;
    private List<KanyeHead> kanyeHeads;
    private Timer timer;
    private int taylorX;
    private int taylorY;
    private boolean isGameOver;

    public Snake() {
        super();

        
        
        taylorImage = new ImageIcon("taylor_swift.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
       
        
        taylorX = 500;
        taylorY = 400;
       
        kanyeHeads = new ArrayList<>();


    }

    public void start() {
        timer = new Timer(3000, e -> {

            //random x coordinate to drop down on
            Random random = new Random();
            int kanyeX = random.nextInt(1000-50);
            kanyeHeads.add(new KanyeHead(kanyeX, 0));
            repaint();
        });

        timer = new Timer(200, e -> {
            
            //random x coordinate to drop down on
            Random random = new Random();
            int kanyeX = random.nextInt(1000-50);
            kanyeHeads.add(new KanyeHead(kanyeX, 0));
            repaint();
        });
    }


    public static void main(String[] args) {

    }
}
