package org.ftn.upp.lass.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_editors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Editor extends User {

    @OneToMany(mappedBy = "assignedEditor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlagiarismAssessmentResult> assignedPlagiarismAssessmentResults = new HashSet<>();
}
