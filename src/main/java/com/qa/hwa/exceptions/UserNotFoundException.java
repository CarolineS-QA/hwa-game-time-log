package com.qa.hwa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// changed from EntityNotFound to FileNotFound
// import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The user doesn't exist")
public class UserNotFoundException extends FileNotFoundException {
}
