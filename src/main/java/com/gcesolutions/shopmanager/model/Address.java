package com.gcesolutions.shopmanager.model;

/**
 * Represents the address.
 * 
 * @author Nand Joshi
 *
 */
public class Address {
	private String address;
	private String city;
	private String state;
	private int zipCode;
	private String country;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return address + " " + city + " " + state + " " + zipCode + " " + country;
	}

}
