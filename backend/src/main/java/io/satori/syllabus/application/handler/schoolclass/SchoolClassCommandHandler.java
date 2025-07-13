package io.satori.syllabus.application.handler.schoolclass;

import io.satori.syllabus.application.command.schoolclass.ArchiveSchoolClassCommand;
import io.satori.syllabus.application.command.schoolclass.CreateSchoolClassCommand;
import io.satori.syllabus.application.command.schoolclass.UpdateSchoolClassCommand;

public interface SchoolClassCommandHandler {
    void handle(CreateSchoolClassCommand command);

    void handle(UpdateSchoolClassCommand command);

    void handle(ArchiveSchoolClassCommand command);
}
