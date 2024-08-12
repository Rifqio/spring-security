package com.rifqio.springsecurity.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizationEvents {
    @EventListener
    public void onFailure(AuthorizationDeniedEvent event) {
        log.error("Authorization failure for user: {} due to {}",
                event.getAuthentication().get().getName(),
                event.getAuthorizationDecision().toString()
        );
    }
}
