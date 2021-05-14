package clone.reddit.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SpringRedditException extends RuntimeException {
    public ResponseEntity<String> SpringRedditException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Subreddit Found");
    }
}
