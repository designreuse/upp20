package org.ftn.upp.literaryassociation.repository;

import org.ftn.upp.literaryassociation.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long>, JpaSpecificationExecutor<Editor> {

}
