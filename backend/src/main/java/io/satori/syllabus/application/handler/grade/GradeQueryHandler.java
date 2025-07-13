package io.satori.syllabus.application.handler.grade;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.dto.GradeDTO;
import io.satori.syllabus.application.query.grade.GetActiveGradesByStudentQuery;
import io.satori.syllabus.application.query.grade.GetArchivedGradesByStudentQuery;
import io.satori.syllabus.application.query.grade.GetGradeByActivityAndStudentQuery;
import io.satori.syllabus.application.query.grade.GetGradeByIdQuery;
import io.satori.syllabus.application.query.grade.GetOwnGradesByRealisationQuery;
import io.satori.syllabus.application.query.grade.GetRecentGradesQuery;
import io.satori.syllabus.domain.model.Grade;

public interface GradeQueryHandler {
    Page<Grade> handle(GetActiveGradesByStudentQuery query);

    Page<Grade> handle(GetArchivedGradesByStudentQuery query);

    Grade handle(GetGradeByActivityAndStudentQuery query);

    Grade handle(GetGradeByIdQuery query);

    Page<GradeDTO> handle(GetOwnGradesByRealisationQuery getOwnGradesByRealisationQuery);

    Page<GradeDTO> handle(GetRecentGradesQuery getRecentGradesQuery);
}
