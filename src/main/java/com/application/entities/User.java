package com.application.entities;

import java.io.Serializable;

/**
 * Represents a user entity with a username and password.
 * This record class encapsulates user information for authentication purposes.
 *
 * @param username The username associated with the user.
 * @param password The password associated with the user.
 */
public record User(String username, String password) implements Serializable {
}
