package org.ftn.upp.lass.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "membership_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MembershipRequest extends Request {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Author author;

    @Column(nullable = false, updatable = false)
    @NotBlank
    @Size(min=10, max=300)
    private String coverLetter;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ReviewResult reviewResult = ReviewResult.IN_PROGRESS;

    @Column(nullable = false)
    private LocalDateTime resubmissionDeadline;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Document> submittedDocuments = new HashSet<>();

    @OneToMany(mappedBy = "membershipRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipReview> membershipReviews = new HashSet<>();
}
