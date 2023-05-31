import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends JPanel {

    private Image taylorImage;
    private List<KanyeHead> kanyeHeads;
    private Timer timer;
    private int taylorX;
    private int taylorY;
    private boolean isGameOver;
    private Random random;

    public Snake() {
        super();

        random = new Random();
        
        
        taylorImage = new ImageIcon("taylor_swift.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
       
        
        taylorX = 500;
        taylorY = 400;
       
        kanyeHeads = new ArrayList<>();


    }

    public void start() {
        timer = new Timer(3000, e -> {

            
            kanyeHeads.add(new KanyeHead(random.nextInt(5)));
            
        });

        timer = new Timer(200, e -> {
            
            for (KanyeHead head : kanyeHeads) {
                head.move();
            }
        });
    }


    public static void main(String[] args) {
        Snake game = new Snake();
        game.start();
        

    }
}
