package clone.reddit.Repository;

import clone.reddit.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRespository extends JpaRepository<Comment,Long> {
}
