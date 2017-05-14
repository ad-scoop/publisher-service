package com.adscoop.publisher.entites;

import org.neo4j.ogm.annotation.GraphId;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class AbstratEntity {

	@GraphId
	private Long id;

}
