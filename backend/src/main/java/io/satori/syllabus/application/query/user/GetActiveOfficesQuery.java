package io.satori.syllabus.application.query.user;


import org.springframework.data.domain.Pageable;

public record GetActiveOfficesQuery(Pageable pageable) {
}
