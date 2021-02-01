package org.ftn.upp.lass.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("BETA_ACCESS_READER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BetaAccessReader extends Reader {

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private Integer penaltyPointsCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reader_beta_access_genres",
            joinColumns = { @JoinColumn(name = "beta_access_reader_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> betaAccessGenres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "beta_access_reader_available_books",
            joinColumns = { @JoinColumn(name = "beta_access_reader_id") },
            inverseJoinColumns = { @JoinColumn(name = "submitted_book_id") }
    )
    private Set<SubmittedBook> availableBetaAccessBooks = new HashSet<>();

    @Transient
    public void incrementPenaltyPoints() {
        ++penaltyPointsCount;
    }
}
