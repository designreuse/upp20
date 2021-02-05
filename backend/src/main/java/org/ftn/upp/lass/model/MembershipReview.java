package org.ftn.upp.lass.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "membership_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipReview extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MembershipRequest membershipRequest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private BoardMember reviewee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReviewResult reviewResult = ReviewResult.IN_PROGRESS;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 10, max = 300)
    @Builder.Default
    private String justification = "Yet to be submitted.";

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime lastChangedAt = LocalDateTime.now();
}
