package org.jan.securityexperiments;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationChecker {
    private static final Log LOG = LogFactory.getLog(AuthorizationChecker.class);

    public boolean check(Authentication authentication, int accountID) {
        if (!authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            LOG.info(String.format("Authorization denied. User not authenticated."));
            return false;
        }
        //This is where you check whether this customer can access this particular account ID. How you implement that is up to you.
        boolean canAccess = accountID == 3;//ServiceLayer.canAccess(authentication.getPrincipal(), accountID);

        if(!canAccess) {
            LOG.info(String.format("Authorization denied for user %s to account %s", authentication.getName(), accountID));
        }
        else {
            LOG.info(String.format("Authorization granted for user %s to account %s", authentication.getName(), accountID));
        }
        return canAccess;
    }
}
