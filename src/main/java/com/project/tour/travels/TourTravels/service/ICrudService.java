package com.project.tour.travels.TourTravels.service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.project.tour.travels.TourTravels.dto.IdentityDTO;
import com.project.tour.travels.TourTravels.dto.PageWrapperDTO;
import com.project.tour.travels.TourTravels.dto.SearchCriteria;

@Transactional(rollbackFor = Exception.class)
public interface ICrudService<T> {

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public PageWrapperDTO<T> findBySearchTerm(SearchCriteria searchCriteria, Pageable pageRequest);

	public T create(T entry);

	public T update(T entry);

	public void delete(IdentityDTO entry);

	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public T findById(Long id);

	public String download(SearchCriteria searchCriteria);

}