package io.satori.syllabus.domain.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import io.satori.syllabus.application.command.post.ArchivePostCommand;
import io.satori.syllabus.application.command.post.CreatePostCommand;
import io.satori.syllabus.application.command.post.UpdatePostCommand;
import io.satori.syllabus.domain.exception.post.PostNotFoundException;
import io.satori.syllabus.domain.model.Post;
import io.satori.syllabus.domain.model.Realisation;
import io.satori.syllabus.domain.model.User;
import io.satori.syllabus.infrastructure.repository.PostRepository;
import io.satori.syllabus.infrastructure.repository.RealisationRepository;
import io.satori.syllabus.infrastructure.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCommandService {

    private final PostRepository postRepository;
    private final RealisationRepository realisationRepository;
    private final UserRepository userRepository;


    public Post create(CreatePostCommand command) {
        Post post = new Post();
        post.setContent(command.getContent());

        Realisation realisation = realisationRepository.findById(command.getRealisationId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setRealisation(realisation);
        post.setTeacher(author);
        post.setTitle(command.getTitle());
        return postRepository.save(post);
    }

    public Post update(UpdatePostCommand command) {
        Post post = postRepository.findById(command.getId())
                .orElseThrow(PostNotFoundException::new);

        post.setTitle(command.getTitle() == null ? post.getTitle() : command.getTitle());
        post.setContent(command.getContent() == null ? post.getContent() : command.getContent());
        post.setEdited(true);

        return postRepository.save(post);
    }

    public void archiveById(ArchivePostCommand command) {
        Post post = postRepository.findById(command.id())
                .orElseThrow(PostNotFoundException::new);

        if (post.isArchived()) {
            return;
        }
        post.setArchived(true);
        postRepository.save(post);
    }
}
