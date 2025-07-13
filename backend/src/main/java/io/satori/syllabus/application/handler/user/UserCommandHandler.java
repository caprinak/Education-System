package io.satori.syllabus.application.handler.user;

import io.satori.syllabus.application.command.user.ArchiveUserCommand;
import io.satori.syllabus.application.command.user.AssignCommand;
import io.satori.syllabus.application.command.user.ChangePasswordCommand;
import io.satori.syllabus.application.command.user.GenerateRegistrationTokensCommand;
import io.satori.syllabus.application.command.user.UnassignCommand;
import io.satori.syllabus.application.command.user.UpdateDescriptionCommand;
import io.satori.syllabus.application.command.user.UpdateProfileImageCommand;
import io.satori.syllabus.application.command.user.UpdateUserCommand;
import io.satori.syllabus.domain.model.RegistrationToken;

import java.util.List;

public interface UserCommandHandler {

    void handle(UpdateUserCommand command);

    void handle(ArchiveUserCommand command);

    List<RegistrationToken> handle(GenerateRegistrationTokensCommand command);

    void handle(UpdateDescriptionCommand command);

    void handle(ChangePasswordCommand command);

    void handle(UnassignCommand unassignCommand);

    void handle(AssignCommand command);

    void handle(UpdateProfileImageCommand updateProfileImageCommand);
}
