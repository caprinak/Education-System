package io.satori.syllabus.application.controller.level;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.satori.syllabus.application.command.level.ArchiveLevelCommand;
import io.satori.syllabus.application.command.level.CreateLevelCommand;
import io.satori.syllabus.application.command.level.UpdateLevelCommand;
import io.satori.syllabus.application.handler.level.LevelCommandHandler;

@RestController
@RequestMapping("/levels")
@RequiredArgsConstructor
public class LevelCommandController {

    private final LevelCommandHandler levelCommandHandler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void createLevel(@Valid @RequestBody CreateLevelCommand command) {
        levelCommandHandler.handle(command);
    }

    @PutMapping
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public void updateLevel(@Valid @RequestBody UpdateLevelCommand command) {
        levelCommandHandler.handle(command);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Secured({"DIRECTOR", "ADMIN"})
    public void archiveById(@PathVariable("id") Long id) {
        levelCommandHandler.handle(new ArchiveLevelCommand(id));
    }
}
