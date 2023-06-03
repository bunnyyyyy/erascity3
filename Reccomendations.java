import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.json.JSONArray;
import org.json.JSONObject;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

/**
 * @author Sanya Badhe
 * @version 6/2/23
 * Inputs taylor song lyrics and offers reccomendations according to the song.
 */
public class Reccomendations extends MyJFrame {
    
    private static final String API_KEY = ApiKey.APIKEY();
    private int songID;
    private String title, artist;
    private String stringInput;
    private JButton enterLyricsButton;
    private JLabel songNameLabel, titleLabel, recLabel, rec1, rec2, rec3, rec4, rec5;
    private JTextField lyricsTextField;
    



    /**
     * Initializes and sets temporary text/fonts to all the labels/buttons.
     * Also adds functionality to the 'enter lyrics' button.
     */
    public Reccomendations() {
        super();
        
        enterLyricsButton = new JButton("Enter Lyrics");
        songNameLabel = new JLabel("song name:", SwingConstants.CENTER);
        titleLabel = new JLabel("Shake it Off", SwingConstants.CENTER);
        recLabel = new JLabel("recomendations:", SwingConstants.CENTER);
        rec1 = new JLabel("B1eautiful People (feat. Khalid) by Ed Sheeran", SwingConstants.CENTER);
        rec2 = new JLabel("B2eautiful People (feat. Khalid) by Ed Sheeran", SwingConstants.CENTER);
        rec3 = new JLabel("B3eautiful People (feat. Khalid) by Ed Sheeran", SwingConstants.CENTER);
        rec4 = new JLabel("B4eautiful People (feat. Khalid) by Ed Sheeran", SwingConstants.CENTER);
        rec5 = new JLabel("B5eautiful People (feat. Khalid) by Ed Sheeran", SwingConstants.CENTER);

        enterLyricsButton.setFont(new Font("Arial", Font.BOLD, 25));
        songNameLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        recLabel.setFont(new Font("Arial", Font.BOLD, 35));
        rec1.setFont(new Font("Arial", Font.BOLD, 40));
        rec2.setFont(new Font("Arial", Font.BOLD, 40));
        rec3.setFont(new Font("Arial", Font.BOLD, 40));
        rec4.setFont(new Font("Arial", Font.BOLD, 40));
        rec5.setFont(new Font("Arial", Font.BOLD, 40));

        


        lyricsTextField = new JTextField(20);

        enterLyricsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeBottomElements();
                artist = null;

                String userInput = JOptionPane.showInputDialog("What taylor song are you thinking of?", "Enter lyrics:");
                lyricsTextField.setText(userInput);
                stringInput = lyricsTextField.getText(); 
                displayRecs();
            }
        });


        

        



    }

    

    /**
     * Adds all the labels and buttons onto the frame and sets the labels to
     * not currently be visible.
     */
    public void displayInitial() {
        super.add(enterLyricsButton, 400, 40, 200, 50);
        super.add(songNameLabel, 10, 100, 980, 75);
        super.add(titleLabel, 10, 150, 980, 100);
        super.add(recLabel, 10, 300, 980, 75);
        super.add(rec1, 10, 375, 980, 50 );
        super.add(rec2, 10, 435, 980, 50 );
        super.add(rec3, 10, 495, 980, 50 );
        super.add(rec4, 10, 555, 980, 50 );
        super.add(rec5, 10, 615, 980, 50 );
        removeBottomElements();

    }

    /**
     * Checks if the song inputted is by Taylor- if so displays recommendations, if not says that song is not by Taylor.
     * If no song is able to be detected, it says no song detected.
     * 
     */
    public void displayRecs() {
        parsedID(stringInput);
        if (getArtist() == null) {
            songNameLabel.setText("No song detected, please try again.");
            songNameLabel.setVisible(true);
        }
        else if (getArtist().equals("Taylor Swift")) {

            boolean works = getReccomendations();

            songNameLabel.setText("song name:");
            titleLabel.setText(title);

            songNameLabel.setVisible(true);
            titleLabel.setVisible(true);
            recLabel.setVisible(true);


            if (works) {    
                recLabel.setText("recommendations:");
                
            }
            else {
                recLabel.setText("sorry - no recs available for this song");
            }


            



        }
        else {
            songNameLabel.setText(title + " is not by Taylor Swift,");
            songNameLabel.setVisible(true);

            titleLabel.setText("please try again.");
            titleLabel.setVisible(true);
        }

    }


    /**
     * Gets the song ID from the the user input and
     * gets the songID, title, and artist from the API.
     * 
     * @param rawInput user input
     */
    public void parsedID(String rawInput) {


        String JSONString = getSongInfo(rawInput);

        JSONObject json = new JSONObject(JSONString);

        if (json.has("tracks")) {
            JSONObject tracksObject = json.getJSONObject("tracks");

            if (tracksObject.has("hits")) {
                JSONArray hitsArray = tracksObject.getJSONArray("hits");

                if (hitsArray.length() > 0) {
                    JSONObject firstHitObject = hitsArray.getJSONObject(0);

                    if (firstHitObject.has("track")) {
                        JSONObject trackObject = firstHitObject.getJSONObject("track");

                        if (trackObject.has("key")) {
                            songID = trackObject.getInt("key");
                            
                            
                        }
                        if (trackObject.has("title")) {
                            title = trackObject.getString("title");
                            
                        }

                        if (trackObject.has("subtitle")) {
                            artist = trackObject.getString("subtitle");
                            
                        }
                        return;
                    }
                }
            }
        }

        System.out.println("no songs");




        
    }
        
    /**
     * Gets the song name and info about the song from the Shazam API.
     * @param rawInput user input
     * @return The body of json response containing song info.
     */
    public String getSongInfo(String rawInput) {
    
        try {
            HttpResponse<String> response = Unirest.get(convertToURL(rawInput))
            .header("X-RapidAPI-Key", API_KEY)
            .header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
            .asString();

            int status = response.getStatus();
            System.out.println("Response status is: " + status);

            String body = response.getBody();
            // System.out.println("Body is: " + body);
            return body;
        }
        
        catch (UnirestException e) {
            e.printStackTrace();
        }

        System.out.println("FAILED");
        return "failed";
    }

    /**
     * Converts the user input into a url format
     * @param rawInput user input
     * @return user input in requested format of API
     */
    public String convertToURL(String rawInput) {
       
        String encodedInput = "";
        try {
            encodedInput = URLEncoder.encode(rawInput, "UTF-8");
        } 
        
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();   
        }
    
        String url = "https://shazam.p.rapidapi.com/search?term=" + encodedInput + "&locale=en-US&offset=0&limit=5";

        return url;
    }


    /**
     * Gets and displays song recs from the API.
     * 
     * @return true if recommendations can be found
     * false if otherwise
     */
    public boolean getReccomendations() {
        boolean works = false;
        try {
            HttpResponse<String> response = Unirest.get("https://shazam.p.rapidapi.com/songs/list-recommendations?key=" + songID + "&locale=en-US")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
                .asString();

            
            int status = response.getStatus();
            System.out.println("Response status is: " + status);

            String body = response.getBody();
            // System.out.println("Body is: " + body);
            
            JSONObject json = new JSONObject(body);



            if (json.has("tracks")) {

                JSONArray tracksArray = json.getJSONArray("tracks");

                for (int i = 0; i < Math.min(tracksArray.length(), 5); i++) {

                    JSONObject trackObject = tracksArray.getJSONObject(i);


                    if (trackObject.has("share")) {
                        JSONObject shareObject = trackObject.getJSONObject("share");
                        String text = shareObject.getString("text");

                        System.out.println("rec - " + text);

                        if (i == 0) {
                            rec1.setText(text);
                            rec1.setVisible(true);
                        }
                        else if (i == 1) {
                            rec2.setText(text);
                            rec2.setVisible(true);
                        }
                        else if (i == 2) {
                            rec3.setText(text);
                            rec3.setVisible(true);
                        }
                        else if (i == 3) {
                            rec4.setText(text);
                            rec4.setVisible(true);
                        }
                        else if (i == 4) {
                            rec5.setText(text);
                            rec5.setVisible(true);
                        }
                        works = true;


                    }
                    
                }
            }

        } 
        
        catch (UnirestException e) {
            e.printStackTrace();
        }


        return works;
    }


    







    /**
     * Returns the artist of the song.
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }


    /**
     * Sets all the labels to be invisible.
     */
    public void removeBottomElements() {
        songNameLabel.setVisible(false);
        titleLabel.setVisible(false);
        recLabel.setVisible(false);
        rec1.setVisible(false);
        rec2.setVisible(false);
        rec3.setVisible(false);
        rec4.setVisible(false);
        rec5.setVisible(false);
    }



    


}


