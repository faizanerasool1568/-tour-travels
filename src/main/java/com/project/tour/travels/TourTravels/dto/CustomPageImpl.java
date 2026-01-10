package com.project.tour.travels.TourTravels.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomPageImpl<T> extends PageImpl<T> {

	private static final long serialVersionUID = 1L;
	private int number;
	private int size;
	private int totalPages;
	private int numberOfElements;
	private long totalElements;
	private boolean previous;
	private boolean first;
	private boolean next;
	private boolean last;
	private List<T> content;
	private List<CustomSortImpl> sort = new ArrayList<CustomSortImpl>();

	public CustomPageImpl() {
		super(new ArrayList<T>());
	}

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CustomPageImpl(@JsonProperty("content") List<T> content, @JsonProperty("number") int number,
			@JsonProperty("size") int size, @JsonProperty("totalPages") int totalPages,
			@JsonProperty("numberOfElements") int numberOfElements, @JsonProperty("totalElements") long totalElements,
			@JsonProperty("previous") boolean previous, @JsonProperty("first") boolean first,
			@JsonProperty("next") boolean next, @JsonProperty("last") boolean last,
			@JsonProperty("sort") List<CustomSortImpl> sort) {
		super(content);
		this.number = number;
		this.size = size;
		this.totalPages = totalPages;
		this.numberOfElements = numberOfElements;
		this.totalElements = totalElements;
		this.previous = previous;
		this.first = first;
		this.next = next;
		this.last = last;
		this.content = content;
		this.sort = sort;
	}

	@Override
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	@Override
	@JsonProperty(value = "totalElements")
	public long getTotalElements() {
		return totalElements;
	}

	@JsonProperty(value = "totalElements")
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isPrevious() {
		return previous;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	@Override
	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	@Override
	public Sort getSort() {
		Sort mySort = null;
		for (CustomSortImpl mySortDTO : sort) {
			if (mySort == null) {
				mySort = Sort.by(mySortDTO.getDirection(), mySortDTO.getProperty());
			} else {
				mySort.and(Sort.by(mySortDTO.getDirection(), mySortDTO.getProperty()));
			}

		}
		return mySort;
	}

	public void setSort(List<CustomSortImpl> sort) {
		this.sort = sort;
	}

	public Page<T> pageImpl() {
		return new PageImpl<>(getContent(), PageRequest.of(getNumber(), getSize(), getSort()), getTotalElements());
	}
}