package com.gym.auth.exception;

public class AccountDeactivatedException extends RuntimeException {
    public AccountDeactivatedException() {
        super("This account has been deactivated");
    }
}