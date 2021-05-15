package clone.reddit.Controller;

import clone.reddit.Model.Post;
import clone.reddit.Service.PostService;
import clone.reddit.dto.PostRequest;
import clone.reddit.dto.PostResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor

public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.create(postRequest));
    }

    @GetMapping()
    public ResponseEntity<List<PostResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getById(id));
    }

    @GetMapping("subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getBySubreddit(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getBySubreddit(id));
    }

    @GetMapping("user/{username}")
    public ResponseEntity<List<PostResponse>> getByUser(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getByUser(username));
    }
}
