package io.satori.syllabus.domain.service.level;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import io.satori.syllabus.application.command.level.ArchiveLevelCommand;
import io.satori.syllabus.application.command.level.CreateLevelCommand;
import io.satori.syllabus.application.command.level.UpdateLevelCommand;
import io.satori.syllabus.domain.exception.level.ActiveSchoolClassesException;
import io.satori.syllabus.domain.exception.level.LevelAlreadyExistsException;
import io.satori.syllabus.domain.exception.level.LevelNotFoundException;
import io.satori.syllabus.domain.model.Level;
import io.satori.syllabus.infrastructure.repository.LevelRepository;
import io.satori.syllabus.infrastructure.repository.SchoolClassRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class LevelCommandService {

    private final LevelRepository levelRepository;
    private final SchoolClassRepository schoolClassRepository;


    public void create(CreateLevelCommand command) {
        Level level = new Level();
        level.setValue(command.getLevel());

        if (levelRepository.findByArchivedAndValue(false, level.getValue()).isPresent()) {
            throw new LevelAlreadyExistsException();
        }
        levelRepository.save(level);
    }

    public void update(UpdateLevelCommand command) {
        Level level = levelRepository.findById(command.getId())
                .orElseThrow(LevelNotFoundException::new);

        if (Objects.equals(level.getValue(), command.getLevel())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        level.setValue(command.getLevel());
        levelRepository.save(level);
    }

    public void archiveById(ArchiveLevelCommand command) {
        Level level = levelRepository.findById(command.id())
                .orElseThrow(LevelNotFoundException::new);

        if (schoolClassRepository.countByArchivedAndLevelId(false, level.getId()) > 0) {
            throw new ActiveSchoolClassesException();
        }
        level.setArchived(true);
        levelRepository.save(level);
    }
}
