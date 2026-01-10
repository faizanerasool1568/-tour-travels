package com.project.tour.travels.TourTravels.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageWrapperDTO<T> extends AbstractDTO {
	private static final long serialVersionUID = -613670860156004155L;

	private CustomPageImpl<T> page;
	private String infoMessage;
	private String errorMessage;

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public PageWrapperDTO() {
		super();
	}

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PageWrapperDTO(@JsonProperty("page") CustomPageImpl<T> page) {
		super();
		this.page = page;
	}

	public PageWrapperDTO(@JsonProperty("page") Page<T> page) {
		CustomPageImpl<T> customPage = new CustomPageImpl<T>();
		customPage.setContent(page.getContent());
		customPage.setFirst(page.isFirst());
		customPage.setLast(page.isLast());
		customPage.setNext(page.hasNext());
		customPage.setPrevious(page.hasPrevious());
		customPage.setNumber(page.getNumber());
		customPage.setNumberOfElements(page.getNumberOfElements());
		customPage.setSize(page.getSize());
		customPage.setTotalElements(page.getTotalElements());
		customPage.setTotalPages(page.getTotalPages());
		Sort sort = page.getSort();
		List<CustomSortImpl> orders = new ArrayList<CustomSortImpl>();
		if (sort != null) {
			for (Iterator<Order> it = sort.iterator(); it.hasNext();) {
				Order order = it.next();
				CustomSortImpl sortImpl = new CustomSortImpl();
				sortImpl.setAscending(order.isAscending());
				sortImpl.setDirection(order.getDirection());
				sortImpl.setIgnoreCase(order.isIgnoreCase());
				sortImpl.setNullHandling(order.getNullHandling());
				sortImpl.setProperty(order.getProperty());
				orders.add(sortImpl);
			}
			customPage.setSort(orders);
		}
		this.page = customPage;
	}

	public PageWrapperDTO(Page<T> page, Long totalElements) {
		CustomPageImpl<T> customPage = new CustomPageImpl<T>();
		customPage.setContent(page.getContent());
		customPage.setFirst(page.isFirst());
		customPage.setLast(page.isLast());
		customPage.setNext(page.hasNext());
		customPage.setPrevious(page.hasPrevious());
		customPage.setNumber(page.getNumber());
		customPage.setNumberOfElements(page.getNumberOfElements());
		customPage.setSize(page.getSize());
		customPage.setTotalElements(totalElements);
		if (totalElements.longValue() == page.getTotalElements())
			customPage.setTotalPages(page.getTotalPages());
		else
			customPage.setTotalPages(page.getTotalPages() + 1);
		Sort sort = page.getSort();
		List<CustomSortImpl> orders = new ArrayList<CustomSortImpl>();
		if (sort != null) {
			for (Iterator<Order> it = sort.iterator(); it.hasNext();) {
				Order order = it.next();
				CustomSortImpl sortImpl = new CustomSortImpl();
				sortImpl.setAscending(order.isAscending());
				sortImpl.setDirection(order.getDirection());
				sortImpl.setIgnoreCase(order.isIgnoreCase());
				sortImpl.setNullHandling(order.getNullHandling());
				sortImpl.setProperty(order.getProperty());
				orders.add(sortImpl);
			}
			customPage.setSort(orders);
		}
		this.page = customPage;
	}

	@JsonProperty("page")
	public Page<T> getPage() {
		return page;
	}

	public void setPage(CustomPageImpl<T> page) {
		this.page = page;
	}

}