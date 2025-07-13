package io.satori.syllabus.application.query.activity;


import org.springframework.data.domain.Pageable;

public record GetArchivedActivitiesByRealisationQuery(Long realisationId, Pageable pageable) {
}
