import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DressUp extends MyJFrame implements ActionListener {
    public static int size = 10;
    public static Color cyan = new Color(0, 139, 139);
    public static Color lightPurple = new Color(243, 225, 255);
    public static long m = 1000000;
    private ArrayList<JLabel> tops;
    private ArrayList<JLabel> middle;
    private ArrayList<JLabel> bottom;
    private Map<JLabel, String> pictures; 
    private JLabel title;
    private ArrayList<String> albums, speakNowSongs;
    private ArrayList<Long> speakNowTimes;
    private JButton topleft, topright, middleleft, middleright, bottomleft, bottomright, checkButton, skipButton;
    private int topindex = 0, middleindex = 0, bottomindex = 0, titleindex = 0, songindex = 0;
    private int points = 0;
    private JLabel pointsLabel, rightWrongLabel, wrongAlbumLabel;
    private JLabel thisIsLabel;
    private boolean firstTry = true;
    private JLabel doneLabel, donePointsLabel, doneCustomLabel; 
    private JLabel taylorGif;
    private JLabel changeOfMusicLabel, currentSong;
    private JButton backMusic, forwardMusic;


    /**
     * Initializes three linked lists of JLabels with top, middle, and bottom pictures of Taylor Swift outfits. 
     */
    public DressUp() {
        //create the frame
        super();

        //initializes albums to all the names of albums and songs of speak now in order
        albums = new ArrayList<>(Arrays.asList("Debut", "Fearless", "Speak Now", "Red", 
        "1989", "Reputation", "Lover", "Folklore", "Evermore", "Midnights"));
        Collections.shuffle(albums);
        speakNowSongs = new ArrayList<>(Arrays.asList("Mine", "Sparks Fly", "Back To December", "Speak Now",
        "Dear John", "Mean", "The Story of Us", "Never Grow Up", "Enchanted", "Better Than Revenge", "Innocent",
        "Haunted", "Last Kiss", "Long Live"));
        speakNowTimes =  new ArrayList<>(Arrays.asList(0L, 230L*m, 491L*m, 785L*m, 1019L*m, 1415L*m, 1651L*m, 1916L*m, 
        2211L*m, 2562L*m, 2779L*m, 3074L*m, 3317L*m, 3682L*m));
        
        //initializes all the pictures and the buttons
        title = new JLabel(albums.get(0), SwingConstants.CENTER);
        pointsLabel = new JLabel("Points: 0 / 0", SwingConstants.CENTER);
        rightWrongLabel = new JLabel("RIGHT!", SwingConstants.CENTER);
        thisIsLabel = new JLabel("This is ", SwingConstants.CENTER);
        wrongAlbumLabel = new JLabel("album", SwingConstants.CENTER);
        doneLabel = new JLabel("DONE!!", SwingConstants.CENTER);
        donePointsLabel = new JLabel("You scored ", SwingConstants.CENTER);
        doneCustomLabel = new JLabel("custom message", SwingConstants.CENTER);
        changeOfMusicLabel = new JLabel("Currently Playing", SwingConstants.CENTER);
        currentSong = new JLabel(speakNowSongs.get(0), SwingConstants.CENTER);

        //sets the font of all the labels
        title.setFont(new Font("Arial", Font.BOLD, 65));
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 25));
        rightWrongLabel.setFont(new Font("Arial", Font.BOLD, 45));
        thisIsLabel.setFont(new Font("Arial", Font.BOLD, 30));
        wrongAlbumLabel.setFont(new Font("Arial", Font.BOLD, 30));
        doneLabel.setFont(new Font("Arial", Font.BOLD, 51));
        donePointsLabel.setFont(new Font("Arial", Font.BOLD, 51));
        doneCustomLabel.setFont(new Font("Arial", Font.BOLD, 51));
        changeOfMusicLabel.setFont(new Font("Arial", Font.BOLD, 25));
        currentSong.setFont(new Font("Arial", Font.BOLD, 25));
        
        //sets the colors of labels
        thisIsLabel.setForeground(Color.RED);
        wrongAlbumLabel.setForeground(Color.RED);
        doneLabel.setForeground(cyan);
        donePointsLabel.setForeground(cyan);
        doneCustomLabel.setForeground(cyan);


        //initializes hash map to store all images and keys + array lists
        pictures = new HashMap<JLabel, String>();
        tops = new ArrayList<JLabel>();
        middle = new ArrayList<JLabel>();
        bottom = new ArrayList<JLabel>();
        taylorGif = new JLabel(new ImageIcon(new ImageIcon("endscreentay.gif").getImage().getScaledInstance(434, 243, Image.SCALE_DEFAULT)));

        //creates buttons and puts arrow icons on them
        ImageIcon left = new ImageIcon(new ImageIcon("leftarrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon leftsmall = new ImageIcon(new ImageIcon("leftarrow.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        topleft = new JButton(left);
        topleft.setForeground(lightPurple);
        middleleft = new JButton(left);
        bottomleft = new JButton(left);
        backMusic = new JButton(leftsmall);

        ImageIcon right = new ImageIcon(new ImageIcon("rightarrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        ImageIcon rightsmall = new ImageIcon(new ImageIcon("rightarrow.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        topright = new JButton(right);
        middleright = new JButton(right);
        bottomright = new JButton(right);
        forwardMusic = new JButton(rightsmall);

       

        //creates the check + skip buttons + sets their fonts
        checkButton = new JButton("Check");
        skipButton = new JButton("Skip");

        checkButton.setFont(new Font("Arial", Font.BOLD, 25));
        skipButton.setFont(new Font("Arial", Font.BOLD, 25));



        
        // converts all pictures to labels and puts the labels in top, middle, bottom linked lists 
        // additionally also puts labels into pictures hash map with respective keys
        File topfiles = new File("tops");
        File middlefiles = new File("middle");
        File bottomfiles = new File("bottom");

        File[] listoftops = topfiles.listFiles();
        File[] listofmiddles = middlefiles.listFiles();
        File[] listofbottoms = bottomfiles.listFiles();

    
       
        for (File x : listoftops) {
            JLabel label = new JLabel(new ImageIcon(new ImageIcon(x.getAbsolutePath()).getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT)));
            pictures.put(label, getKey(x.getAbsolutePath()));
            if (!getKey(x.getAbsolutePath()).equals("")) {
                tops.add(label);
            } 
        }
        

       
        for (File x : listofmiddles) {
            JLabel label = new JLabel(new ImageIcon(new ImageIcon(x.getAbsolutePath()).getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT)));
            pictures.put(label, getKey(x.getAbsolutePath()));
            if (!getKey(x.getAbsolutePath()).equals("")) {
                middle.add(label);
            }
            
        }

        for (File x : listofbottoms) {
            JLabel label = new JLabel(new ImageIcon(new ImageIcon(x.getAbsolutePath()).getImage().getScaledInstance(300, 150, Image.SCALE_DEFAULT)));
            pictures.put(label, getKey(x.getAbsolutePath()));
            if (!getKey(x.getAbsolutePath()).equals("")) {
                bottom.add(label);
            }
           
        }

        //randomizes picture order
        Collections.shuffle(tops);
        Collections.shuffle(middle);
        Collections.shuffle(bottom);
    }



    //displays the first pictures + buttons
    public void displayInitial() {
        super.add(title, 325, 50, 350, 100);
        super.add(pointsLabel, 760, 50, 190, 100);
        super.add(tops.get(0), 350, 200, 300, 150);
        super.add(middle.get(0), 350, 375, 300, 150);
        super.add(bottom.get(0), 350, 550, 300, 150);
        super.add(rightWrongLabel, 20, 140, 200, 100);
        super.add(thisIsLabel, 20, 240, 200, 100);
        super.add(wrongAlbumLabel, 5, 300, 230, 100);



        //miscellaneous things that need to be done
        rightWrongLabel.setVisible(false);
        rightWrongLabel.setForeground(Color.RED);
        thisIsLabel.setVisible(false);
        wrongAlbumLabel.setVisible(false);

        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        topleft.setBorder(border);
        middleleft.setBorder(border);
        bottomleft.setBorder(border);
        topright.setBorder(border);
        middleright.setBorder(border);
        bottomright.setBorder(border);
        backMusic.setBorder(border);
        forwardMusic.setBorder(border);
        
        //adds music related things
        super.add(changeOfMusicLabel, 675, 660, 300, 100);
        super.add(currentSong, 675, 700, 300, 100);
        super.add(backMusic, 665, 738, 20, 20);
        super.add(forwardMusic, 965, 738, 20, 20);
        
        

       

        //adds the buttons
        super.add(topleft, 250, 250, 50, 50);
        super.add(middleleft, 250, 425, 50, 50);
        super.add(bottomleft, 250, 600, 50, 50);
        super.add(topright, 700, 250, 50, 50);
        super.add(middleright, 700, 425, 50, 50);
        super.add(bottomright, 700, 600, 50, 50);
        super.add(checkButton, 60, 70, 100, 50);
        super.add(skipButton, 180, 70, 100, 50);
        checkPictureIndex();
        checkButtons();
        checkMusicButtons();

        

    }

    //moves to new album 
    public void newTitle() {
        firstTry = true;
        titleindex += 1;
        title.setText(albums.get(titleindex));
        skipButton.setVisible(true);
    }



    //adds functionality to buttons
    public void buttonsWork() {
        topleft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkPictureIndex();
               remove(tops.get(topindex));
               topindex -= 1;
               add(tops.get(topindex), 350, 200, 300, 150);
               checkButtons();
            }
         });

         topright.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkPictureIndex();
               remove(tops.get(topindex));
               topindex += 1;
               add(tops.get(topindex), 350, 200, 300, 150);
               checkButtons();
            }
         });
        
         middleleft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkPictureIndex();
               remove(middle.get(middleindex));
               middleindex -= 1;
               add(middle.get(middleindex), 350, 375, 300, 150);
               checkPictureIndex();
               checkButtons();
            }
         });
         middleright.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkPictureIndex();
               remove(middle.get(middleindex));
               middleindex += 1;
               add(middle.get(middleindex), 350, 375, 300, 150);
               checkPictureIndex();
               checkButtons();
            }
         });

         bottomleft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkPictureIndex();
               remove(bottom.get(bottomindex));
               bottomindex -= 1;
               add(bottom.get(bottomindex), 350, 550, 300, 150);
               checkPictureIndex();
               checkButtons();
            }
         });

         bottomright.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               checkPictureIndex();
               remove(bottom.get(bottomindex));
               bottomindex += 1;
               add(bottom.get(bottomindex), 350, 550, 300, 150);
               checkPictureIndex();
               checkButtons();
            }
         });
         
         checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            
            if (checkButton.getText().equals("Check")) {

                // everything matches up
                if (pictures.get(tops.get(topindex)).equals(pictures.get(middle.get(middleindex))) 
                && pictures.get(middle.get(middleindex)).equals(pictures.get(bottom.get(bottomindex)))
                && pictures.get(bottom.get(bottomindex)).equals(albums.get(titleindex))
                ) {
                    checkButton.setText("Next");
                    rightWrongLabel.setText("RIGHT!");
                    rightWrongLabel.setForeground(Color.GREEN);
                    rightWrongLabel.setVisible(true);
                    if (firstTry) {
                        points += 1;
                    }
                    String text = "Points " + points + " / " + (titleindex + 1);
                    pointsLabel.setText(text);
                    thisIsLabel.setVisible(false);
                    wrongAlbumLabel.setVisible(false);
                    skipButton.setVisible(false);
                    
                }

                // outfit matches but wrong album
                else if (pictures.get(tops.get(topindex)).equals(pictures.get(middle.get(middleindex))) 
                && pictures.get(middle.get(middleindex)).equals(pictures.get(bottom.get(bottomindex)))) {
                    firstTry = false;
                    rightWrongLabel.setText("WRONG!");
                    rightWrongLabel.setForeground(Color.RED);
                    thisIsLabel.setText("This is");
                    wrongAlbumLabel.setText(pictures.get(tops.get(topindex)));

                    

                    rightWrongLabel.setVisible(true);
                    thisIsLabel.setVisible(true);
                    wrongAlbumLabel.setVisible(true);
                }

                // outfit doesn't even match badd
                else {
                    firstTry = false;
                    rightWrongLabel.setText("WRONG!");
                    rightWrongLabel.setForeground(Color.RED);

                    thisIsLabel.setFont(new Font("Arial", Font.BOLD, 36));
                    wrongAlbumLabel.setFont(new Font("Arial", Font.BOLD, 36));

                    thisIsLabel.setText("Outfits Not");
                    wrongAlbumLabel.setText("Matching");
                    rightWrongLabel.setVisible(true);
                    thisIsLabel.setVisible(true);
                    wrongAlbumLabel.setVisible(true);
                
                }

                
            }

            // next button to get new titlel
            else if (checkButton.getText().equals("Next")) {
                
                thisIsLabel.setText("This is");
                checkButton.setText("Check");
                rightWrongLabel.setVisible(false);
                thisIsLabel.setVisible(false);
                wrongAlbumLabel.setVisible(false);

                if (titleindex == size - 1) {
                    doneScreen();
                }
                else {
                    newTitle(); 
                }                   
                
            }
        }
         });

         // skips current album to go to next album
         skipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rightWrongLabel.setVisible(false);
                thisIsLabel.setVisible(false);
                wrongAlbumLabel.setVisible(false);

                String text = "Points " + points + " / " + (titleindex + 1);
                pointsLabel.setText(text);

                if (titleindex == size - 1) {
                    doneScreen();
                }
                else {
                    newTitle();
                }
                
            }
         });



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


    public void doneScreen() {
        
        //removes all previous buttons, keeps music
        remove(checkButton);
        remove(skipButton);
        remove(rightWrongLabel);
        remove(thisIsLabel);
        remove(wrongAlbumLabel);
        remove(title);
        remove(pointsLabel);
        remove(topleft);
        remove(topright);
        remove(middleleft);
        remove(middleright);
        remove(bottomright);
        remove(bottomleft);
        remove(tops.get(topindex));
        remove(middle.get(middleindex));
        remove(bottom.get(bottomindex));
        
        String pointsText = "You scored " + points + " / " + size + "!";
        donePointsLabel.setText(pointsText);
        String customText = "";
        if (points <= 4) {
            customText += "You don't deserve tickets..";
        }
        else if (points <= 6) {
            customText += "Mid fan but not a swiftie!";
        }
        else if (points <= 8) {
            customText += "Taylor would be proud!!";
        }
        else {
            customText += "Certified swiftie :)";
        }
        doneCustomLabel.setText(customText);


        super.add(doneLabel, 20, 100, 730, 100);
        super.add(donePointsLabel, 20, 200, 730, 100);
        super.add(doneCustomLabel, 20, 300, 730, 100);
        super.add(taylorGif, 60, 390, 651, 364);
    }

    //removes left/right buttons if you cannot go more left or right anymore
    public void checkButtons() {
        if(topindex == 0) {
            remove(topleft);
        }
        
        else {
            add(topleft);
        }
        
        if(topindex == size - 1) {
            remove(topright);
        }
        else {
            add(topright);
        }

        if(middleindex == 0) {
            remove(middleleft);
        }
        else {
            add(middleleft);
        }
        if(middleindex == size - 1) {
            remove(middleright);
        }
        else {
            add(middleright);
        }

        if(bottomindex == 0) {
            remove(bottomleft);
        }
        else {
            add(bottomleft);
        }
        if(bottomindex == size - 1) {
            remove(bottomright);
        }
        else {
            add(bottomright);
        }




    }

    public void checkPictureIndex() {
        if (topindex < 0) {
            topindex = 0;
        }
        else if (topindex >= size) {
            topindex = size - 1;
        }

        if (middleindex < 0) {
            middleindex = 0;
        }
        else if (middleindex >= size) {
            middleindex = size - 1;
        }

        if (bottomindex < 0) {
            bottomindex = 0;
        }
        else if (bottomindex >= size) {
            bottomindex = size - 1;
        }
       
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
        super.add(label, x, y, width, height);

    }

    public String getKey(String filePath) {
        int lastSlashIndex = filePath.lastIndexOf('/');
        int dotIndex = filePath.lastIndexOf('.');

        String key = filePath.substring(lastSlashIndex + 1, dotIndex);
        return key;

    }



    
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }


}

    

