package clone.reddit.Service;

import clone.reddit.Mapper.PostMapper;
import clone.reddit.Model.Post;
import clone.reddit.Model.Subreddit;
import clone.reddit.Model.User;
import clone.reddit.Repository.PostRepository;
import clone.reddit.Repository.SubredditRepository;
import clone.reddit.Repository.UserRepository;
import clone.reddit.dto.PostRequest;
import clone.reddit.dto.PostResponse;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private  final UserRepository userRepository;

    private final AuthService authService;
    private final PostMapper postMapper;

    @Transactional
    public PostResponse create(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new NoSuchElementException("No post Found"));
        User user = authService.getCurrentUser();
        Post post = postMapper.mapRequestToPost(postRequest, subreddit, user);
        postRepository.save(post);
        PostResponse response = postMapper.mapToResponse(post);
        return response;
    }

    @Transactional
    public List<PostResponse> getAll() {
        return postRepository.findAll()
                .stream()
                .map(post -> postMapper.mapToResponse(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponse getById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No post Found"));
        return postMapper.mapToResponse(post);
    }

    @Transactional
    public List<PostResponse> getBySubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Subreddit Found"));
        return postRepository.findBySubreddit(subreddit)
                .stream()
                .map(post -> postMapper.mapToResponse(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PostResponse> getByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(post -> postMapper.mapToResponse(post))
                .collect(Collectors.toList());
    }
}
