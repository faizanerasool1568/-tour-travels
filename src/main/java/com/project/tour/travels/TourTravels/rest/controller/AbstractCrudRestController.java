package com.project.tour.travels.TourTravels.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.tour.travels.TourTravels.dto.IdentityDTO;
import com.project.tour.travels.TourTravels.dto.PageWrapperDTO;
import com.project.tour.travels.TourTravels.dto.SearchCriteria;
import com.project.tour.travels.TourTravels.service.ICrudService;

public abstract class AbstractCrudRestController<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrudRestController.class);

	private final ICrudService<T> crudService;
	private final String className;

	public AbstractCrudRestController(ICrudService<T> crudService, String className) {
		this.crudService = crudService;
		this.className = className;
	}

	public ICrudService<T> getCrudService() {
		return crudService;
	}

	@PostMapping("/create")
	public T create(@RequestBody @Valid T newEntry) {
		LOGGER.info("Creating a new {} entry by using information: {}", className, newEntry);
		T created = crudService.create(newEntry);
		// done changes @29-Jan-2018
		LOGGER.info("Created a new {} entry: {}", className, created);
		return created;
	}

	@PostMapping("/update")
	public T update(@RequestBody @Valid T entry) {
		LOGGER.info("Updating a {} entry by using information: {}", className, entry);
		T updated = crudService.update(entry);
		LOGGER.info("Updating a {} entry: {}", className, updated);
		return updated;
	}

	@PostMapping("/get")
	public T findById(@RequestBody @Valid IdentityDTO identityDTO) {
		LOGGER.info("Finding {} entry by using id: {}", className, identityDTO.getId());
		T groupEntry = crudService.findById(identityDTO.getId());
		LOGGER.info("Found {} entry: {}", className, groupEntry);
		return groupEntry;
	}

	@PostMapping("/delete")
	public void delete(@RequestBody @Valid IdentityDTO identityDTO) {
		LOGGER.info("Deleting a {} entry with id: {}", className, identityDTO.getId());
		crudService.delete(identityDTO);
		LOGGER.info("Deleted the {} entry with id: {}", className, identityDTO.getId());
	}

	@PostMapping("/search")
	public Object findBySearchTerm(@RequestBody @Valid SearchCriteria searchCriteria, Pageable pageRequest,
			HttpServletRequest request) {
		LOGGER.info("Finding {} entries by search term: {} and page request: {}", className, searchCriteria,
				pageRequest);
		PageWrapperDTO<T> searchResultPage = crudService.findBySearchTerm(searchCriteria, pageRequest);
		return searchResultPage;

	}

	@PostMapping("/download")
	public String download(@RequestBody @Valid SearchCriteria searchCriteria) {
		return crudService.download(searchCriteria);
	}
}
