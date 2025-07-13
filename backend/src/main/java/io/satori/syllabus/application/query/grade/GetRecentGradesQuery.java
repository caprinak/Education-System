package io.satori.syllabus.application.query.grade;


import org.springframework.data.domain.Pageable;

public record GetRecentGradesQuery(Pageable pageable) {
}
