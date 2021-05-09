package clone.reddit.Controller;

import clone.reddit.dto.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthConroller {

    @PostMapping(value = "/signup")
    public void signup(@RequestBody RegisterRequest registerRequest) {

    }
}
