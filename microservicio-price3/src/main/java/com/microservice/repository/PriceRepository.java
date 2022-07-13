package com.microservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.microservice.entity.Price;

// TODO: Auto-generated Javadoc
/**
 * The Interface PriceRepository.
 */
public interface PriceRepository extends CrudRepository<Price, Long> {

	/**
	 * Find prices by product id and brand id.
	 *
	 * @param productId the product id
	 * @param brandId   the brand id
	 * @return the list
	 */
	List<Price> findPricesByProductIdAndBrandId(int productId, int brandId);

	/**
	 * Find by product id.
	 *
	 * @param productId the product id
	 * @return the list
	 */
	List<Price> findByProductId(int productId);

	/**
	 * Find by price.
	 *
	 * @param price the price
	 * @return the list
	 */
	List<Price> findByPrice(double price);

	/**
	 * Find by priority.
	 *
	 * @param i the i
	 * @return the list
	 */
	List<Price> findByPriority(int i);

	/**
	 * Find prices by product id and brand id and start date.
	 *
	 * @param productId the product id
	 * @param brandId   the brand id
	 * @param fecha     the fecha
	 * @return the list
	 */
	List<Price> findPricesByProductIdAndBrandIdAndStartDate(int productId, int brandId, Date fecha);

	/**
	 * Find prices by product id and brand id and start date less than and end date
	 * greater than order by priority asc.
	 *
	 * @param productId the product id
	 * @param brandId   the brand id
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @return the list
	 */
	List<Price> findPricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(
			int productId, int brandId, Date startDate, Date endDate);

	/**
	 * Find top 1 prices by product id and brand id and start date less than and end
	 * date greater than order by priority asc.
	 *
	 * @param productId the product id
	 * @param brandId   the brand id
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @return the list
	 */
	List<Price> findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(
			int productId, int brandId, Date startDate, Date endDate);

}
