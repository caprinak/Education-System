package io.satori.syllabus.application.handler.post;

import io.satori.syllabus.application.command.post.ArchivePostCommand;
import io.satori.syllabus.application.command.post.CreatePostCommand;
import io.satori.syllabus.application.command.post.UpdatePostCommand;

public interface PostCommandHandler {
    void handle(CreatePostCommand command);

    void handle(UpdatePostCommand command);

    void handle(ArchivePostCommand command);
}
