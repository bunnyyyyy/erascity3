import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Snake extends MyJFrame implements KeyListener  {

    private JLabel taylorImage, gameOverLabel, timeLabel;
    private List<KanyeHead> kanyeHeads;
    private Timer timer;
    private int x, y;
    private boolean isGameOver;
    private Random random;
    private JPanel panel;
    private JButton playAgainButton;
    public static int STEP = 10;
    private long startTime;
    private long endTime;


    public Snake() {
        
        super();
        random = new Random();
        
        
        taylorImage = new JLabel(new ImageIcon(new ImageIcon("taylor_swift.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
        

        x = 500;
        y = 400;
        super.add(taylorImage, x, y, 50, 50);

       
        kanyeHeads = new ArrayList<>();

        super.frame().addKeyListener(this);
        super.frame().setFocusable(true);
        super.frame().setFocusTraversalKeysEnabled(false);

        isGameOver = false;


    }

    public void start() {
        startTime = System.currentTimeMillis();

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

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            if (y - STEP >= 0) {
                y -= STEP;
            }
            
        } 
        
        else if (key == KeyEvent.VK_S) {
            if (y + STEP <= 750) {
                y += STEP;
            }
        } 
        
        else if (key == KeyEvent.VK_A) {
            if (x - STEP >= 0) {
                x -= STEP;
            }
        } 
        
        else if (key == KeyEvent.VK_D) {
            if (x + STEP <= 950) {
                x += STEP;
            }
        }

       
        super.move(taylorImage, x, y);
    }



    public boolean checkCollisions() {
        
    
        for (KanyeHead kanyeHead : kanyeHeads) {
            if (Math.abs(x - kanyeHead.getX()) <= 50 && Math.abs(y - kanyeHead.getY()) <= 50) {
                return true;
            }
        
        }
    
            return false;
               
    }

    public void gameOver() {
        //System.out.println("DONE COLLIDED");
    }

  
    public void startCollisonTesting() {
        Thread thread = new Thread(() -> {
            while (!isGameOver) {
                
                if (checkCollisions()) {
                    endTime = System.currentTimeMillis();
                    isGameOver = true;
                    gameOver();
                    createGameOverPanel(); 
                    
                }

                try {
                    Thread.sleep(10); 
                } 
                
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
       
    }


    public void createGameOverPanel() {

        gameOverLabel = new JLabel("Time survived:", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));

        timeLabel = new JLabel((endTime - startTime) / 1000 + "s", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        panel = new JPanel(new BorderLayout());
        panel.add(gameOverLabel, BorderLayout.CENTER);
        panel.add(timeLabel, BorderLayout.CENTER);

        System.out.println("HERE");
        //playAgainButton = new JButton("Home Page");
        super.frame().setVisible(false);
        super.frame().dispose();
        


        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.setVisible(true);

       
       
        JOptionPane.showMessageDialog(super.frame(), panel, "Game Over", JOptionPane.PLAIN_MESSAGE);
     


    }


    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }


    




    public static void main(String[] args) {
        Snake game = new Snake();
     

        game.start();
        game.startCollisonTesting();
       
        game.setVisible();


    }

    
}
