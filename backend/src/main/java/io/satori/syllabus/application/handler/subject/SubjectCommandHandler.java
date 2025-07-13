package io.satori.syllabus.application.handler.subject;

import io.satori.syllabus.application.command.subject.ArchiveSubjectCommand;
import io.satori.syllabus.application.command.subject.CreateSubjectCommand;
import io.satori.syllabus.application.command.subject.UpdateSubjectCommand;
import io.satori.syllabus.application.command.subject.UpdateSubjectImageCommand;

public interface SubjectCommandHandler {
    void handle(CreateSubjectCommand command);

    void handle(UpdateSubjectCommand command);

    void handle(ArchiveSubjectCommand command);

    void handle(UpdateSubjectImageCommand command);
}
