package io.satori.syllabus.application.handler.post;

import org.springframework.data.domain.Page;
import io.satori.syllabus.application.dto.PostDTO;
import io.satori.syllabus.application.query.post.GetActivePostsByRealisationQuery;
import io.satori.syllabus.application.query.post.GetArchivedPostsByRealisationQuery;
import io.satori.syllabus.application.query.post.GetPostByIdQuery;
import io.satori.syllabus.application.query.post.GetRecentActivePosts;
import io.satori.syllabus.domain.model.Post;

public interface PostQueryHandler {
    Page<PostDTO> handle(GetActivePostsByRealisationQuery query);

    Page<Post> handle(GetArchivedPostsByRealisationQuery query);

    Post handle(GetPostByIdQuery query);

    Page<PostDTO> handle(GetRecentActivePosts query);
}
