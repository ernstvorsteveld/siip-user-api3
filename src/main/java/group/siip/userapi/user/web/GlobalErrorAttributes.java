package group.siip.userapi.user.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes{
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private HttpStatus errorStatus = HttpStatus.BAD_REQUEST;
    private String errorMessage = "please try again later....";

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);

        if (getError(request) instanceof MyTimeoutException) {
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            map.put("code", ((MyTimeoutException) getError(request)).getCode());
        } else {
            map.put("status", getErrorStatus());
            map.put("message", getErrorMessage());
        }
        return map;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(HttpStatus errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}