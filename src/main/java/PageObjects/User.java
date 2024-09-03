package PageObjects;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    protected  String firstName;
    protected  String lastName;
    protected String email;
    protected  String password;
    protected  String phoneNumber;
    protected  String gender;
    protected final String[] occupationsRoles = {"Doctor", "Student", "Engineer", "Scientist"};
    protected String occupationValue;
    protected Random random = new Random();


    public User(){
        userSetUp();
        this.gender = random.nextBoolean() ? "Male" : "Female";
    }

    private void userSetUp(){
        HttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://randomuser.me/api/");
        JSONObject responseData = null;

        try {
            HttpResponse response = client.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseBody = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            responseData = new JSONObject(responseBody.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        JSONObject user = responseData.getJSONArray("results").getJSONObject(0);
        String originalFirstName = user.getJSONObject("name").getString("first");
        String originalLastName = user.getJSONObject("name").getString("last");

        firstName = filterEuropeanCharacters(originalFirstName);
        lastName = filterEuropeanCharacters(originalLastName);

        password = "Sp123iunwc";
        phoneNumber = "7007007001";
        occupationValue = occupationsRoles[random.nextInt(occupationsRoles.length)];
    }

    private String filterEuropeanCharacters(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InBasicLatin}|\\s");
        Matcher matcher = pattern.matcher(normalized);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            builder.append(matcher.group());
        }
        return builder.toString();
    }
}
