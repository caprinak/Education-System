package io.satori.syllabus.application.handler.user;

import io.satori.syllabus.application.command.user.LoginCommand;
import io.satori.syllabus.application.command.user.RegisterCommand;
import io.satori.syllabus.infrastructure.security.JwtResponse;

public interface AuthHandler {
    JwtResponse handle(LoginCommand command);

    void handle(RegisterCommand command);
}
