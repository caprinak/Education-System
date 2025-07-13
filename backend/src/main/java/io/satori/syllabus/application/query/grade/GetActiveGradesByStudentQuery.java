package io.satori.syllabus.application.query.grade;


import org.springframework.data.domain.Pageable;

public record GetActiveGradesByStudentQuery(Long studentId, Pageable pageable) {
}
