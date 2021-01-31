package org.ftn.upp.literaryassociation.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "published_books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublishedBook extends Book {

    @Column(nullable = false, updatable = false, unique = true)
    @Min(10) @Max(13)
    @Setter(AccessLevel.NONE)
    private String isbn;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Integer yearOfPublication;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private String placeOfPublication;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private Integer pageCount;

    @ElementCollection
    @CollectionTable(
            name = "published_book_keywords",
            joinColumns = @JoinColumn(name = "published_book_id")
    )
    private Set<String> keywords = new HashSet<>();

    @ManyToMany(mappedBy = "publishedBooks", fetch = FetchType.LAZY)
    private Set<Author> authors = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id", nullable = false)
    private Publisher publisher;
}