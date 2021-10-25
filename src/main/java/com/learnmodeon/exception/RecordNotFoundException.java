package com.learnmodeon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NO_CONTENT,
        reason = "Record does not exist"
)
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super();
    }
}
