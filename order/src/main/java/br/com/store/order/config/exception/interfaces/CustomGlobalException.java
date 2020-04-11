package br.com.store.order.config.exception.interfaces;

import br.com.store.order.config.exception.response.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomGlobalException {

    default ResponseEntity error(List<String> error, Throwable message) {
        CustomErrorResponse errors = CustomErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .errors(error)
                .message(message.getMessage())
                .build();
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

}
