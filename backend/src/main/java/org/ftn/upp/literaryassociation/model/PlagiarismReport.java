package org.ftn.upp.lass.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plagiarism_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlagiarismReport extends AbstractBaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reported_book_id", referencedColumnName = "id", nullable = false, updatable = false)
    private PublishedBook reportedBook;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plagiarized_book_id", referencedColumnName = "id", nullable = false, updatable = false)
    private PublishedBook plagiarizedBook;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private Author reportedBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlagiarismReportStatus status;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime reportedAt;

    @OneToMany(mappedBy = "plagiarismReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlagiarismAssessmentResult> plagiarismAssessmentResults = new HashSet<>();
}
