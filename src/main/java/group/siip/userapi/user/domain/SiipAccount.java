package group.siip.userapi.user.domain;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SiipAccount extends DomainObject {
    private String id;
    private String keycloakLocation;
    private String username;
    private String mobile;
    private String email;
    private String password;
    private String first;
    private String last;
    private String dateOfBirth;

    private FaceTemplate faceTemplate;
    private String siipId1;
    private String siipId2;
    private String appId;
    private String pinCode;
    private String dataHash;
    private boolean enabled;

    private String location;
}