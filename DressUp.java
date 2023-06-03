import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * 
 * Creates the matching outfits game.
 * @author Sanya Badhe
 * @version 6/2/23
 * 
 */
public class DressUp extends MyJFrame {
    private static int size = 10;
    private static Color cyan = new Color(0, 139, 139);
    private static Color lightPurple = new Color(243, 225, 255);
    private ArrayList<JLabel> tops;
    private ArrayList<JLabel> middle;
    private ArrayList<JLabel> bottom;
    private ArrayList<String> albums;
    private Map<JLabel, String> pictures; 
    private JLabel title;
    private JButton topleft, topright, middleleft, middleright, bottomleft, bottomright, checkButton, skipButton;
    private int topindex = 0, middleindex = 0, bottomindex = 0, titleindex = 0;
    private int points = 0;
    private JLabel pointsLabel, rightWrongLabel, wrongAlbumLabel;
    private JLabel thisIsLabel;
    private boolean firstTry = true;
    private JLabel doneLabel, donePointsLabel, doneCustomLabel; 
    private JLabel taylorGif;


    /**
     * Calls super() to create a JFrame.
     * Initializes albums to all the names of albums and songs of speak now in order.
     * Initializes all the pictures and the buttons.
     * Sets the fonts of all the labels.
     * Sets the colors of all the labels.
     * Creates buttons and puts arrow icons on them
     * Initializes hash map to store all images and keys + array lists.
     * Randomizes the picture order.
     */
    public DressUp() {
        //create the frame
        super();

        //initializes albums to all the names of albums
        albums = new ArrayList<>(Arrays.asList("Debut", "Fearless", "Speak Now", "Red", 
        "1989", "Reputation", "Lover", "Folklore", "Evermore", "Midnights"));
        Collections.shuffle(albums);
        
        //initializes all the pictures and the buttons
        title = new JLabel(albums.get(0), SwingConstants.CENTER);
        pointsLabel = new JLabel("Points: 0 / 0", SwingConstants.CENTER);
        rightWrongLabel = new JLabel("RIGHT!", SwingConstants.CENTER);
        thisIsLabel = new JLabel("This is ", SwingConstants.CENTER);
        wrongAlbumLabel = new JLabel("album", SwingConstants.CENTER);
        doneLabel = new JLabel("DONE!!", SwingConstants.CENTER);
        donePointsLabel = new JLabel("You scored ", SwingConstants.CENTER);
        doneCustomLabel = new JLabel("custom message", SwingConstants.CENTER);

        //sets the font of all the labels
        title.setFont(new Font("Arial", Font.BOLD, 65));
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 25));
        rightWrongLabel.setFont(new Font("Arial", Font.BOLD, 45));
        thisIsLabel.setFont(new Font("Arial", Font.BOLD, 30));
        wrongAlbumLabel.setFont(new Font("Arial", Font.BOLD, 30));
        doneLabel.setFont(new Font("Arial", Font.BOLD, 51));
        donePointsLabel.setFont(new Font("Arial", Font.BOLD, 51));
        doneCustomLabel.setFont(new Font("Arial", Font.BOLD, 51));
        
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
        taylorGif = new JLabel(new ImageIcon(new ImageIcon("pictures/endscreentay.gif").getImage().getScaledInstance(434, 243, Image.SCALE_DEFAULT)));

        //creates buttons and puts arrow icons on them
        ImageIcon left = new ImageIcon(new ImageIcon("pictures/leftarrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));

        topleft = new JButton(left);
        topleft.setForeground(lightPurple);
        middleleft = new JButton(left);
        bottomleft = new JButton(left);

        ImageIcon right = new ImageIcon(new ImageIcon("pictures/rightarrow.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        topright = new JButton(right);
        middleright = new JButton(right);
        bottomright = new JButton(right);

       

        //creates the check + skip buttons + sets their fonts
        checkButton = new JButton("Check");
        skipButton = new JButton("Skip");

        checkButton.setFont(new Font("Arial", Font.BOLD, 25));
        skipButton.setFont(new Font("Arial", Font.BOLD, 25));



        
        // converts all pictures to labels and puts the labels in top, middle, bottom linked lists 
        // additionally also puts labels into pictures hash map with respective keys
        File topfiles = new File("pictures/tops");
        File middlefiles = new File("pictures/middle");
        File bottomfiles = new File("pictures/bottom");

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



    /**
     * Displays all the labels + buttons on the frame in their initial positions.
     */
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

        //sets the border of buttons
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        topleft.setBorder(border);
        middleleft.setBorder(border);
        bottomleft.setBorder(border);
        topright.setBorder(border);
        middleright.setBorder(border);
        bottomright.setBorder(border);
        
    
        
        

       

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


        

    }

   /**
    * Resets the check/skip button and changes the current title to the next album.
    */
    public void newTitle() {
        firstTry = true;
        titleindex += 1;
        title.setText(albums.get(titleindex));
        skipButton.setVisible(true);
    }



    /**
     * Adds functionality to all the buttons, so when top left is pushed the top pictures move one picture to the left, and same for 
     * top right, middleleft, middleright, bottomleft, bottomright.
     * 
     * Also adds functionality to check/skip buttons
     */
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



         



    }


    /**
     * Displays the done screen which removes all previous elements and replaces it with a 
     * taylor gif and a customized messages depending on the score.
     */
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

    /**
     * Removes left/right buttons if you cannot go more left or right anymore.
     */
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

    /**
     * From time to time, the buttons will be pressed too fast and no pictures will show up. This method checks if this 
     * has happened, and readjusts the picture's indexes to either the first/last picture.
     */
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

    
    /**
     * Sets a button to be invisible.
     * @param button button to be set invisible
     */
    public void add(JButton button) {
        button.setVisible(true);
    }

    /**
     * Sets a label to be invisible.
     * @param label label to be set invisible
     */
    public void add(JLabel label) {
        label.setVisible(true);
    }
    
    /**
     * Sets a label to be visible.
     * @param label label to be set visible
     */
    public void remove(JLabel label) {
        label.setVisible(false);
    }
    
    /**
     * Sets a button to be visible.
     * @param button button to be set visible
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
        super.add(label, x, y, width, height);

    }

    /**
     * Parses a picture's filepath to get the album name of the picture
     * 
     * @param filePath filepath of picture
     * @return album name that the picture belongs to
     */
    public String getKey(String filePath) {
        int lastSlashIndex = filePath.lastIndexOf('/');
        int dotIndex = filePath.lastIndexOf('.');

        String key = filePath.substring(lastSlashIndex + 1, dotIndex);
        return key;

    }



    
   

}

    

