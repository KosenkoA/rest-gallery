package com.restconsumer.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Item <%s> not found.";

    private final Object id;

    @Override
    public String getMessage() {
        return String.format(MESSAGE,  getId());
    }
}
