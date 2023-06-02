import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.sound.sampled.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;


public class Reccomendations extends MyJFrame {
    
    private static final String API_KEY = ApiKey.APIKEY();
    private int songID;
    private String title, artist;
    private String stringInput;
    private JButton enterLyricsButton;
    private JLabel songNameLabel, titleLabel, recLabel, rec1, rec2, rec3, rec4, rec5;
    private JTextField lyricsTextField;
    private JFrame frame;
    



    public Reccomendations() {
        super();
        frame = super.frame();
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

                String userInput = JOptionPane.showInputDialog(Reccomendations.this, "Enter lyrics:");
                lyricsTextField.setText(userInput);
                stringInput = lyricsTextField.getText(); 
                displayRecs();
            }
        });


        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }

        });

        



    }

    //gets the song (search endpoint)

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

                        if (artist.equals("Taylor Swift")) {
                            System.out.println("Key: " + songID);
                            System.out.println("Title: " + title);
                            System.out.println("Artist: " + artist);
                        }
                        else {
                           System.out.println(title + " IS NOT BY TAYLOR >:(" );
                        }
                        return;
                    }
                }
            }
        }

        System.out.println("no songs");




        
    }
        
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


    







    public String getArtist() {
        return artist;
    }


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


    //gets the reccomendations (list-recommendations endpoint)

    public static void main(String[] args) {
        Reccomendations rec = new Reccomendations();
       
        rec.displayInitial();
        rec.setVisible();
        
    }


}


