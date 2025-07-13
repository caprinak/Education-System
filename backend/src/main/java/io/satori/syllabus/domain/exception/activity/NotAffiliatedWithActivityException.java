package io.satori.syllabus.domain.exception.activity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.satori.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAffiliatedWithActivityException extends SyllabusException {
}
