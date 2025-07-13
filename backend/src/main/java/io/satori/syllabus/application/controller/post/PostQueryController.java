package io.satori.syllabus.application.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.satori.syllabus.application.dto.PostDTO;
import io.satori.syllabus.application.handler.post.PostQueryHandler;
import io.satori.syllabus.application.query.post.GetPostByIdQuery;
import io.satori.syllabus.application.query.post.GetRecentActivePosts;
import io.satori.syllabus.domain.model.Post;
import io.satori.syllabus.domain.shared.AccessGuard;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostQueryController {

    private final PostQueryHandler postQueryHandler;
    private final AccessGuard accessGuard;

    @GetMapping("/{id}")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public Post getPostById(@PathVariable("id") Long id) {
        accessGuard.checkAccessToPost(id);
        return postQueryHandler.handle(new GetPostByIdQuery(id));
    }

    @GetMapping("/recent")
    @Secured({"STUDENT"})
    public Page<PostDTO> getRecentPosts(Pageable pageable) {
        return postQueryHandler.handle(new GetRecentActivePosts(pageable));
    }
}
