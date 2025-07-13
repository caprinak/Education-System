package io.satori.syllabus.domain.service.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import io.satori.syllabus.domain.exception.subject.SubjectNotFoundException;
import io.satori.syllabus.domain.model.Subject;
import io.satori.syllabus.infrastructure.repository.RealisationRepository;
import io.satori.syllabus.infrastructure.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class SubjectQueryService {

    private final SubjectRepository subjectRepository;
    private final RealisationRepository realisationRepository;

    public Page<Subject> getAllActive(Pageable pageable) {
        Page<Subject> subjects = subjectRepository.findAllByArchived(false, pageable);
        subjects.forEach((subject -> {
            subject.setActiveRealisationsCount(realisationRepository.countByArchivedAndSubjectId(false, subject.getId()));
        }));
        return subjects;
    }


    public Page<Subject> getAllArchived(Pageable pageable) {
        return subjectRepository.findAllByArchived(true, pageable);
    }


    public Page<Subject> getByNameContaining(String name, Pageable pageable) {
        return subjectRepository.findSubjectByNameContainingIgnoreCase(name, pageable);
    }


    public Subject getById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(SubjectNotFoundException::new);
    }
}
