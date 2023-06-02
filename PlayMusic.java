import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PlayMusic extends Main implements ActionListener {

    
    public static long m = 1000000;
    
    private ArrayList<String> speakNowSongs;
    private ArrayList<Long> speakNowTimes;
    private int songindex = 0;;
    private JLabel changeOfMusicLabel, currentSong;
    private JButton backMusic, forwardMusic;
    private JFrame frame;


    /**
     * Initializes three linked lists of JLabels with top, middle, and bottom pictures of Taylor Swift outfits. 
     */
    public PlayMusic(JFrame frame) {
        //create the frame
        this.frame = frame;

        //initializes albums to all the names of albums and songs of speak now in order
        speakNowSongs = new ArrayList<>(Arrays.asList("Mine", "Sparks Fly", "Back To December", "Speak Now",
        "Dear John", "Mean", "The Story of Us", "Never Grow Up", "Enchanted", "Better Than Revenge", "Innocent",
        "Haunted", "Last Kiss", "Long Live"));
        speakNowTimes =  new ArrayList<>(Arrays.asList(0L, 230L*m, 491L*m, 785L*m, 1019L*m, 1415L*m, 1651L*m, 1916L*m, 
        2211L*m, 2562L*m, 2779L*m, 3074L*m, 3317L*m, 3682L*m));
        
        //initializes all the pictures and the buttons
        
        changeOfMusicLabel = new JLabel("Currently Playing", SwingConstants.CENTER);
        currentSong = new JLabel(speakNowSongs.get(0), SwingConstants.CENTER);

        //sets the font of all the labels
      
        changeOfMusicLabel.setFont(new Font("Arial", Font.BOLD, 25));
        currentSong.setFont(new Font("Arial", Font.BOLD, 25));
        
        //sets the colors of labels
      


        //initializes hash map to store all images and keys + array lists
       
        //creates buttons and puts arrow icons on them
       
        ImageIcon leftsmall = new ImageIcon(new ImageIcon("leftarrow.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

       
        backMusic = new JButton(leftsmall);

        ImageIcon rightsmall = new ImageIcon(new ImageIcon("rightarrow.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        forwardMusic = new JButton(rightsmall);

       

        //creates the check + skip buttons + sets their fonts
   

    }


        
       


    //displays the first pictures + buttons
    public void displayInitial() {
       

        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
   
        backMusic.setBorder(border);
        forwardMusic.setBorder(border);
        
        //adds music related things
        add(changeOfMusicLabel, 675, 660, 300, 100);
        add(currentSong, 675, 700, 300, 100);
        add(backMusic, 665, 738, 20, 20);
        add(forwardMusic, 965, 738, 20, 20);
        
        
        checkMusicButtons();

        

    }

   



    //adds functionality to buttons
    public void buttonsWork() {
       


        //music buttons!!!!!!!!!!!
        backMusic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                songindex -= 1;
                currentSong.setText(speakNowSongs.get(songindex));
                long time = speakNowTimes.get(songindex);
                setMusic(time);    
                checkMusicButtons();
            }
         });
         forwardMusic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                songindex += 1;
                currentSong.setText(speakNowSongs.get(songindex));
                long time = speakNowTimes.get(songindex);
                setMusic(time);    
                checkMusicButtons();
            }
         });

         



    }




    public void checkMusicButtons() {
        if(songindex == 0) {
            remove(backMusic);
        }
        else {
            add(backMusic);
        }
        if(songindex == 13) {
            remove(forwardMusic);
        }
        else {
            add(forwardMusic);
        }
    }



    //below is everything music related
    Music speakNowMusic = new Music("snowmusic.wav");

    public void playMusic() {
        speakNowMusic.playMusic();
    }

    public void setMusic(long time) {
        speakNowMusic.setSong(time);
    }


    public void add(JButton button) {
        button.setVisible(true);
    }

    public void add(JLabel label) {
        label.setVisible(true);
    }
    
    
    public void remove(JLabel label) {
        label.setVisible(false);
    }
    
    public void remove(JButton button) {
        button.setVisible(false);
    }


    public void add(JLabel label, int x, int y, int width, int height) {

        label.setBounds(x, y, width, height);
        label.setVisible(true);
        frame.getContentPane().add(label);

    }

    public void add(JButton button, int x, int y, int width, int height) {

        button.setBounds(x, y, width, height);
        button.setVisible(true);
        frame.getContentPane().add(button);

    }




    
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}



    

