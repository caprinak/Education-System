package io.satori.syllabus.domain.service.schoolclass;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import io.satori.syllabus.application.command.realisation.ArchiveRealisationCommand;
import io.satori.syllabus.application.command.schoolclass.ArchiveSchoolClassCommand;
import io.satori.syllabus.application.command.schoolclass.CreateSchoolClassCommand;
import io.satori.syllabus.application.command.schoolclass.UpdateSchoolClassCommand;
import io.satori.syllabus.domain.exception.schoolclass.SchoolClassAlreadyExistsException;
import io.satori.syllabus.domain.exception.schoolclass.SchoolClassNotFoundException;
import io.satori.syllabus.domain.model.Level;
import io.satori.syllabus.domain.model.Realisation;
import io.satori.syllabus.domain.model.SchoolClass;
import io.satori.syllabus.domain.model.User;
import io.satori.syllabus.domain.service.realisation.RealisationCommandService;
import io.satori.syllabus.infrastructure.repository.LevelRepository;
import io.satori.syllabus.infrastructure.repository.RealisationRepository;
import io.satori.syllabus.infrastructure.repository.SchoolClassRepository;
import io.satori.syllabus.infrastructure.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class SchoolClassCommandService {

    private final SchoolClassRepository schoolClassRepository;
    private final LevelRepository levelRepository;
    private final UserRepository userRepository;
    private final RealisationRepository realisationRepository;
    private final RealisationCommandService realisationCommandService;


    public SchoolClass create(CreateSchoolClassCommand command) {
        String shortName = command.getShortName().trim();

        if (schoolClassRepository.findByArchivedAndNameAndLevelId(false, shortName, command.getLevelId()) != null) {
            throw new SchoolClassAlreadyExistsException();
        }

        SchoolClass schoolClass = new SchoolClass();

        Level level = levelRepository.findById(command.getLevelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User teacher = userRepository.findById(command.getTeacherId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        schoolClass.setLevel(level);
        schoolClass.setSupervisingTeacher(teacher);
        schoolClass.setName(shortName);
        schoolClass.setFullName(command.getFullName());

        return schoolClassRepository.save(schoolClass);
    }

    public SchoolClass update(UpdateSchoolClassCommand command) {
        SchoolClass schoolClass = schoolClassRepository.findById(command.getId())
                .orElseThrow(SchoolClassNotFoundException::new);

        String newShortName = command.getShortName();
        SchoolClass conflictingClass;

        if (newShortName != null) {
            newShortName = newShortName.trim();
            conflictingClass = schoolClassRepository
                    .findByArchivedAndNameAndLevelId(false, newShortName, schoolClass.getLevel().getId());

            if (conflictingClass != null && !Objects.equals(conflictingClass.getId(), schoolClass.getId())) {
                throw new SchoolClassAlreadyExistsException();
            }
        }

        if (command.getLevelId() != null) {
            Level level = levelRepository.findById(command.getLevelId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            conflictingClass = schoolClassRepository
                    .findByArchivedAndNameAndLevelId(false, schoolClass.getName(), level.getId());

            if (conflictingClass != null && !Objects.equals(conflictingClass.getId(), schoolClass.getId())) {
                throw new SchoolClassAlreadyExistsException();
            }
            schoolClass.setLevel(level);
        }

        if (command.getTeacherId() != null) {
            User teacher = userRepository.findById(command.getTeacherId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            schoolClass.setSupervisingTeacher(teacher);
        }

        schoolClass.setName(command.getShortName() == null ? schoolClass.getName() : command.getShortName());
        schoolClass.setFullName(command.getFullName() == null ? schoolClass.getFullName() : command.getFullName());

        return schoolClassRepository.save(schoolClass);
    }

    public void archiveById(ArchiveSchoolClassCommand command) {
        SchoolClass schoolClass = schoolClassRepository.findById(command.id())
                .orElseThrow(SchoolClassNotFoundException::new);

        schoolClass.setSupervisingTeacher(null);
        schoolClass.getStudents().forEach((student) -> student.setSchoolClass(null));

        List<Realisation> realisations = realisationRepository.findAllByArchivedAndSchoolClassId(false, schoolClass.getId());
        realisations.forEach((realisation -> realisationCommandService.archiveById(new ArchiveRealisationCommand(realisation.getId()))));

        schoolClass.setArchived(true);
        schoolClassRepository.save(schoolClass);
    }
}
