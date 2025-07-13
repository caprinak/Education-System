package io.satori.syllabus.domain.adapter.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.handler.schoolclass.SchoolClassQueryHandler;
import io.satori.syllabus.application.query.schoolclass.GetActiveSchoolClassesQuery;
import io.satori.syllabus.application.query.schoolclass.GetArchivedSchoolClassesQuery;
import io.satori.syllabus.application.query.schoolclass.GetSchoolClassByIdQuery;
import io.satori.syllabus.domain.model.SchoolClass;
import io.satori.syllabus.domain.service.schoolclass.SchoolClassQueryService;

@RequiredArgsConstructor
@Component
public class SchoolClassQueryHandlerImpl implements SchoolClassQueryHandler {

    private final SchoolClassQueryService schoolClassQueryService;

    @Override
    public Page<SchoolClass> handle(GetActiveSchoolClassesQuery query) {
        return schoolClassQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<SchoolClass> handle(GetArchivedSchoolClassesQuery query) {
        return schoolClassQueryService.getAllArchived(query.pageable());
    }

    @Override
    public SchoolClass handle(GetSchoolClassByIdQuery query) {
        return schoolClassQueryService.getById(query.id());
    }
}
