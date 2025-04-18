package lucca.shizuru.preTest.repositories;

import lucca.shizuru.preTest.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserDetails findByUserLogin(String userLogin);
}
