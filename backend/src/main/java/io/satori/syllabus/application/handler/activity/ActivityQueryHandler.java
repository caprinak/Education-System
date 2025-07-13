package io.satori.syllabus.application.handler.activity;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.dto.ActivityDTO;
import io.satori.syllabus.application.dto.GradeOfActivityDTO;
import io.satori.syllabus.application.query.activity.GetActiveActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.activity.GetActivityByIdQuery;
import io.satori.syllabus.application.query.activity.GetArchivedActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.activity.GetIncomingActivitiesByRealisationQuery;
import io.satori.syllabus.application.query.activity.GetIncomingActivitiesQuery;
import io.satori.syllabus.application.query.grade.GetGradesOfActivityQuery;
import io.satori.syllabus.domain.model.Activity;

import java.util.List;

public interface ActivityQueryHandler {
    Page<ActivityDTO> handle(GetActiveActivitiesByRealisationQuery query);

    Page<Activity> handle(GetArchivedActivitiesByRealisationQuery query);

    Activity handle(GetActivityByIdQuery query);

    Page<ActivityDTO> handle(GetIncomingActivitiesByRealisationQuery getIncomingActivitiesByRealisationQuery);

    List<GradeOfActivityDTO> handle(GetGradesOfActivityQuery getGradesOfActivityQuery);

    Page<ActivityDTO> handle(GetIncomingActivitiesQuery getIncomingActivitiesQuery);
}
