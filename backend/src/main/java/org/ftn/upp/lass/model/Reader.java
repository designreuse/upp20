package org.ftn.upp.lass.model;

import lombok.*;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_readers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "reader_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("READER")
@DiscriminatorOptions(force = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader extends User {

    @Column(nullable = false)
    private final Boolean hasRequestedBetaAccess = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MembershipStatus membershipStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "reader_favorite_genres",
            joinColumns = { @JoinColumn(name = "reader_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> favoriteGenres = new HashSet<>();
}
