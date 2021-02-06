package org.ftn.upp.lass.model;

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
    @Builder.Default
    private Integer version = 1;

    @Column(nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private LocalDateTime submittedAt = LocalDateTime.now();

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime editedAt = LocalDateTime.now();

    @Lob
    @Column(nullable = false, updatable = false)
    private byte[] byteContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_document_id", referencedColumnName = "id")
    private Document previousDocument;
}
