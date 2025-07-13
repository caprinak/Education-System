package io.satori.syllabus.application.handler.level;

import io.satori.syllabus.application.command.level.ArchiveLevelCommand;
import io.satori.syllabus.application.command.level.CreateLevelCommand;
import io.satori.syllabus.application.command.level.UpdateLevelCommand;

public interface LevelCommandHandler {
    void handle(CreateLevelCommand command);

    void handle(UpdateLevelCommand command);

    void handle(ArchiveLevelCommand command);
}
