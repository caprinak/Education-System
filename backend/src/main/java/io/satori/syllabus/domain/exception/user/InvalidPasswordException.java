package io.satori.syllabus.domain.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.satori.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPasswordException extends SyllabusException {
}
