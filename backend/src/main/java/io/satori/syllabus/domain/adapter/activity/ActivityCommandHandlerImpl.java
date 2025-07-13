package io.satori.syllabus.domain.adapter.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.command.activity.ArchiveActivityCommand;
import io.satori.syllabus.application.command.activity.CreateActivityCommand;
import io.satori.syllabus.application.command.activity.UpdateActivityCommand;
import io.satori.syllabus.application.handler.activity.ActivityCommandHandler;
import io.satori.syllabus.domain.service.activity.ActivityCommandService;

@RequiredArgsConstructor
@Component
public class ActivityCommandHandlerImpl implements ActivityCommandHandler {

    private final ActivityCommandService activityCommandService;

    @Override
    public void handle(CreateActivityCommand command) {
        activityCommandService.create(command);
    }

    @Override
    public void handle(UpdateActivityCommand command) {
        activityCommandService.update(command);
    }

    @Override
    public void handle(ArchiveActivityCommand command) {
        activityCommandService.archiveById(command);
    }

}
