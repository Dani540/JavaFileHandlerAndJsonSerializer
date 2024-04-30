package com.application;

import com.application.data.imp.JsonHandler;
import com.application.entities.User;
import com.application.data.out.IFileHandler;
import com.application.utilities.PasswordGenerator;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Manages user-related operations and data using provided utilities.
 * This class facilitates the creation, serialization, and management of user entities.
 * <p>
 * This class use an implementation of IFileHandler used for file operations.
 * This class use a jsonHandler implementation for handling user object serialization.
 */
public class Application {
    private final int DEFAULT_USER_LIST_SIZE = 12;
    private final IFileHandler fileManagement;
    private final JsonHandler<User> jsonHandler;

    /**
     * Constructs an Application instance with the given dependencies.
     *
     * @param fileManagement An implementation of IFileHandler used for file operations.
     * @param jsonHandler A jsonHandler implementation for handling user object serialization.
     */
    public Application(IFileHandler fileManagement, JsonHandler<User> jsonHandler) {
        this.fileManagement = fileManagement;
        this.jsonHandler = jsonHandler;
    }

    /**
     * Creates a list of default users with generated usernames and passwords.
     *
     * @return A list of User instances with default usernames and passwords.
     */
    public List<User> createUsers(){
        return createUsers(DEFAULT_USER_LIST_SIZE);
    }

    /**
     * Creates a list of users with generated usernames and passwords, limited by the provided bound.
     *
     * @param bound The number of users to create.
     * @return A list of User instances with generated usernames and passwords.
     */
    public List<User> createUsers(int bound){
        PasswordGenerator passwordGenerator = new PasswordGenerator();

        return IntStream.range(0, bound)
                .mapToObj(i -> new User("User" + (i+1), passwordGenerator.generate()))
                .toList();
    }

    /**
     * Serializes a list of User objects into a list of serialized JSON strings.
     *
     * @param users The list of User objects to be serialized.
     * @return A list of serialized JSON strings representing the users.
     */
    public List<String> saveUsers(List<User> users){
        return users.stream().map(jsonHandler::serialize).toList();
    }

    /**
     * Serializes and saves a list of User objects to a specified file path.
     *
     * @param users The list of User objects to be serialized and saved.
     * @param path The path to the file where the serialized data will be saved.
     * @return true if all users were successfully serialized and saved, false otherwise.
     */
    public boolean saveUsers(List<User> users, String path){
        return users.stream().allMatch(user -> fileManagement.writeLine(path, jsonHandler.serialize(user)));
    }

    /**
     * Deserializes a list of JSON strings representing User objects.
     *
     * @param jsonLog The list of serialized JSON strings to be deserialized.
     * @return A list of User instances reconstructed from the serialized data.
     */
    public List<User> getUsers(List<String> jsonLog){
        return jsonLog.stream()
                .map(json -> jsonHandler.deserialize(json, User.class))
                .toList();
    }
}
