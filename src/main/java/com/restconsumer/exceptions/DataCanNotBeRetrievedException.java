package com.restconsumer.exceptions;

public class DataCanNotBeRetrievedException extends RuntimeException {
        private static final String MESSAGE = "Data can not be retrieved.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
