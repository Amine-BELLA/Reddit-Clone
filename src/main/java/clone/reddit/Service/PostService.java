package clone.reddit.Service;

import clone.reddit.Mapper.PostMapper;
import clone.reddit.Model.Post;
import clone.reddit.Model.Subreddit;
import clone.reddit.Model.User;
import clone.reddit.Repository.PostRepository;
import clone.reddit.Repository.SubredditRepository;
import clone.reddit.dto.PostRequest;
import clone.reddit.dto.PostResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    @Transactional
    public Post create(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName());
        User user = authService.getCurrentUser();
        Post post = postMapper.mapRequestToPost(postRequest, subreddit, user);
        postRepository.save(post);
        return post;
    }
}
