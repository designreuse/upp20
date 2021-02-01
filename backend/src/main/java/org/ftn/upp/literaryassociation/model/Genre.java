package org.ftn.upp.lass.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre extends AbstractBaseEntity {

    @Column(nullable = false, unique = true)
    @NotBlank
    private String name;
}
