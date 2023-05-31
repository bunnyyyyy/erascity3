import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.sound.sampled.*;
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


public class Reccomendations {
    
    private static final String API_KEY = ApiKey.APIKEY();
    private int songID;
    private String title, artist;



    //gets the song (search endpoint)


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


    public void getReccomendations() {
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
                        System.out.println("Rec: " + text);
                    }
                    
                }
            }

        } 
        
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }





    public String getArtist() {
        return artist;
    }



    //gets the reccomendations (list-recommendations endpoint)

    public static void main(String[] args) {
        Reccomendations rec = new Reccomendations();
        rec.parsedID("i was finally clean ");
        if (rec.getArtist() == null) {
            
        }
        if (rec.getArtist().equals("Taylor Swift")) {
            rec.getReccomendations();
        }
        else {
            System.out.println("not tayorajsdkll");
        }
        
    }


}


