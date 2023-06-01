import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake extends MyJFrame {

    private Image taylorImage;
    private List<KanyeHead> kanyeHeads;
    private Timer timer, timer1;
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

        //KanyeHead head = new KanyeHead(super.frame(), 1);


    }

    public void start() {
        timer = new Timer(300, e -> {

        //    System.out.println("ASDHFHSALKLF");
          

            SwingUtilities.invokeLater(() -> {
                KanyeHead kanyeHead = new KanyeHead(super.frame(), random.nextInt(5)); 
                kanyeHeads.add(kanyeHead);
                
    
                Thread thread = new Thread(kanyeHead);
                thread.start();
            });
            
            
        });
        timer.start();
        

        

    }


    public static void main(String[] args) {
        Snake game = new Snake();
     
        

        game.start();
        game.setVisible();


    }
}
