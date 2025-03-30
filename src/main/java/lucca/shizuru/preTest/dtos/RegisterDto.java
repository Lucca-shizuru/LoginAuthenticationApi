package lucca.shizuru.preTest.dtos;

import lucca.shizuru.preTest.enums.UserRole;

public record RegisterDto(String userLogin, String userPassword, UserRole role) {
}
