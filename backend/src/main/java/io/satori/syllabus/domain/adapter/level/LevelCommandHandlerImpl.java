package io.satori.syllabus.domain.adapter.level;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.command.level.ArchiveLevelCommand;
import io.satori.syllabus.application.command.level.CreateLevelCommand;
import io.satori.syllabus.application.command.level.UpdateLevelCommand;
import io.satori.syllabus.application.handler.level.LevelCommandHandler;
import io.satori.syllabus.domain.service.level.LevelCommandService;

@RequiredArgsConstructor
@Component
public class LevelCommandHandlerImpl implements LevelCommandHandler {

    private final LevelCommandService levelCommandService;

    @Override
    public void handle(CreateLevelCommand command) {
        levelCommandService.create(command);
    }

    @Override
    public void handle(UpdateLevelCommand command) {
        levelCommandService.update(command);
    }

    @Override
    public void handle(ArchiveLevelCommand command) {
        levelCommandService.archiveById(command);
    }

}
