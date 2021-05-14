package clone.reddit.Controller;

import clone.reddit.Model.Subreddit;
import clone.reddit.Repository.SubredditRepository;
import clone.reddit.Service.SubredditService;
import clone.reddit.dto.SubredditDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subreddit")
@AllArgsConstructor

public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping(value = "/create")
    public ResponseEntity<SubredditDto> create(@RequestBody SubredditDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.create(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SubredditDto> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.getById(id));
    }
}
