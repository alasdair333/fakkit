package com.fakkit.exceptions;

import org.springframework.mail.MailException;

public class FakkitException extends RuntimeException {
    public FakkitException(String s, Exception e) {
        super(s, e);
    }

    public FakkitException(String exMessage) {
        super(exMessage);
    }
}
