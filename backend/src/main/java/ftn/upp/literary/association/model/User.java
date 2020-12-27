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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String email;

    @ElementCollection(targetClass = Genre.class)
    @CollectionTable(
            name = "user_genre",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Set<Genre> genres = new HashSet<>();

}
