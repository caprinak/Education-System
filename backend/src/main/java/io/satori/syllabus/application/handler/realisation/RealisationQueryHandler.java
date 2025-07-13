package io.satori.syllabus.application.handler.realisation;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.dto.AverageGradeDTO;
import io.satori.syllabus.application.dto.RealisationDTO;
import io.satori.syllabus.application.dto.SubjectDTO;
import io.satori.syllabus.application.query.realisation.GetActiveRealisationsQuery;
import io.satori.syllabus.application.query.realisation.GetArchivedRealisationsQuery;
import io.satori.syllabus.application.query.realisation.GetOwnRealisationsQuery;
import io.satori.syllabus.application.query.realisation.GetRealisationAverageGradeQuery;
import io.satori.syllabus.application.query.realisation.GetRealisationByIdQuery;
import io.satori.syllabus.application.query.realisation.GetRealisationInfoByIdQuery;
import io.satori.syllabus.domain.model.Realisation;

import java.util.List;

public interface RealisationQueryHandler {
    Page<RealisationDTO> handle(GetActiveRealisationsQuery query);

    Page<RealisationDTO> handle(GetArchivedRealisationsQuery query);

    Realisation handle(GetRealisationByIdQuery query);

    AverageGradeDTO handle(GetRealisationAverageGradeQuery getRealisationAverageGradeQuery);

    List<SubjectDTO> handle(GetOwnRealisationsQuery getOwnRealisationsQuery);

    RealisationDTO handle(GetRealisationInfoByIdQuery getRealisationInfoByIdQuery);
}
