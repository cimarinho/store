package br.com.store.order.domain.entity;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class LogDomain {

    private Long idOrder;
    private String error;
    private LocalDateTime dateTime;


    public LogDomain createLog() {
        this.dateTime = LocalDateTime.now();
        return this;
    }


}
