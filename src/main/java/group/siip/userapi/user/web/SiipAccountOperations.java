package group.siip.userapi.user.web;

import group.siip.userapi.user.domain.SiipAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public interface SiipAccountOperations {

    @GetMapping(value = "/{mobile}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    Flux<SiipAccount> get(@PathVariable
                     @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Mobile number is invalid")
                     @NotBlank @Size(max = 12, message = "Maximum length of a mobile number is 12") String mobile);

    @PostMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    Mono<SiipAccount> create(@Valid @RequestBody SiipAccount account);

}
