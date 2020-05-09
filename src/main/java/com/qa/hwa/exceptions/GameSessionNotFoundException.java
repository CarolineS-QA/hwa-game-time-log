package com.qa.hwa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The game session doesn't exist")
public class GameSessionNotFoundException extends EntityNotFoundException {
}
