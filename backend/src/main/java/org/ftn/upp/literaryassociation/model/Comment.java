package org.ftn.upp.literaryassociation.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false)
    @NotBlank
    private String text;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime commentedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "beta_access_reader_id", nullable = false)
    private BetaAccessReader commentedBy;
}
