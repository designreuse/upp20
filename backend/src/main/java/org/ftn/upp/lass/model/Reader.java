package org.ftn.upp.lass.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_readers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Reader extends User {

    @Column(nullable = false)
    @Builder.Default
    private Boolean hasRequestedBetaAccess = false;

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
