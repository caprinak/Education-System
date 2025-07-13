package io.satori.syllabus.application.handler.activity;

import io.satori.syllabus.application.command.activity.ArchiveActivityCommand;
import io.satori.syllabus.application.command.activity.CreateActivityCommand;
import io.satori.syllabus.application.command.activity.UpdateActivityCommand;

public interface ActivityCommandHandler {
    void handle(CreateActivityCommand command);

    void handle(UpdateActivityCommand command);

    void handle(ArchiveActivityCommand command);
}
