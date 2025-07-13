package io.satori.syllabus.domain.adapter.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.command.user.LoginCommand;
import io.satori.syllabus.application.command.user.RegisterCommand;
import io.satori.syllabus.application.handler.user.AuthHandler;
import io.satori.syllabus.domain.service.user.AuthService;
import io.satori.syllabus.infrastructure.security.JwtResponse;

@RequiredArgsConstructor
@Component
public class AuthHandlerImpl implements AuthHandler {

    private final AuthService authService;

    @Override
    public JwtResponse handle(LoginCommand command) {
        return authService.login(command);
    }

    @Override
    public void handle(RegisterCommand command) {
        authService.register(command);
    }
}
