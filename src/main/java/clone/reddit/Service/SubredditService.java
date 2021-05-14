package clone.reddit.Service;

import clone.reddit.Model.Subreddit;
import clone.reddit.Repository.SubredditRepository;
import clone.reddit.dto.SubredditDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubredditService {
    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto create(SubredditDto subredditDto) {
        Subreddit subreddit = mapSubredditDto(subredditDto);
        subredditDto.setId(subreddit.getId());
        subredditRepository.save(subreddit);
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subreddit -> mapToDto(subreddit))
                .collect(Collectors.toList());
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder()
                .name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .createdDate(Instant.now())
                .build();
    }

    private SubredditDto mapToDto(Subreddit subreddit) {
        return SubredditDto.builder()
                .name(subreddit.getName())
                .description(subreddit.getDescription())
                .id(subreddit.getId())
                .nbrOfPosts(subreddit.getPosts().size())
                .build();
    }
}
