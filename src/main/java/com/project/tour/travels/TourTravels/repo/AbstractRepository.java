package com.project.tour.travels.TourTravels.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long>, QuerydslPredicateExecutor<T> {

	List<T> findAll(Predicate predicate);

	List<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);

	List<T> findAll(OrderSpecifier<?>... orders);

	default T findOne(Long id) {
		return (T) findById(id).orElse(null);
	}

}