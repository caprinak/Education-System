package io.satori.syllabus.application.query.schoolclass;


import org.springframework.data.domain.Pageable;

public record GetArchivedSchoolClassesQuery(Pageable pageable) {
}
