package clone.reddit.Controller;

import clone.reddit.Model.Post;
import clone.reddit.Service.PostService;
import clone.reddit.dto.PostRequest;
import clone.reddit.dto.PostResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@AllArgsConstructor

public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Post> create(PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postRequest));
    }
}
