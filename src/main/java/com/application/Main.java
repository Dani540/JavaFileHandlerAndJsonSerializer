package com.application;

import com.application.entities.User;
import com.application.utilities.FileManagement;
import com.application.utilities.Serializer;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Entry point of the application.
 * This class demonstrates the usage of the Application class by creating users,
 * serializing them, and saving the serialized data to a file.
 */
public class Main {

    /**
     * The main method that serves as the entry point of the application.
     * It demonstrates the usage of the Application class by creating users,
     * serializing them, and saving the serialized data to a file.
     *
     * @param args The command-line arguments (unused in this context).
     */
    public static void main(String[] args) {

        // Create an Application instance with necessary dependencies
        Application application = new Application(new FileManagement(), new Serializer<>());

        // Create a list of users
        List<User> myUsers = application.createUsers();

        // Serialize and save the list of users to a file
        boolean myUserSerialized = application.saveUsers(myUsers, "data/example.txt");

        // Deserialize the list of users from the file
        List<User> againMyUsers = application.getUsers(
                new FileManagement().readAllLines("data/example.txt")
        );

        // Print the list of users, serialization status, and deserialized users
        System.out.println("\nList of users: \n" + myUsers);
        System.out.println("\nUser are serialized?: \n" + myUserSerialized);
        System.out.println("\nList of serialized-user deserialized: \n" + againMyUsers);

        // Compare deserialized users with original users based on instance and hashcode equality
        System.out.println( "\nUsers deserialized are same of user previously serialized? (based in instance and hashcode): \n" +
                IntStream.range(0, myUsers.size())
                        .allMatch(i->
                                myUsers.get(i).hashCode()==againMyUsers.get(i).hashCode() &&
                                myUsers.get(i).equals(againMyUsers.get(i))
                        )
        );
    }
}
