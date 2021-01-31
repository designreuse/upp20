package org.ftn.upp.literaryassociation.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false)
    @NotBlank
    private String name;

    @Column(nullable = false, updatable = false)
    @NotBlank
    private String contentType;

    @Column(nullable = false, updatable = false)
    @Positive
    @Setter(AccessLevel.NONE)
    private Integer version;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime submittedAt;

    @Column(nullable = false)
    private LocalDateTime editedAt;

    @Lob
    @Column(nullable = false, updatable = false)
    private byte[] byteContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_document_id", referencedColumnName = "id")
    private Document previousDocument;
}
