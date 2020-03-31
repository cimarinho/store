package br.com.store.order.domain;

import br.com.store.order.domain.repository.Repository;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class LogDomain {

    private Repository<LogDomain, Long> repository;

    private Long idOrder;
    private String error;
    private LocalDateTime dateTime;


    public LogDomain createLog() {
        this.dateTime = LocalDateTime.now();
        return repository.save(this);
    }


}
