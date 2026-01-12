package lucca.shizuru.preTest.services;

import lombok.AllArgsConstructor;
import lucca.shizuru.preTest.Exceptions.UserAlreadyExistsException;
import lucca.shizuru.preTest.dtos.RegisterDto;
import lucca.shizuru.preTest.dtos.UserRegisteredEvent;
import lucca.shizuru.preTest.models.UserModel;
import lucca.shizuru.preTest.repositories.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    public void registerUser(RegisterDto data) {
        if(userRepository.findByUserLogin(data.userLogin()) != null) throw new UserAlreadyExistsException();
        String encryptedPassword = passwordEncoder.encode(data.userPassword());
        UserModel newUser = new UserModel(data.userLogin(), encryptedPassword, data.role());
        userRepository.save(newUser);

        eventPublisher.publishEvent(new UserRegisteredEvent(newUser));

    }

}
