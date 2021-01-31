package org.ftn.upp.literaryassociation.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "plagiarism_assessment_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlagiarismAssessmentResult extends AbstractBaseEntity {

    @Column(nullable = false)
    @NotBlank
    @Size(min = 10, max = 300)
    private String justification;

    @Column(nullable = false)
    private final Boolean isPlagiarism = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assigned_editor_id", referencedColumnName = "id", nullable = false)
    public Editor assignedEditor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plagiarism_report_id", referencedColumnName = "id", nullable = false)
    public PlagiarismReport plagiarismReport;
}
