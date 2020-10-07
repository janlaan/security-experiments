package org.jan.securityexperiments;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@Configuration
public class AuthorizationAspect {

    @Before("@annotation(org.jan.securityexperiments.MyDataAuthorize) && args(accountID,..)")
    public void before(int accountID) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        if(!canAccess(name, accountID)) {
            throw new MyAuthException(String.format("Access denied for user %s to account %s", name, accountID));
        }
    }

    private boolean canAccess(String username, int accountID) {
        return username.equals("user") && accountID == 3;
    }

    //Ensure that whenever @MyDataAuthorize is used without an int as the first parameter, it fails
    @Before("@annotation(org.jan.securityexperiments.MyDataAuthorize)")
    public void before2(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(args.length < 1 || !(args[0] instanceof Integer)) {
            throw new RuntimeException("Invalid arguments to @MyDataAuthorize");
        }
    }
}


