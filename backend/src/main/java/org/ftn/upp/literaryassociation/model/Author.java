package org.ftn.upp.literaryassociation.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author extends User {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MembershipStatus membershipStatus;

    @Column(nullable = false)
    @Min(0) @Max(2)
    private Integer resubmissionCount;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MembershipRequest> membershipRequests = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "author_favorite_genres",
            joinColumns = { @JoinColumn(name = "author_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> favoriteGenres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "author_published_books",
            joinColumns = { @JoinColumn(name = "author_id") },
            inverseJoinColumns = { @JoinColumn(name = "published_book_id") }
    )
    private Set<PublishedBook> publishedBooks = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubmittedBook> submittedBooks = new HashSet<>();

    @Transient
    public void incrementResubmissionCount() {
        ++resubmissionCount;
    }
}
