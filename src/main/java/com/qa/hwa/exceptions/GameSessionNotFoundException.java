package com.qa.hwa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// I changed from EntityNotFound to FileNotFound
// import javax.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The game session doesn't exist")
public class GameSessionNotFoundException extends FileNotFoundException {
}
