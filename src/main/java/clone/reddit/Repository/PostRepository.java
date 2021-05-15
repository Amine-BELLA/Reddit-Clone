package clone.reddit.Repository;

import clone.reddit.Model.Post;
import clone.reddit.Model.Subreddit;
import clone.reddit.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findBySubreddit(Subreddit subreddit);

    Optional<Post> findByUser(User user);
}
