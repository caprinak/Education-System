package io.satori.syllabus.application.controller.realisation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.satori.syllabus.application.dto.ActivityDTO;
import io.satori.syllabus.application.dto.AverageGradeDTO;
import io.satori.syllabus.application.dto.GradeDTO;
import io.satori.syllabus.application.dto.PostDTO;
import io.satori.syllabus.application.dto.RealisationDTO;
import io.satori.syllabus.application.dto.SubjectDTO;
import io.satori.syllabus.application.handler.activity.ActivityQueryHandler;
import io.satori.syllabus.application.handler.grade.GradeQueryHandler;
import io.satori.syllabus.application.handler.post.PostQueryHandler;
import io.satori.syllabus.application.handler.realisation.RealisationQueryHandler;
import io.satori.syllabus.application.query.activity.GetActiveActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.activity.GetIncomingActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.grade.GetOwnGradesByRealisationQuery;
import io.satori.syllabus.application.query.post.GetActivePostsByRealisationQuery;
import io.satori.syllabus.application.query.realisation.GetActiveRealisationsQuery;
import io.satori.syllabus.application.query.realisation.GetArchivedRealisationsQuery;
import io.satori.syllabus.application.query.realisation.GetOwnRealisationsQuery;
import io.satori.syllabus.application.query.realisation.GetRealisationAverageGradeQuery;
import io.satori.syllabus.application.query.realisation.GetRealisationByIdQuery;
import io.satori.syllabus.application.query.realisation.GetRealisationInfoByIdQuery;
import io.satori.syllabus.domain.model.Realisation;
import io.satori.syllabus.domain.shared.AccessGuard;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class RealisationQueryController {

    private final RealisationQueryHandler realisationQueryHandler;
    private final PostQueryHandler postQueryHandler;
    private final ActivityQueryHandler activityQueryHandler;
    private final GradeQueryHandler gradeQueryHandler;
    private final AccessGuard accessGuard;

    @GetMapping("/{id}/secured")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Realisation getRealisationById(@PathVariable("id") Long id) {
        return realisationQueryHandler.handle(new GetRealisationByIdQuery(id));
    }

    @GetMapping("/{id}")
    @Secured({"STUDENT", "TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public RealisationDTO getRealisationInfoById(@PathVariable("id") Long id) {
        accessGuard.checkAccessToRealisation(id);
        return realisationQueryHandler.handle(new GetRealisationInfoByIdQuery(id));
    }

    @GetMapping
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<RealisationDTO> getActiveRealisations(Pageable pageable) {
        return realisationQueryHandler.handle(new GetActiveRealisationsQuery(pageable));
    }

    @GetMapping("/archived")
    @Secured({"OFFICE", "DIRECTOR", "ADMIN"})
    public Page<RealisationDTO> getArchivedRealisations(Pageable pageable) {
        return realisationQueryHandler.handle(new GetArchivedRealisationsQuery(pageable));
    }

    @GetMapping("/{id}/posts")
    @Secured({"STUDENT", "TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Page<PostDTO> getActivePostsOfRealisation(@PathVariable("id") Long id, Pageable pageable) {
        accessGuard.checkAccessToRealisation(id);
        return postQueryHandler.handle(new GetActivePostsByRealisationQuery(id, pageable));
    }

    @GetMapping("/{id}/grades")
    @Secured("STUDENT")
    public Page<GradeDTO> getOwnGradesOfRealisation(@PathVariable("id") Long id, Pageable pageable) {
        accessGuard.checkAccessToRealisation(id);
        return gradeQueryHandler.handle(new GetOwnGradesByRealisationQuery(id, pageable));
    }

    @GetMapping("/{id}/activities")
    @Secured({"STUDENT", "TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Page<ActivityDTO> getActiveActivitiesOfRealisation(@PathVariable("id") Long id, Pageable pageable) {
        accessGuard.checkAccessToRealisation(id);
        return activityQueryHandler.handle(new GetActiveActivitiesByRealisationQuery(id, pageable));
    }

    @GetMapping("/{id}/activities/incoming")
    @Secured({"STUDENT", "TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Page<ActivityDTO> getIncomingActivitiesOfRealisation(@PathVariable("id") Long id, Pageable pageable) {
        accessGuard.checkAccessToRealisation(id);
        return activityQueryHandler.handle(new GetIncomingActivitiesByRealisationQuery(id, pageable));
    }

    @GetMapping("/{id}/average")
    @Secured("STUDENT")
    public AverageGradeDTO getRealisationAverageGrade(@PathVariable("id") Long id) {
        accessGuard.checkAccessToRealisation(id);
        return realisationQueryHandler.handle(new GetRealisationAverageGradeQuery(id));
    }

    @GetMapping("/me")
    @Secured({"STUDENT", "TEACHER"})
    public List<SubjectDTO> getOwnRealisations() {
        return realisationQueryHandler.handle(new GetOwnRealisationsQuery());
    }
}
