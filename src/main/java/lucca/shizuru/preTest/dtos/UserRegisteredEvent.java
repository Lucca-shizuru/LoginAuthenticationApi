package lucca.shizuru.preTest.dtos;

import lucca.shizuru.preTest.models.UserModel;

public record UserRegisteredEvent(UserModel user) {
}
