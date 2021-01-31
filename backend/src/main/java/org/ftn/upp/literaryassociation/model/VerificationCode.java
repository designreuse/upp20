package org.ftn.upp.literaryassociation.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verification_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationCode extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false, unique = true)
    private String value;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime generatedAt;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VerificationCodeStatus status;
}
