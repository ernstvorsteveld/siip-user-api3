package group.siip.userapi.user.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MyTimeoutException extends RuntimeException {

    private final String message;
    private final String code;

    public MyTimeoutException(String code, String message) {
        super("Timeout occurred: " + message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
}
