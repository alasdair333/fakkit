package com.fakkit.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String s) {
        super("Cannot find post: " + s);
    }
}
