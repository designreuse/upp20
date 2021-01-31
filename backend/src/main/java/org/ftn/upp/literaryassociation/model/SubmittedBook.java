package org.ftn.upp.literaryassociation.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "submitted_books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmittedBook extends Book {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookSubmissionStatus submissionStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Author author;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> betaAccessReaderComments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "availableBetaAccessBooks")
    private Set<BetaAccessReader> betaAccessReaders = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CorrectionRequest> requestedCorrections = new HashSet<>();
}
