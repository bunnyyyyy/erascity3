import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class PlayMusic {

    
    private static long m = 1000000;
    private ArrayList<String> speakNowSongs;
    private ArrayList<Long> speakNowTimes;
    private int songindex = 0;;
    private JLabel changeOfMusicLabel, currentSong;
    private JButton backMusic, forwardMusic;
    private JFrame frame;


    /**
     * Initializes the speak now song names along with their timestamps in snowmusic.wav. 
     * Initializes song related labels and buttons. 
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
        
     
        
        changeOfMusicLabel = new JLabel("Currently Playing", SwingConstants.CENTER);
        currentSong = new JLabel(speakNowSongs.get(0), SwingConstants.CENTER);
      
        changeOfMusicLabel.setFont(new Font("Arial", Font.BOLD, 25));
        currentSong.setFont(new Font("Arial", Font.BOLD, 25));
        
      
       
        ImageIcon leftsmall = new ImageIcon(new ImageIcon("pictures/leftarrow.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        backMusic = new JButton(leftsmall);

        ImageIcon rightsmall = new ImageIcon(new ImageIcon("pictures/rightarrow.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        forwardMusic = new JButton(rightsmall);



   

    }


        
       


    /**
     *  Displays all the music labels + buttons on the frame in their initial positions.
     */
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

   



    /**
     * Adds functionality to the move back and move forward song buttons.
     */
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

  



   /**
    * Removes left button if you cannot go left anymore, and same for right.
    */ 
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


    Music speakNowMusic = new Music("snowmusic.wav");

    /**
     * Plays music from last saved position.  
     */
    public void playMusic() {
        speakNowMusic.playMusic();
    }

     /**
     * Saves current position and pauses music.
     */
    public void pauseMusic() {
        speakNowMusic.pauseMusic();
    }

    /**
     * Switches the song playing to the one at timestamp given.
     * @param time timestamp of current song
     */
    public void setMusic(long time) {
        speakNowMusic.setSong(time);
    }


    /**
     * Sets button in to be visible.
     * @param button button to be visible
     */
    public void add(JButton button) {
        button.setVisible(true);
    }

    /**
     * Sets label in to be visible.
     * @param label label to be visible
     */
    public void add(JLabel label) {
        label.setVisible(true);
    }
    
    
    /**
     * Sets label in to be invisible.
     * @param label label to be invisible
     */
    public void remove(JLabel label) {
        label.setVisible(false);
    }
    
    /**
     * Sets button in to be invisible.
     * @param button button to be invisible
     */
    public void remove(JButton button) {
        button.setVisible(false);
    }


    /**
     * Adds a label to the frame.
     * 
     * @param label label to be added
     * @param x x-coordinate
     * @param y y-coordinatee
     * @param width width of label
     * @param height height of label
     */
    public void add(JLabel label, int x, int y, int width, int height) {

        label.setBounds(x, y, width, height);
        label.setVisible(true);
        frame.getContentPane().add(label);

    }

    /**
     * Adds a button to the frame.
     * 
     * @param button button to be added
     * @param x x-coordinate
     * @param y y-coordinatee
     * @param width width of button
     * @param height height of button
     */
    public void add(JButton button, int x, int y, int width, int height) {

        button.setBounds(x, y, width, height);
        button.setVisible(true);
        frame.getContentPane().add(button);

    }





}



    

