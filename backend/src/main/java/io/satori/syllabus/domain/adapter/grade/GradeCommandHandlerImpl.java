package io.satori.syllabus.domain.adapter.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.command.grade.ArchiveGradeCommand;
import io.satori.syllabus.application.command.grade.CreateGradeCommand;
import io.satori.syllabus.application.command.grade.UpdateGradeCommand;
import io.satori.syllabus.application.handler.grade.GradeCommandHandler;
import io.satori.syllabus.domain.service.grade.GradeCommandService;

@RequiredArgsConstructor
@Component
public class GradeCommandHandlerImpl implements GradeCommandHandler {

    private final GradeCommandService gradeCommandService;

    @Override
    public void handle(CreateGradeCommand command) {
        gradeCommandService.create(command);
    }

    @Override
    public void handle(UpdateGradeCommand command) {
        gradeCommandService.update(command);
    }

    @Override
    public void handle(ArchiveGradeCommand command) {
        gradeCommandService.archiveById(command);
    }

}
