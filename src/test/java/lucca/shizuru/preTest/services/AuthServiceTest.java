package lucca.shizuru.preTest.services;

import lucca.shizuru.preTest.dtos.RegisterDto;
import lucca.shizuru.preTest.enums.UserRole;
import lucca.shizuru.preTest.models.UserModel;
import lucca.shizuru.preTest.repositories.UserRepository;
import lucca.shizuru.preTest.Exceptions.UserAlreadyExistsException;
import lucca.shizuru.preTest.dtos.UserRegisteredEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

 class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("Deve registrar um usuário com sucesso quando os dados forem válidos")
    void register_Sucesso() {
        RegisterDto dto = new RegisterDto("lucca", "senha123", UserRole.USER);
        when(userRepository.findByUserLogin("lucca")).thenReturn(null);
        when(passwordEncoder.encode("senha123")).thenReturn("senha_criptografada");


        authService.register(dto);

        verify(userRepository, times(1)).save(any(UserModel.class));


        verify(eventPublisher, times(1)).publishEvent(any(UserRegisteredEvent.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o login já estiver em uso")
    void register_ErroUsuarioExistente() {

        RegisterDto dto = new RegisterDto("lucca", "senha123", UserRole.USER);

        when(userRepository.findByUserLogin("lucca")).thenReturn(new UserModel());


        assertThrows(UserAlreadyExistsException.class, () -> {
            authService.register(dto);
        });


        verify(userRepository, never()).save(any());
        verify(eventPublisher, never()).publishEvent(any());
    }
}

