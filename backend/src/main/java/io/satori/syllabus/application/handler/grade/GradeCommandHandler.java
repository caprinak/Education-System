package io.satori.syllabus.application.handler.grade;

import io.satori.syllabus.application.command.grade.ArchiveGradeCommand;
import io.satori.syllabus.application.command.grade.CreateGradeCommand;
import io.satori.syllabus.application.command.grade.UpdateGradeCommand;

public interface GradeCommandHandler {
    void handle(CreateGradeCommand command);

    void handle(UpdateGradeCommand command);

    void handle(ArchiveGradeCommand command);
}
