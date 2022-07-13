package com.microservice.rest;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.microservice.entity.Price;

// TODO: Auto-generated Javadoc
/**
 * The Class PriceControllerTest.
 */
@SpringBootTest
public class PriceControllerTest {

	/** The price controller. */
	@Autowired
	private PriceController priceController;

	/**
	 * Listar test.
	 */
	@Test
	void listarTest() {
		ResponseEntity<List<Price>> prices = (ResponseEntity<List<Price>>) priceController.listar();
		// The result is 4 registries
		Assertions.assertNotNull(prices);
		Assertions.assertTrue(4 == prices.getBody().size());
	}

	/**
	 * Search by product id and brand id and date string test 1.
	 * 
	 * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1
	 * The result expected is 35.50 -> True
	 *
	 * @param productId     =35455
	 * @param brandId       =1
	 * @param dateToAnalyze ="2020-06-14 10:00:00"
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void searchByProductIdAndBrandIdAndDateStringTest1() throws ParseException {
		int productId = 35455;
		int brandId = 1;
		String dateToAnalyze = "2020-06-14 10:00:00";
		String result = priceController.searchByProductIdAndBrandIdAndDateString(productId, brandId, dateToAnalyze);

		Assertions.assertNotNull(result);
		Assertions.assertTrue(Double.parseDouble(getPriceFromString(result)) == 35.50);
	}

	/**
	 * Search by product id and brand id and date string test 2.
	 * 
	 * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1
	 * The result expected is 35.50 -> True
	 *
	 * @param productId     =35455
	 * @param brandId       =1
	 * @param dateToAnalyze ="2020-06-14 16:00:00"
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void searchByProductIdAndBrandIdAndDateStringTest2() throws ParseException {
		int productId = 35455;
		int brandId = 1;
		String dateToAnalyze = "2020-06-14 16:00:00";
		String result = priceController.searchByProductIdAndBrandIdAndDateString(productId, brandId, dateToAnalyze);

		Assertions.assertNotNull(result);
		Assertions.assertTrue(Double.parseDouble(getPriceFromString(result)) == 35.50);
	}

	/**
	 * Search by product id and brand id and date string test 3.
	 * 
	 * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1
	 * The result expected is 35.50 -> True
	 *
	 * @param productId     =35455
	 * @param brandId       =1
	 * @param dateToAnalyze ="2020-06-14 21:00:00"
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void searchByProductIdAndBrandIdAndDateStringTest3() throws ParseException {
		int productId = 35455;
		int brandId = 1;
		String dateToAnalyze = "2020-06-14 21:00:00";
		String result = priceController.searchByProductIdAndBrandIdAndDateString(productId, brandId, dateToAnalyze);

		Assertions.assertNotNull(result);
		Assertions.assertTrue(Double.parseDouble(getPriceFromString(result)) == 35.50);
	}

	/**
	 * Search by product id and brand id and date string test 4.
	 * 
	 * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1
	 * The result expected is 35.50 -> True
	 * 
	 * @param productId     =35455
	 * @param brandId       =1
	 * @param dateToAnalyze ="2020-06-15 10:00:00"
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void searchByProductIdAndBrandIdAndDateStringTest4() throws ParseException {
		int productId = 35455;
		int brandId = 1;
		String dateToAnalyze = "2020-06-15 10:00:00";
		String result = priceController.searchByProductIdAndBrandIdAndDateString(productId, brandId, dateToAnalyze);

		Assertions.assertNotNull(result);
		Assertions.assertTrue(Double.parseDouble(getPriceFromString(result)) == 35.50);
	}

	/**
	 * Search by product id and brand id and date string test 5.
	 * 
	 * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1
	 * The result expected is 35.50 -> True
	 * 
	 * @param productId     =35455
	 * @param brandId       =1
	 * @param dateToAnalyze ="2020-06-16 21:00:00"
	 * 
	 * @throws ParseException the parse exception
	 */
	@Test
	void searchByProductIdAndBrandIdAndDateStringTest5() throws ParseException {
		int productId = 35455;
		int brandId = 1;
		String dateToAnalyze = "2020-06-16 21:00:00";
		String result = priceController.searchByProductIdAndBrandIdAndDateString(productId, brandId, dateToAnalyze);

		Assertions.assertNotNull(result);
		Assertions.assertTrue(Double.parseDouble(getPriceFromString(result)) == 35.50);
	}

	/**
	 * Gets the price from string.
	 *
	 * @param searchResult the search result
	 * @return the price from string
	 */
	private String getPriceFromString(String searchResult) {

		int found = searchResult.indexOf("Final Price");
		return searchResult.substring(searchResult.indexOf("=", found) + 1, searchResult.indexOf("-", found)).trim();
	}

}
