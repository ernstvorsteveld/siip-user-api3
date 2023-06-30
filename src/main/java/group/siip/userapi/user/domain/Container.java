package group.siip.userapi.user.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Container {

    private String value;

    public String get() {
        return value;
    }
}
