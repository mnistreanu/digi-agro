package com.arobs.entity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
        Equals and hashCode must behave consistently across all entity state transitions.
        The entity identifier can be used for equals and hashCode, but only if the hashCode returns the same value all the time.
        Also, when the entity identifier is null, we can guarantee equality only for the same object references.

        Additional information you can find on "Vlad Mihalcea" blog
        "How to implement equals and hashCode using the JPA entity identifier (Primary Key)"
        https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BaseEntity)) {
            return false;
        }

        BaseEntity other = (BaseEntity) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
