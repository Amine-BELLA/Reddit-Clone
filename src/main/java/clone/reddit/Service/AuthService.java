package clone.reddit.Service;

import clone.reddit.Model.User;
import clone.reddit.Repository.UserRepository;
import clone.reddit.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional

public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setEnabled(false);
        user.setCreated(Instant.now());
        userRepository.save(user);
    }
}
