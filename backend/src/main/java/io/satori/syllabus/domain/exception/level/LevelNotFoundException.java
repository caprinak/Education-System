package io.satori.syllabus.domain.exception.level;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.satori.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LevelNotFoundException extends SyllabusException {
}
