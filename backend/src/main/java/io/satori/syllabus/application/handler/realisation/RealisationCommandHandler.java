package io.satori.syllabus.application.handler.realisation;

import io.satori.syllabus.application.command.realisation.ArchiveRealisationCommand;
import io.satori.syllabus.application.command.realisation.CreateRealisationCommand;
import io.satori.syllabus.application.command.realisation.UpdateRealisationCommand;

public interface RealisationCommandHandler {
    void handle(CreateRealisationCommand command);

    void handle(UpdateRealisationCommand command);

    void handle(ArchiveRealisationCommand command);
}
