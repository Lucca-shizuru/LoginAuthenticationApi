package lucca.shizuru.preTest.services;
import lombok.RequiredArgsConstructor;
import lucca.shizuru.preTest.Exceptions.UserAlreadyExistsException;
import lucca.shizuru.preTest.dtos.AuthenticationDto;
import lucca.shizuru.preTest.dtos.RegisterDto;
import lucca.shizuru.preTest.dtos.UserRegisteredEvent;
import lucca.shizuru.preTest.infra.security.TokenService;
import lucca.shizuru.preTest.models.UserModel;
import lucca.shizuru.preTest.repositories.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor

public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    public String login(AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.userLogin(), data.userPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((UserModel) auth.getPrincipal());
    }
    public void register(RegisterDto data) {
        if (this.userRepository.findByUserLogin(data.userLogin()) != null) {
            throw new RuntimeException("User already exists");
        }

        String encryptedPassword = passwordEncoder.encode(data.userPassword());
        UserModel newUser = new UserModel(data.userLogin(), encryptedPassword, data.role());
        this.userRepository.save(newUser);

        eventPublisher.publishEvent(new UserRegisteredEvent(newUser));
    }

}
