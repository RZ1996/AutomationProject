package PageObjects;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

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

    public void userSetUp(){
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
        firstName = user.getJSONObject("name").getString("first");
        lastName = user.getJSONObject("name").getString("last");
        password = "Sp123iunwc";
        phoneNumber = "7007007001";
        occupationValue = occupationsRoles[random.nextInt(occupationsRoles.length)];
    }


}
