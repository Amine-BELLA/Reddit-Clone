package clone.reddit.Service;

import clone.reddit.Exceptions.SpringRedditException;
import clone.reddit.Mapper.SubredditMapper;
import clone.reddit.Model.Subreddit;
import clone.reddit.Repository.SubredditRepository;
import clone.reddit.dto.SubredditDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto create(SubredditDto subredditDto) {
        Subreddit subreddit = subredditMapper.mapDtoToSubreddit(subredditDto);
        subredditDto.setId(subreddit.getId());
        subredditRepository.save(subreddit);
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subreddit -> subredditMapper.mapSubredditToDto(subreddit))
                .collect(Collectors.toList());
    }

    @Transactional
    public SubredditDto getById(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> {return new SpringRedditException();});
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
