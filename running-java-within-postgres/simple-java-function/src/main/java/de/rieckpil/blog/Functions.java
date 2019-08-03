package de.rieckpil.blog;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.postgresql.pljava.annotation.Function;

public class Functions {

    @Function
    public static String greet(String personName) {
        return "Hello World, " + personName + " !";
    }

    @Function(name = "split_string_by_delimiter")
    public static Iterator<String> splitStringByDelimiter(String tagString, String delimiter) {

        if (delimiter == null || delimiter.isEmpty()) {
            delimiter = ">";
        }

        List<String> tags = new ArrayList<String>();

        for (String currentTag : tagString.split(delimiter)) {
            tags.add(currentTag.trim());
        }

        return tags.iterator();
    }

    @Function(name = "get_oldest_person")
    public static String getOldestPerson() throws SQLException {

        try (Statement statement = DriverManager //
                .getConnection("jdbc:default:connection") //
                .createStatement(); //
             ResultSet resultSet = statement.executeQuery("SELECT * FROM persons")) {

            List<Person> personList = new ArrayList<>();

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setLastName(resultSet.getString("last_name"));
                person.setDayOfBirth(resultSet.getDate("day_of_birth").toLocalDate());
                personList.add(person);
            }

            Collections.sort(personList);
            Person oldestPerson = personList.get(0);

            return String.format("The oldest person is %s, %s with %s years!", oldestPerson.getFirstName(),
                    oldestPerson.getLastName(), Period.between(oldestPerson.getDayOfBirth(), LocalDate.now()).getYears());
        }
    }
}