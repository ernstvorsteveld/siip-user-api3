package group.siip.userapi.user.service;

import group.siip.userapi.user.domain.Mobile;
import group.siip.userapi.user.domain.SiipAccount;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public Mono<SiipAccount> create(SiipAccount account) {
        return Mono.just(account);
    }

    @Override
    public Flux<SiipAccount> get(Mobile mobile) {
        return Flux.fromArray(new SiipAccount[] {
                SiipAccount.builder().first("john").build()
        });
    }
}
