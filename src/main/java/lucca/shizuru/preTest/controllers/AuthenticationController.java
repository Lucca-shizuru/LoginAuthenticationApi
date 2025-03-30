package lucca.shizuru.preTest.controllers;

import jakarta.validation.Valid;
import lucca.shizuru.preTest.dtos.AuthenticationDto;
import lucca.shizuru.preTest.dtos.RegisterDto;
import lucca.shizuru.preTest.models.UserModel;
import lucca.shizuru.preTest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.userLogin(), authenticationDto.userPassword());
        var auth = this.authenticationManager.authenticate( usernamePassword);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        if(this.userRepository.findByUserLogin(registerDto.userLogin()) != null ) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.userPassword());
        UserModel newUser= new UserModel(registerDto.userLogin(), encryptedPassword, registerDto.role());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
