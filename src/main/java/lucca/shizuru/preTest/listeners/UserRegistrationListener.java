package lucca.shizuru.preTest.listeners;

import lucca.shizuru.preTest.dtos.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener {
    @EventListener
    public void handleUserRegistration(UserRegisteredEvent event) {

        System.out.println("OBSERVER EM AÇÃO: Enviando e-mail de boas-vindas para " + event.user().getUserLogin());
    }

}
