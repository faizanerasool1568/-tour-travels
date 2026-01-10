package com.project.tour.travels.TourTravels.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.project.tour.travels.TourTravels.config.SecurityUtils;
import com.project.tour.travels.TourTravels.domain.AbstractEntity;
import com.project.tour.travels.TourTravels.dto.AbstractDTO;
import com.project.tour.travels.TourTravels.dto.BusinessException;
import com.project.tour.travels.TourTravels.dto.FieldErrorVM;
import com.project.tour.travels.TourTravels.dto.IdentityDTO;
import com.project.tour.travels.TourTravels.dto.PageWrapperDTO;
import com.project.tour.travels.TourTravels.dto.SearchCriteria;
import com.project.tour.travels.TourTravels.mapper.UserMapper;
import com.project.tour.travels.TourTravels.repo.AbstractRepository;

abstract class AbstractCrudService<T extends AbstractDTO, R extends AbstractRepository<? extends AbstractEntity>>
		implements ICrudService<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrudService.class);
	private final String serviceType;
	private final Class<?> clazz;
	private @Autowired ActivityLogService activityLogService;
	public @Autowired UserMapper userMapper;

	public AbstractCrudService() {
		this.serviceType = null;
		this.clazz = null;
	}

	public AbstractCrudService(String serviceType) {
		this.serviceType = serviceType;
		this.clazz = null;
	}

	public AbstractCrudService(String serviceType, Class<?> clazz) {
		this.serviceType = serviceType;
		this.clazz = clazz;
	}

	@Override
	public T create(T entry) {
		LOGGER.info("--------AbstractCrudService : create -------------------- : BEGIN");
		List<FieldErrorVM> recordExist = this.isDuplicateRecordExists(entry);
		LOGGER.info("--------Duplicate Check Return Value -------------------- " + recordExist);
		if (recordExist != null && recordExist.size() > 0) {
			throw new BusinessException(recordExist);
		}
		// Triggering userActivity
		activityLogService.prepareAndInsert("-", "Create", SecurityUtils.getCurrentUser().getTrackingId(), serviceType);
		LOGGER.info("--------AbstractCrudService : create/Update-------------------- : END");
		entry = this.createOrUpdate(entry, true);
		return entry;
	}

	@Override
	public T update(T entry) {
		LOGGER.info("--------AbstractCrudService : update -------------------- : BEGIN");
		// Triggering userActivity
		activityLogService.prepareAndInsert("-", "Update", SecurityUtils.getCurrentUser().getTrackingId(), serviceType);
		LOGGER.info("--------AbstractCrudService : update -------------------- : END");
		entry = this.createOrUpdate(entry, false);
		return entry;
	}

	public abstract List<FieldErrorVM> isDuplicateRecordExists(T Entry);

	@Override
	public PageWrapperDTO<T> findBySearchTerm(SearchCriteria searchCriteria, Pageable pageRequest) {
		LOGGER.info("--------AbstractCrudService : findBySearchTerm -------------------- : BEGIN");
		PageWrapperDTO<T> pageWrapperDTO = filter(searchCriteria, pageRequest);
		LOGGER.info("--------AbstractCrudService : findBySearchTerm -------------------- : END");
		return pageWrapperDTO;
	}

	public abstract PageWrapperDTO<T> filter(SearchCriteria searchCriteria, Pageable pageable);

	public abstract T createOrUpdate(T entry, boolean isCreate);

	public abstract void delete(IdentityDTO dto, boolean isDelete);

	@Override
	public void delete(IdentityDTO dto) {
		LOGGER.info("--------AbstractCrudService : delete -------------------- : BEGIN");
		activityLogService.prepareAndInsert("-", "Delete", SecurityUtils.getCurrentUser().getTrackingId(), serviceType);
		this.delete(dto, true);
		LOGGER.info("--------AbstractCrudService : delete -------------------- : END");
	}

	@Override
	public T findById(Long id) {
		LOGGER.info("--------AbstractCrudService : findById -------------------- : BEGIN");
		T enty = mapEntityIntoDTO(id, false);
		LOGGER.info("--------AbstractCrudService : findById -------------------- : END");
		return enty;
	}

	public abstract T mapEntityIntoDTO(Long id, boolean isDelete);

}