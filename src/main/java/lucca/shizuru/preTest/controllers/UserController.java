package lucca.shizuru.preTest.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lucca.shizuru.preTest.dtos.AuthenticationDto;
import lucca.shizuru.preTest.dtos.LoginResponseDto;
import lucca.shizuru.preTest.dtos.RegisterDto;
import lucca.shizuru.preTest.infra.security.TokenService;
import lucca.shizuru.preTest.models.UserModel;
import lucca.shizuru.preTest.repositories.UserRepository;
import lucca.shizuru.preTest.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto data) {
        var token = authService.login(data);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDto data) {
        authService.register(data);
        return ResponseEntity.ok().build();
    }


}
