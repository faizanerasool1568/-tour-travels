package com.project.tour.travels.TourTravels.dto;

import java.io.Serializable;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomSortImpl implements Serializable {

	private static final long serialVersionUID = -8259487633897846370L;

	private Direction direction;
	private String property;
	private Boolean ignoreCase;
	private NullHandling nullHandling;
	private Boolean ascending;

	@JsonCreator(mode = Mode.PROPERTIES)
	public CustomSortImpl() {
		super();
	}

	@JsonCreator(mode = Mode.PROPERTIES)
	public CustomSortImpl(@JsonProperty("direction") Direction direction, @JsonProperty("property") String property,
			@JsonProperty("ignoreCase") Boolean ignoreCase, @JsonProperty("nullHandling") NullHandling nullHandling,
			@JsonProperty("ascending") Boolean ascending) {
		super();
		this.direction = direction;
		this.property = property;
		this.ignoreCase = ignoreCase;
		this.nullHandling = nullHandling;
		this.ascending = ascending;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Boolean getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public NullHandling getNullHandling() {
		return nullHandling;
	}

	public void setNullHandling(NullHandling nullHandling) {
		this.nullHandling = nullHandling;
	}

	public Boolean getAscending() {
		return ascending;
	}

	public void setAscending(Boolean ascending) {
		this.ascending = ascending;
	}

}