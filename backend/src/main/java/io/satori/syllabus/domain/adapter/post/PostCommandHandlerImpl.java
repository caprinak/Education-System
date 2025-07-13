package io.satori.syllabus.domain.adapter.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.satori.syllabus.application.command.post.ArchivePostCommand;
import io.satori.syllabus.application.command.post.CreatePostCommand;
import io.satori.syllabus.application.command.post.UpdatePostCommand;
import io.satori.syllabus.application.handler.post.PostCommandHandler;
import io.satori.syllabus.domain.service.post.PostCommandService;

@RequiredArgsConstructor
@Component
public class PostCommandHandlerImpl implements PostCommandHandler {

    private final PostCommandService postCommandService;

    @Override
    public void handle(CreatePostCommand command) {
        postCommandService.create(command);
    }

    @Override
    public void handle(UpdatePostCommand command) {
        postCommandService.update(command);
    }

    @Override
    public void handle(ArchivePostCommand command) {
        postCommandService.archiveById(command);
    }

}
