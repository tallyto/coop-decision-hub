package com.tallyto.sicred.coopdecisionhub.exception;

public class VoterAlreadyVotedException extends ValidationException  {
    public VoterAlreadyVotedException(String message) {
        super(message);
    }

    public VoterAlreadyVotedException(String message, Throwable cause) {
        super(message, cause);
    }
}
