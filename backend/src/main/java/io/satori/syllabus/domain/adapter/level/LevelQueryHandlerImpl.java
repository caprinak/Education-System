package io.satori.syllabus.domain.adapter.level;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.handler.level.LevelQueryHandler;
import io.satori.syllabus.application.query.level.GetActiveLevelsQuery;
import io.satori.syllabus.application.query.level.GetArchivedLevelsQuery;
import io.satori.syllabus.application.query.level.GetLevelByIdQuery;
import io.satori.syllabus.application.query.level.GetLevelByValueQuery;
import io.satori.syllabus.domain.model.Level;
import io.satori.syllabus.domain.service.level.LevelQueryService;

@RequiredArgsConstructor
@Component
public class LevelQueryHandlerImpl implements LevelQueryHandler {

    private final LevelQueryService levelQueryService;

    @Override
    public Page<Level> handle(GetActiveLevelsQuery query) {
        return levelQueryService.getAllActive(query.pageable());
    }

    @Override
    public Page<Level> handle(GetArchivedLevelsQuery query) {
        return levelQueryService.getAllArchived(query.pageable());
    }

    @Override
    public Level handle(GetLevelByIdQuery query) {
        return levelQueryService.getById(query.id());
    }

    @Override
    public Level handle(GetLevelByValueQuery query) {
        return levelQueryService.getByValue(query.level());
    }
}
