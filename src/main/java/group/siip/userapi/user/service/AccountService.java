package group.siip.userapi.user.service;

import group.siip.userapi.user.domain.Mobile;
import group.siip.userapi.user.domain.SiipAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<SiipAccount> create(SiipAccount account);

    Flux<SiipAccount> get(Mobile mobile);
}
