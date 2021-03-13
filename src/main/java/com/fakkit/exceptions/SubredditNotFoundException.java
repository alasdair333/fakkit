package com.fakkit.exceptions;

public class SubredditNotFoundException extends RuntimeException {
    public SubredditNotFoundException(String subredditName) {
        super("Unable to find: " + subredditName);
    }
}
