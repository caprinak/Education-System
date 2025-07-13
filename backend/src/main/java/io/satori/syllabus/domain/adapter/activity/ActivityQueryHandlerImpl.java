package io.satori.syllabus.domain.adapter.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.dto.ActivityDTO;
import io.satori.syllabus.application.dto.GradeOfActivityDTO;
import io.satori.syllabus.application.handler.activity.ActivityQueryHandler;
import io.satori.syllabus.application.query.activity.GetActiveActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.activity.GetActivityByIdQuery;
import io.satori.syllabus.application.query.activity.GetArchivedActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.activity.GetIncomingActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.activity.GetIncomingActivitiesQuery;
import io.satori.syllabus.application.query.grade.GetGradesOfActivityQuery;
import io.satori.syllabus.domain.exception.grade.GradeNotFoundException;
import io.satori.syllabus.domain.model.Activity;
import io.satori.syllabus.domain.model.Grade;
import io.satori.syllabus.domain.model.User;
import io.satori.syllabus.domain.service.activity.ActivityQueryService;
import io.satori.syllabus.domain.service.grade.GradeQueryService;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ActivityQueryHandlerImpl implements ActivityQueryHandler {

    private final ActivityQueryService activityQueryService;
    private final GradeQueryService gradeQueryService;

    @Override
    public Page<ActivityDTO> handle(GetActiveActivitiesByRealisationQuery query) {
        return activityQueryService.getAllActiveByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query) {
        return activityQueryService.getAllArchivedByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public Activity handle(GetActivityByIdQuery query) {
        return activityQueryService.getById(query.id());
    }

    @Override
    public Page<ActivityDTO> handle(GetIncomingActivitiesByRealisationQuery query) {
        return activityQueryService.getAllIncomingByRealisation(query.realisationId(), query.pageable());
    }

    @Override
    public List<GradeOfActivityDTO> handle(GetGradesOfActivityQuery query) {
        List<GradeOfActivityDTO> gradesOfActivity = activityQueryService.getGradesOfActivity(query.activityId(), query.pageable());
        gradesOfActivity.forEach((gradeOfActivityDTO -> {
            try {
                Grade grade = gradeQueryService.getByActivityAndStudent(gradeOfActivityDTO.getActivityId(), gradeOfActivityDTO.getStudentId());
                gradeOfActivityDTO.setGrade(grade.getValue());
                gradeOfActivityDTO.setComment(grade.getComment());
                gradeOfActivityDTO.setCreatedAt(grade.getCreatedAt());
                gradeOfActivityDTO.setUpdatedAt(grade.getUpdatedAt());
            } catch (GradeNotFoundException ignored) {
            }
        }));
        return gradesOfActivity;
    }

    @Override
    public Page<ActivityDTO> handle(GetIncomingActivitiesQuery query) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return activityQueryService.getAllIncomingByStudent(user.getId(), query.pageable());
    }
}
