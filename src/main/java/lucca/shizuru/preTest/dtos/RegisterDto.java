package lucca.shizuru.preTest.dtos;

import lucca.shizuru.preTest.models.UserRole;

public record RegisterDto(String userLogin, String userPassword, UserRole role) {
}
