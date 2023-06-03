import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {
  
    private static boolean paused = false;
  
    
    
    /**
     * Main method which adds buttons for each of the 4 mini games. Each button 
     * declares an instance of its respctive game and starts the games.
     * Also plays music in the main frame. 
     * @param args allows for the use of command-line argumnts
     */
    public static void main(String[] args) { 

        MyJFrame frame = new MyJFrame();

        JLabel title = new JLabel("Taylor Mini Games", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 65));
        
        JButton x2048Button, snakeButton, dressUpButton, recButton;
        
        x2048Button = new JButton("2048");
        snakeButton = new JButton("Reputation Era");
        dressUpButton = new JButton("Dress Up");
        recButton = new JButton("Song Recs");


        x2048Button.setFont(new Font("Arial", Font.BOLD, 50));
        snakeButton.setFont(new Font("Arial", Font.BOLD, 50));
        dressUpButton.setFont(new Font("Arial", Font.BOLD, 50));
        recButton.setFont(new Font("Arial", Font.BOLD, 50));

        frame.add(title, 10, 50, 980, 100);
        frame.add(x2048Button, 50, 200, 400, 150);
        frame.add(snakeButton, 450, 200, 400, 150);
        frame.add(dressUpButton, 50, 400, 400, 150);
        frame.add(recButton, 450, 400, 400, 150);

        PlayMusic music = new PlayMusic(frame.frame());
        music.displayInitial();
        music.buttonsWork();
        music.playMusic();

        
        

        x2048Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Game game = new Game();
                game.setUpGUI();
               if (paused) {
                music.playMusic();
                paused = false;
            }
               
            }
         });

         snakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Snake game = new Snake();
     
                music.pauseMusic();
                game.start();
                game.startCollisonTesting();
                paused = true;
               
                game.setVisible();
        
            }
         });

         dressUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DressUp speakNow = new DressUp();
        
                speakNow.displayInitial();
                speakNow.setVisible();

                if (paused) {
                    music.playMusic();
                    paused = false;
                }
        
                speakNow.buttonsWork();
        
            }
         });

         recButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reccomendations rec = new Reccomendations();
       
                rec.displayInitial();
                rec.setVisible();
                if (paused) {
                    music.playMusic();
                    paused = false;
                }
            }
         });


        frame.setVisible();


    }



       

}