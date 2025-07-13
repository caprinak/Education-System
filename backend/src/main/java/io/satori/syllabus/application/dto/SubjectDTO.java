package io.satori.syllabus.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.satori.syllabus.domain.model.Realisation;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {

    private Long courseId;

    private String subjectName;

    private String subjectAbbreviation;

    private String imageUrl;

    private String schoolClassName;

    public SubjectDTO(Realisation realisation) {
        this.courseId = realisation.getId();
        this.subjectName = realisation.getSubject().getName();
        this.subjectAbbreviation = realisation.getSubject().getAbbreviation();
        this.schoolClassName = realisation.getSchoolClass().getSchoolClassName();
        this.imageUrl = realisation.getSubject().getImageUrl();
    }
}
