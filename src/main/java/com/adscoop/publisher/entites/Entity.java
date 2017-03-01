package com.adscoop.publisher.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.neo4j.ogm.annotation.GraphId;

/**
 * Created by thokle on 24/08/2016.
 */
public abstract class Entity {


    @GraphId
    @JsonProperty("id")
    private Long id;

    public Entity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || id == null || getClass() != obj.getClass()) return false;
        Entity entity = (Entity) obj;

        if (!id.equals(entity.getId())) return false;

        return true;


    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
