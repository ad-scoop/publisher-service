package com.adscoop.publisher.entites;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import lombok.*;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class Campagin extends AbstratEntity {

	private String name;
	private long startDate;
	private long endDate;
	private double maxPricePrDay;
	private long clicks;


	
	@Builder.Default
	private List<Long> webSiteIds = Lists.newArrayList();

	@Relationship(type = "CAMPAGIN_HAS_BANNERS")
	@Builder.Default
	private List<Banner> banners = Lists.newArrayList();

}
