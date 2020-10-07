package org.jan.securityexperiments;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/accounts")
public class AccountsController {

    @GetMapping("/{accountID}")
    public String getAccountInfo(@PathVariable int accountID) {
        return "This is your account info for account number " + accountID;
    }

    @MyDataAuthorize
    @GetMapping("/details/{accountID}")
    public String getSomething(@PathVariable int accountID) {
        return "Showing account details for account # " + accountID;
    }

    @MyDataAuthorize
    @GetMapping("/something2/{bananas}")
    public String getBananas(@PathVariable String bananas) {
        return "this is bananas: " + bananas;
    }
}
