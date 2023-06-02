import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {

    
    
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

        x2048Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Game game = new Game();
               game.setUpGUI();
            }
         });

         snakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Snake game = new Snake();
     

                game.start();
                game.startCollisonTesting();
               
                game.setVisible();
        
            }
         });

         dressUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DressUp speakNow = new DressUp();
        
                speakNow.displayInitial();
                speakNow.setVisible();
        
                speakNow.buttonsWork();
        
            }
         });

         recButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reccomendations rec = new Reccomendations();
       
                rec.displayInitial();
                rec.setVisible();
            }
         });


        PlayMusic music = new PlayMusic(frame.frame());
        music.displayInitial();
        music.buttonsWork();
        music.playMusic();

         

        frame.setVisible();


    }



       

}