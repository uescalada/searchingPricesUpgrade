/*
 * 
 */
package com.microservice.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservice.entity.Price;

// TODO: Auto-generated Javadoc
/**
 * The Class PriceRepositoryTest.
 */
@SpringBootTest
public class PriceRepositoryTest {

	/** The price repository. */
	@Autowired
	private PriceRepository priceRepository;

	/**
	 * Find prices by product id and brand id test.
	 */
	@Test
	void findPricesByProductIdAndBrandIdTest() {
		List<Price> prices = priceRepository.findPricesByProductIdAndBrandId(35455, 1);
		Assertions.assertNotNull(prices);
		Assertions.assertTrue(4 == prices.size());
	}

	/**
	 * Find by price test.
	 */
	@Test
	void findByPriceTest() {
		List<Price> prices = priceRepository.findByPrice(35.50);
		Assertions.assertNotNull(prices);
		Assertions.assertTrue(1 == prices.size());
	}

	/**
	 * Find by priority test.
	 */
	@Test
	void findByPriorityTest() {
		List<Price> prices = priceRepository.findByPriority(8);
		Assertions.assertNotNull(prices);
		Assertions.assertTrue(prices.isEmpty());
	}

	/**
	 * Find top 1 prices by product id and brand id and start date less than and end
	 * date greater than test 1.
	 *
	 * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	void findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanTest1() throws ParseException {
		Date fechaTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 10:00:00");

		List<Price> prices = priceRepository
				.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(35455,
						1, fechaTest, fechaTest);
		Assertions.assertNotNull(prices);// trajo 1, l primera es la lista 1 (priority 0)
		Assertions.assertTrue(1 == prices.get(0).getPriceList());
	}

	/**
	 * Find top 1 prices by product id and brand id and start date less than and end
	 * date greater than test 2.
	 * 
	 * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanTest2() throws ParseException {
		Date fechaTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 16:00:00");

		List<Price> prices = priceRepository
				.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(35455,
						1, fechaTest, fechaTest);
		Assertions.assertNotNull(prices);// trajo 2 (lista UNO y DOS), l primera es la lista 1 (priority 0)
		Assertions.assertTrue(1 == prices.get(0).getPriceList());
	}

	/**
	 * Find top 1 prices by product id and brand id and start date less than and end
	 * date greater than test 3.
	 * 
	 * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanTest3() throws ParseException {
		Date fechaTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 21:00:00");

		List<Price> prices = priceRepository
				.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(35455,
						1, fechaTest, fechaTest);
		Assertions.assertNotNull(prices);// trajo 1 (lista UNO), l primera es la lista 1 (priority 0)
		Assertions.assertTrue(1 == prices.get(0).getPriceList());
	}

	/**
	 * Find top 1 prices by product id and brand id and start date less than and end
	 * date greater than test 4.
	 * 
	 * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanTest4() throws ParseException {
		Date fechaTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 10:00:00");

		List<Price> prices = priceRepository
				.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(35455,
						1, fechaTest, fechaTest);
		Assertions.assertNotNull(prices); // trajo 2 ((lista UNO y TRES), l primera es la lista 1 (priority 0)
		Assertions.assertTrue(1 == prices.get(0).getPriceList());
	}

	/**
	 * Find top 1 prices by product id and brand id and start date less than and end
	 * date greater than test 5.
	 * 
	 * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanTest5() throws ParseException {
		Date fechaTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-16 21:00:00");

		List<Price> prices = priceRepository
				.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(35455,
						1, fechaTest, fechaTest);
		Assertions.assertNotNull(prices);// trajo 2 (lista UNO y CUATRO), l primera es la lista 1 (priority 0)
		Assertions.assertTrue(1 == prices.get(0).getPriceList());
	}

}
