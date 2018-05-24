package com.persistence.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the endpoints database table.
 * 
 */
@Entity
@Table(name="endpoints")
public class EndpointEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String endpoint;

	private String name;


	public EndpointEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndpoint() {
		return this.endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}