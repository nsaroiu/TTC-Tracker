package com.example.backend.data_access;

public class InvalidRequestException extends Exception {

    /** An exception thrown when the API returns an error message.
     *
     * @param message Error message received from the API
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}
