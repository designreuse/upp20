package org.ftn.upp.lass.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Request extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private LocalDateTime requestedAt = LocalDateTime.now();
}
