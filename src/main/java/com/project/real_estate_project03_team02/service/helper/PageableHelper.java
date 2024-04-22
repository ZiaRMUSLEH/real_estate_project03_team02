package com.project.real_estate_project03_team02.service.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * The PageableHelper class provides utility methods for creating Pageable objects
 * with various properties such as page number, page size, and sorting criteria.
 */
@Component
public class PageableHelper {

	/**
	 * Constructs a Pageable object with specified properties including page number,
	 * page size, and sorting criteria.
	 *
	 * @param page the page number (zero-based index)
	 * @param size the size of each page
	 * @param sort the property to sort by
	 * @param type the sorting type (either "asc" for ascending or "desc" for descending)
	 * @return a Pageable object configured with the specified properties
	 */
	public Pageable getPageableWithProperties(int page,int size, String sort,String type ){
		Pageable pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
		if(Objects.equals(type,"desc")){
			pageable = PageRequest.of(page,size, Sort.by(sort).descending());
		}
		return pageable;
	}

	/**
	 * Constructs a Pageable object with default properties, including page number
	 * and page size.
	 *
	 * @param page the page number (zero-based index)
	 * @param size the size of each page
	 * @return a Pageable object configured with default properties
	 */
	public Pageable getPageableWithProperties(int page, int size) {
		return PageRequest.of(page, size);
	}
}
