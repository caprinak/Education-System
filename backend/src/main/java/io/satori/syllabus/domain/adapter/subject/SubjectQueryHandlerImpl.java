package io.satori.syllabus.domain.adapter.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.handler.subject.SubjectQueryHandler;
import io.satori.syllabus.application.query.subject.GetActiveSubjectsQuery;
import io.satori.syllabus.application.query.subject.GetArchivedSubjectsQuery;
import io.satori.syllabus.application.query.subject.GetSubjectByIdQuery;
import io.satori.syllabus.application.query.subject.SearchSubjectByNameQuery;
import io.satori.syllabus.domain.model.Subject;
import io.satori.syllabus.domain.service.subject.SubjectQueryService;

@RequiredArgsConstructor
@Component
public class SubjectQueryHandlerImpl implements SubjectQueryHandler {

    private final SubjectQueryService subjectQueryService;

    @Override
    public Page<Subject> handle(GetActiveSubjectsQuery query) {
        return subjectQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<Subject> handle(GetArchivedSubjectsQuery query) {
        return subjectQueryService.getAllArchived(query.pageable());
    }

    @Override
    public Page<Subject> handle(SearchSubjectByNameQuery query) {
        return subjectQueryService.getByNameContaining(query.name(), query.pageable());
    }

    @Override
    public Subject handle(GetSubjectByIdQuery query) {
        return subjectQueryService.getById(query.id());
    }
}
