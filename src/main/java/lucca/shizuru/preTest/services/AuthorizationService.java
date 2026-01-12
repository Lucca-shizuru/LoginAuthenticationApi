package lucca.shizuru.preTest.services;

import lombok.RequiredArgsConstructor;
import lucca.shizuru.preTest.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUserLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o login: " + username);
        }

        return user;
    }
}
