package org.ftn.upp.lass.repository;

import org.ftn.upp.lass.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long>, JpaSpecificationExecutor<SystemAdmin> {

}
