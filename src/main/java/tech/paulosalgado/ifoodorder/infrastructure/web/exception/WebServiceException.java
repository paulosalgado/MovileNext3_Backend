package tech.paulosalgado.ifoodorder.infrastructure.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WebServiceException {

    private String type;
    private String message;

}
