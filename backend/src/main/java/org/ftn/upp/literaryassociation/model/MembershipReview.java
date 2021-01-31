package org.ftn.upp.literaryassociation.model;

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
    private ReviewResult reviewResult;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 10, max = 300)
    private String justification;

    @Column(nullable = false)
    private LocalDateTime lastChangedAt;
}
