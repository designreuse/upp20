package org.ftn.upp.literaryassociation.model;

import lombok.*;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "request_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PRINTING_REQUEST")
@DiscriminatorOptions(force = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintingRequest extends Request {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private PublishedBook publishedBook;
}
