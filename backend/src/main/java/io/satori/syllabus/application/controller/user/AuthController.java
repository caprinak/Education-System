package io.satori.syllabus.application.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.satori.syllabus.application.command.user.LoginCommand;
import io.satori.syllabus.application.command.user.RegisterCommand;
import io.satori.syllabus.application.handler.user.AuthHandler;
import io.satori.syllabus.infrastructure.security.JwtResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

    @PostMapping("/authorize")
    public JwtResponse login(@Valid @RequestBody LoginCommand command) {
        return authHandler.handle(command);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterCommand command) {
        authHandler.handle(command);
    }
}
