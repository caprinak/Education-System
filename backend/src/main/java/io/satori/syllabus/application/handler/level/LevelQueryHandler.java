package io.satori.syllabus.application.handler.level;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.query.level.GetActiveLevelsQuery;
import io.satori.syllabus.application.query.level.GetArchivedLevelsQuery;
import io.satori.syllabus.application.query.level.GetLevelByIdQuery;
import io.satori.syllabus.application.query.level.GetLevelByValueQuery;
import io.satori.syllabus.domain.model.Level;

public interface LevelQueryHandler {
    Page<Level> handle(GetActiveLevelsQuery query);

    Page<Level> handle(GetArchivedLevelsQuery query);

    Level handle(GetLevelByIdQuery query);

    Level handle(GetLevelByValueQuery query);
}
