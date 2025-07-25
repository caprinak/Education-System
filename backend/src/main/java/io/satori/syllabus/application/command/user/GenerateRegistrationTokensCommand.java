package io.satori.syllabus.application.command.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import io.satori.syllabus.domain.model.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenerateRegistrationTokensCommand {

    @NotNull
    @Positive
    private Integer amount;

    @NotNull
    private Role role;

    private Long schoolClassId;
}
