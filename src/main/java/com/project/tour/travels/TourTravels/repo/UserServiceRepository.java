package com.project.tour.travels.TourTravels.repo;

import org.springframework.stereotype.Repository;

import com.project.tour.travels.TourTravels.domain.UserService;

@Repository
public interface UserServiceRepository extends AbstractRepository<UserService> {

	UserService findByCode(String code);

}