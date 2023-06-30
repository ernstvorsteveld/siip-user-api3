package group.siip.userapi.user.web;

import group.siip.userapi.user.domain.Mobile;
import group.siip.userapi.user.domain.SiipAccount;
import group.siip.userapi.user.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/users")
public class SiipAccountController implements group.siip.userapi.user.web.SiipAccountOperations {

    private AccountService accountService;

    @Autowired
    public SiipAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Flux<SiipAccount> get(String mobile) {
        return accountService
                .get(new Mobile(mobile))
                .onErrorResume(e -> {
                    throw new MyTimeoutException("USR001001", "While getting user by mobile number.");
                });
    }

    @Override
    public Mono<SiipAccount> create(@RequestBody SiipAccount account) {
        return accountService
                .create(account)
                .onErrorResume(e -> {
                    throw new MyTimeoutException("USR001000", "While creating a new user.");
                });
    }
}
