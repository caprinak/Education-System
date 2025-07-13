package io.satori.syllabus.application.controller.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.satori.syllabus.application.dto.ActivityDTO;
import io.satori.syllabus.application.dto.GradeOfActivityDTO;
import io.satori.syllabus.application.handler.activity.ActivityQueryHandler;
import io.satori.syllabus.application.query.activity.GetActivityByIdQuery;
import io.satori.syllabus.application.query.activity.GetIncomingActivitiesQuery;
import io.satori.syllabus.application.query.grade.GetGradesOfActivityQuery;
import io.satori.syllabus.domain.model.Activity;
import io.satori.syllabus.domain.shared.AccessGuard;

import java.util.List;

@RestController
@RequestMapping("/activities")
@RequiredArgsConstructor
public class ActivityQueryController {

    private final ActivityQueryHandler activityQueryHandler;
    private final AccessGuard accessGuard;

    @GetMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Activity getActivityById(@PathVariable("id") Long id) {
        accessGuard.checkAccessToActivity(id);
        return activityQueryHandler.handle(new GetActivityByIdQuery(id));
    }

    @GetMapping("/{id}/grades")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public List<GradeOfActivityDTO> getGradesOfActivity(@PathVariable("id") Long id, Pageable pageable) {
        accessGuard.checkAccessToActivity(id);
        return activityQueryHandler.handle(new GetGradesOfActivityQuery(id, pageable));
    }

    @GetMapping("/incoming")
    @Secured({"STUDENT"})
    public Page<ActivityDTO> getIncomingActivities(Pageable pageable) {
        return activityQueryHandler.handle(new GetIncomingActivitiesQuery(pageable));
    }
}
