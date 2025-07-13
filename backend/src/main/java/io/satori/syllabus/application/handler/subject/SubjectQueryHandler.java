package io.satori.syllabus.application.handler.subject;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.query.subject.GetActiveSubjectsQuery;
import io.satori.syllabus.application.query.subject.GetArchivedSubjectsQuery;
import io.satori.syllabus.application.query.subject.GetSubjectByIdQuery;
import io.satori.syllabus.application.query.subject.SearchSubjectByNameQuery;
import io.satori.syllabus.domain.model.Subject;

public interface SubjectQueryHandler {
    Page<Subject> handle(GetActiveSubjectsQuery query);

    Page<Subject> handle(GetArchivedSubjectsQuery query);

    Page<Subject> handle(SearchSubjectByNameQuery query);

    Subject handle(GetSubjectByIdQuery query);

}
