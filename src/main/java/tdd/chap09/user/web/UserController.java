package tdd.chap09.user.web;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tdd.chap09.common.ErrorResponse;
import tdd.chap09.user.web.dto.UserRegisterRequest;
import tdd.chap09.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity register(@RequestBody UserRegisterRequest request) {
        try {
            userService.register(request.getId(), request.getPwd(), request.getEmail());
            return ResponseEntity.created(URI.create("/users/" + request.getId())).build();
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ErrorResponse.error(ex));
        }
    }
}
