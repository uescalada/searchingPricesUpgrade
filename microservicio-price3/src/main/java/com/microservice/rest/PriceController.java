/*
 * 
 */
package com.microservice.rest;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.Price;
import com.microservice.repository.PriceRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

// TODO: Auto-generated Javadoc
/**
 * The Class PriceController.
 */
@RestController
@RequestMapping("price")
public class PriceController {

	/** The price repository. */
	@Autowired
	PriceRepository priceRepository;

	/**
	 * Listar.
	 *
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.GET)
	@Operation(summary = "Prices List")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Prices found"),
			@ApiResponse(responseCode = "204", description = "There are no prices") })
	public ResponseEntity<List<Price>> listar() {

		List<Price> prices = (List<Price>) priceRepository.findAll();

		if (prices.isEmpty()) {
			return ResponseEntity.noContent().build(); // error 204
		} else {
			return ResponseEntity.ok(prices); // ok 200
		}
	}

	/**
	 * Detailed price.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(path = "{id}", method = RequestMethod.GET)
	@Operation(summary = "Obtain Price by its id in Price Table")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Prices found"),
			@ApiResponse(responseCode = "204", description = "There are no prices") })
	public ResponseEntity<Price> detailedPrice(@PathVariable("id") Long id) {

		Price price = priceRepository.findById(id).orElse(null);

		if (price == null) {
			return ResponseEntity.noContent().build(); // error 204
		} else {
			return ResponseEntity.ok(price); // ok 200
		}
	}

	/**
	 * Delete price.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	@Operation(summary = "Delete a Price by its id in Price Table")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Price deleted"),
			@ApiResponse(responseCode = "204", description = "There are no prices") })
	public ResponseEntity<Price> deletePrice(@PathVariable("id") Long id) {

		Price price = priceRepository.findById(id).orElse(null);

		if (price == null) {
			return ResponseEntity.noContent().build(); // error 204
		} else {
			priceRepository.deleteById(id);
			return ResponseEntity.ok(price); // ok 200
		}
	}

	/**
	 * Creates the price.
	 *
	 * @param p the p
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.POST)
	@Operation(summary = "Create a new Price in Price Table")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Price created") })
	public ResponseEntity<Object> createPrice(@RequestBody Price p) {

		priceRepository.save(p);
		URI uri = URI.create(String.format("/price/%s", p.getId()));

		return ResponseEntity.created(uri).build(); // ok 200

	}

	/**
	 * Search by product id and brand id and date.
	 *
	 * @param productId the product id
	 * @param brandId the brand id
	 * @param dateValue the date value
	 * @return the response entity
	 * @throws ParseException the parse exception
	 */
	@GetMapping({ "/{productId}/{brandId}/{dateValue}", "" })
	@Operation(summary = "Obtain Object Data Price by productId & brandId & dateValue")
	public ResponseEntity<List<Price>> searchByProductIdAndBrandIdAndDate(@PathVariable("productId") int productId,
			@PathVariable("brandId") int brandId, @PathVariable("dateValue") String dateValue) throws ParseException {

		Date dateValueFormated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateValue);

		List<Price> prices = priceRepository
				.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(
						productId, brandId, dateValueFormated, dateValueFormated);

		ResponseEntity<List<Price>> respuesta = null;
		if (prices.isEmpty()) {
			respuesta = ResponseEntity.noContent().build(); // error 204
		} else {
			respuesta = ResponseEntity.ok(prices); // ok 200
		}

		return respuesta;
	}

	// query a utilizar
	//
	// select TOP 1 from prices where
	// brandId = paramBrandId AND productId = paramProductId AND
	// (start_date < paramDate < end_date)
	/**
	 * Search by product id and brand id and date string.
	 *
	 * @param productId the product id
	 * @param brandId the brand id
	 * @param dateValue the date value
	 * @return the string
	 * @throws ParseException the parse exception
	 */
	// order by price_list asc;
	@GetMapping({ "/showData/{productId}/{brandId}/{dateValue}", "" })
	@Operation(summary = "Obtain String Data Price by productId & brandId & dateValue")
	public String searchByProductIdAndBrandIdAndDateString(@PathVariable("productId") int productId,
			@PathVariable("brandId") int brandId, @PathVariable("dateValue") String dateValue) throws ParseException {

		Date dateValueFormated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateValue);

		List<Price> prices = priceRepository
				.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(
						productId, brandId, dateValueFormated, dateValueFormated);

		String respuesta = "";
		if (prices.isEmpty()) {
			respuesta = "No data found"; // error 204
		} else {
			// identificador de producto, identificador de cadena, tarifa a aplicar, fechas
			// de aplicación y precio final a aplicar
			respuesta = makingDataPriceResponse(prices.get(0)); // ok 200
		}

		return respuesta;
	}

	/**
	 * Making data price response.
	 *
	 * @param p the p
	 * @return the string
	 */
	private String makingDataPriceResponse(Price p) {

		String priceData = "";
		priceData += "Product  = " + p.getProductId();
		priceData += " -Cadena  = " + p.getBrandId();
		priceData += " -Price List = " + p.getPriceList();
		priceData += " -Start date = " + p.getStartDate();
		priceData += " -End date = " + p.getEndDate();
		priceData += " -Final Price = " + String.format("%.2f", p.getPrice());
		priceData += " -Currency = " + p.getCurr();
		return priceData;
	}

}
