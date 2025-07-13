package io.satori.syllabus.domain.exception.realisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.satori.syllabus.domain.exception.SyllabusException;

@ResponseStatus(HttpStatus.GONE)
public class RealisationArchivedException extends SyllabusException {
}
