package io.satori.syllabus.domain.exception.subject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.satori.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SubjectUpdateException extends SyllabusException {
}
