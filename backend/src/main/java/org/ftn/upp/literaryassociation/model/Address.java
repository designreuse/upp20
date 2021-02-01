package org.ftn.upp.lass.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends AbstractBaseEntity {

    @Column(nullable = false)
    @NotBlank
    private String street;

    @Column(nullable = false)
    @NotBlank
    private String city;

    @Column(nullable = false)
    @NotBlank
    private String postalCode;

    @Column(nullable = false)
    @NotBlank
    private String country;

    @Column(nullable = false)
    @Min(-90) @Max(90)
    private Double latitude;

    @Column(nullable = false)
    @Min(-180) @Max(180)
    private Double longitude;
}
