package org.ftn.upp.lass.model;

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

    @Transient
    public boolean isUsed() {
        return this.status.equals(VerificationCodeStatus.USED);
    }

    @Transient
    public boolean hasExpired() {
        return this.status.equals(VerificationCodeStatus.EXPIRED);
    }
}
