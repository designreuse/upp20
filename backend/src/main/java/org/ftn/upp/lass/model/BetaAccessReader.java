package org.ftn.upp.lass.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_beta_access_readers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BetaAccessReader extends Reader {

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private Integer penaltyPointsCount = 0;

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
