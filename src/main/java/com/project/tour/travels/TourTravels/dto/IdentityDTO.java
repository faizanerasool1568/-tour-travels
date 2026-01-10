package com.project.tour.travels.TourTravels.dto;

public class IdentityDTO extends AbstractDTO {

	private static final long serialVersionUID = -8668235651933128162L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IdentityDTO(Long id) {
		super();
		this.id = id;
	}

	public IdentityDTO() {

	}

	@Override
	public String toString() {
		return "IdentityDTO [id=" + id + "]";
	}
}