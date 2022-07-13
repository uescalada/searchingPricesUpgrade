/*
 * 
 */
package com.microservice.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Price {
	@Id
	private Long id;
	private int brandId;
	private int priceList;
	private int productId;
	private int priority;
	private double price;
	private String curr;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	private final SimpleDateFormat ownDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
	private java.util.Date parseDate(String timestampODF) {
		try {
			return ownDateFormat.parse(timestampODF);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Price(Long id, int brandId, int priceList, int productId, int priority, double price, String curr,
			String startDate, String endDate) {
		super();
		this.id = id;
		this.brandId = brandId;
		this.priceList = priceList;
		this.productId = productId;
		this.priority = priority;
		this.price = price;
		this.curr = curr;
		this.startDate = parseDate(startDate);
		this.endDate = parseDate(endDate);
	}

}
