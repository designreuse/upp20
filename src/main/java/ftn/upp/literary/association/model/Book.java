package ftn.upp.literary.association.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Genre genre;
    private Integer isbn;
    private String publisher;
    private Integer yearPublished;
    private String placePublished;
    private Integer numberOfPages;
    private String synopsis;

    @ElementCollection
    private Set<String> keywords = new HashSet<>();

}
