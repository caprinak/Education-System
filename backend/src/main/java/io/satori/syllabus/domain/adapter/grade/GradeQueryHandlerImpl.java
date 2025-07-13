package io.satori.syllabus.domain.adapter.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.dto.GradeDTO;
import io.satori.syllabus.application.handler.grade.GradeQueryHandler;
import io.satori.syllabus.application.query.grade.GetActiveGradesByStudentQuery;
import io.satori.syllabus.application.query.grade.GetArchivedGradesByStudentQuery;
import io.satori.syllabus.application.query.grade.GetGradeByActivityAndStudentQuery;
import io.satori.syllabus.application.query.grade.GetGradeByIdQuery;
import io.satori.syllabus.application.query.grade.GetOwnGradesByRealisationQuery;
import io.satori.syllabus.application.query.grade.GetRecentGradesQuery;
import io.satori.syllabus.domain.model.Grade;
import io.satori.syllabus.domain.service.grade.GradeQueryService;

@RequiredArgsConstructor
@Component
public class GradeQueryHandlerImpl implements GradeQueryHandler {

    private final GradeQueryService gradeQueryService;

    @Override
    public Page<Grade> handle(GetActiveGradesByStudentQuery query) {
        return gradeQueryService.getAllActiveByStudent(query.pageable());
    }

    @Override
    public Page<Grade> handle(GetArchivedGradesByStudentQuery query) {
        return gradeQueryService.getAllArchivedByStudent(query.studentId(), query.pageable());
    }

    @Override
    public Grade handle(GetGradeByActivityAndStudentQuery query) {
        return gradeQueryService.getByActivityAndStudent(query.activityId(), query.studentId());
    }

    @Override
    public Grade handle(GetGradeByIdQuery query) {
        return gradeQueryService.getById(query.id());
    }

    @Override
    public Page<GradeDTO> handle(GetOwnGradesByRealisationQuery query) {
        return gradeQueryService.getOwnGradesByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Page<GradeDTO> handle(GetRecentGradesQuery query) {
        return gradeQueryService.getAllActiveByStudent(query.pageable())
                .map(grade -> new GradeDTO(grade.getActivity(), grade));
    }
}
