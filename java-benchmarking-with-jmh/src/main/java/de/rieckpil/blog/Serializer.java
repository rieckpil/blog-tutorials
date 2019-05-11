package de.rieckpil.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;

public class Serializer {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String userJsonString = "{\"firstName\": \"Mike\", \"lastName\":\"Duke\", \"hobbies\": [{\"name\": \"Soccer\", " +
                "\"tags\": [\"Teamsport\", \"Ball\", \"Outdoor\", \"Championship\"]}], \"address\":" +
                " { \"street\": \"Mainstreet\", \"streetNumber\": \"1A\", \"city\": \"New York\", \"country\":\"USA\", " +
                "\"postalCode\": 1337}}";

        User user = objectMapper.readValue(userJsonString, User.class);

        System.out.println(user);

        Gson gson = new Gson();

        User userFromGson = gson.fromJson(userJsonString, User.class);

        System.out.println(userFromGson);


    }
}
