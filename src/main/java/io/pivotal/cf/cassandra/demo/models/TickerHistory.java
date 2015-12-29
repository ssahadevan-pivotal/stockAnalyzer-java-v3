package io.pivotal.cf.cassandra.demo.models;

/*
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
*/

import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class TickerHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private String id;

        @JsonSerialize
	private String name;

        @JsonSerialize
	private String ticker;

        @JsonSerialize
	private String price;

        @JsonSerialize
	private String pe;

        @JsonSerialize
	private String recommendation;

        @JsonSerialize
	private String yield;
	
	/* To Do
	@Column
	@CreatedDate
	private DateTime createdDate;
	*/

	public TickerHistory() {
	}

	public TickerHistory(String ticker
                                     , String id
			             , String name
			             , String price
			             , String pe
			             , String recommendation
			             , String yield
			             ) {
                this.id = id ;
		this.name = name;
		this.ticker = ticker;
		this.price = price;
		this.pe = pe;
		this.recommendation = recommendation;
		this.yield = yield;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPe() {
		return pe;
	}

	public void setPe(String pe) {
		this.pe = pe;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getYield() {
		return yield;
	}

	public void setYield(String yield) {
		this.yield = yield;
	}
}
