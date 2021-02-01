package org.ftn.upp.lass.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "correction_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorrectionRequest extends Request {

    @Column(nullable = false, updatable = false)
    @NotBlank
    private String text;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CorrectionRequestStatus status;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proofreader_id", referencedColumnName = "id", nullable = false)
    private Proofreader requestedByProofreader;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "editor_id", referencedColumnName = "id", nullable = false)
    private Editor requestedByEditor;
}
