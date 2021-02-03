package org.ftn.upp.lass.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract base class all entities inherit from,
 * which takes care of dealing with primary keys, {@code hashCode} and {@code equals} operations.
 */
@MappedSuperclass
@Getter
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof AbstractBaseEntity)) {
            return false;
        }
        AbstractBaseEntity other = (AbstractBaseEntity) obj;
        return getId().equals(other.getId());
    }
}
