package com.example.restservice.model;

import javax.persistence.*;

@Entity
@Table(name = "urls")
public class Url {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "hash")
	private String hash;

	@Column(name = "actual")
	private String actual;

	public Url() {

	}

	public Url(String hash, String actual) {
		this.hash = hash;
		this.actual = actual;
	}

	public long getId() {
		return id;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", hash=" + hash + ", actual=" + actual + "]";
	}
}