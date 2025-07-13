package io.satori.syllabus.domain.shared;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.satori.syllabus.domain.exception.activity.ActivityNotFoundException;
import io.satori.syllabus.domain.exception.grade.GradeNotFoundException;
import io.satori.syllabus.domain.exception.post.PostNotFoundException;
import io.satori.syllabus.domain.exception.realisation.NotAffiliatedWithRealisationException;
import io.satori.syllabus.domain.exception.realisation.RealisationArchivedException;
import io.satori.syllabus.domain.exception.realisation.RealisationNotFoundException;
import io.satori.syllabus.domain.model.Activity;
import io.satori.syllabus.domain.model.Grade;
import io.satori.syllabus.domain.model.Post;
import io.satori.syllabus.domain.model.Realisation;
import io.satori.syllabus.domain.model.Role;
import io.satori.syllabus.domain.model.User;
import io.satori.syllabus.infrastructure.repository.ActivityRepository;
import io.satori.syllabus.infrastructure.repository.GradeRepository;
import io.satori.syllabus.infrastructure.repository.PostRepository;
import io.satori.syllabus.infrastructure.repository.RealisationRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AccessGuard {

    private final RealisationRepository realisationRepository;
    private final ActivityRepository activityRepository;
    private final PostRepository postRepository;
    private final GradeRepository gradeRepository;

    public void checkAccessToRealisation(Long realisationId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new NotAffiliatedWithRealisationException();
        }
        List<Role> privilegedRoles = new ArrayList<>(Arrays.asList(Role.OFFICE, Role.DIRECTOR, Role.ADMIN));

        Realisation realisation = realisationRepository.findById(realisationId)
                .orElseThrow(RealisationNotFoundException::new);

        if (realisation.isArchived() && !privilegedRoles.contains(user.getRole())) {
            throw new RealisationArchivedException();
        }

        boolean isAffiliatedAsStudent = realisation.getSchoolClass()
                .getStudents()
                .stream()
                .anyMatch((student -> Objects.equals(student.getId(), user.getId())));

        boolean isAffiliatedAsTeacher = Objects.equals(realisation.getTeacher().getId(), user.getId());

        if (!isAffiliatedAsStudent && !isAffiliatedAsTeacher && !privilegedRoles.contains(user.getRole())) {
            throw new NotAffiliatedWithRealisationException();
        }
    }

    public void checkAccessToActivity(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(ActivityNotFoundException::new);
        checkAccessToRealisation(activity.getRealisation().getId());
    }

    public void checkAccessToPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        checkAccessToRealisation(post.getRealisation().getId());
    }

    public void checkAccessToGrade(Long gradeId) {
        Grade grade = gradeRepository.findById(gradeId)
                .orElseThrow(GradeNotFoundException::new);
        checkAccessToRealisation(grade.getActivity().getRealisation().getId());
    }
}
