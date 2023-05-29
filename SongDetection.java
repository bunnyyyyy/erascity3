import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SongDetection {
    private static final String FILE_PATH = "/Users/nmunjal/Downloads/erascity/clinteastwood_portion_mono.raw";
    private static final String API_KEY = ApiKey.APIKEY();

    public String convertRaw() throws IOException {

        Path path = Paths.get(FILE_PATH);
        byte[] byteArray = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(byteArray);

    }

    public void getSong() throws IOException {
        String fileStr = convertRaw();

        try {
            HttpResponse<String> response = Unirest.post("https://shazam.p.rapidapi.com/songs/v2/detect?timezone=America%2FChicago&locale=en-US")
                    .header("content-type", "text/plain")
                    .header("X-RapidAPI-Key", API_KEY)
                    .header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
                    .body(fileStr)
                    .asString();

            int status = response.getStatus();
            System.out.println("Response status is: " + status);

            String body = response.getBody();
            System.out.println("Body is: " + body);


        } 
        
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SongDetection test = new SongDetection();
        test.getSong();
    }
}
