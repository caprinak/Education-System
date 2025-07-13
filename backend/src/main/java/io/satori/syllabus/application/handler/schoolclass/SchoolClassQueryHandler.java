package io.satori.syllabus.application.handler.schoolclass;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.query.schoolclass.GetActiveSchoolClassesQuery;
import io.satori.syllabus.application.query.schoolclass.GetArchivedSchoolClassesQuery;
import io.satori.syllabus.application.query.schoolclass.GetSchoolClassByIdQuery;
import io.satori.syllabus.domain.model.SchoolClass;

public interface SchoolClassQueryHandler {
    Page<SchoolClass> handle(GetActiveSchoolClassesQuery query);

    Page<SchoolClass> handle(GetArchivedSchoolClassesQuery query);

    SchoolClass handle(GetSchoolClassByIdQuery query);
}
