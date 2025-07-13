package io.satori.syllabus.domain.adapter.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.command.realisation.ArchiveRealisationCommand;
import io.satori.syllabus.application.command.realisation.CreateRealisationCommand;
import io.satori.syllabus.application.command.realisation.UpdateRealisationCommand;
import io.satori.syllabus.application.handler.realisation.RealisationCommandHandler;
import io.satori.syllabus.domain.service.realisation.RealisationCommandService;

@RequiredArgsConstructor
@Component
public class RealisationCommandHandlerImpl implements RealisationCommandHandler {

    private final RealisationCommandService realisationCommandService;

    @Override
    public void handle(CreateRealisationCommand command) {
        realisationCommandService.create(command);
    }

    @Override
    public void handle(UpdateRealisationCommand command) {
        realisationCommandService.update(command);
    }

    @Override
    public void handle(ArchiveRealisationCommand command) {
        realisationCommandService.archiveById(command);
    }

}
