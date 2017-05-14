package com.adscoop.publisher.entites;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner extends AbstratEntity {

	private Integer width;
	private Integer height;
	private Integer clicks;
	private String picture;

	@Builder.Default
	private List<Long> bannerSpaceIds = new ArrayList<>();

}
