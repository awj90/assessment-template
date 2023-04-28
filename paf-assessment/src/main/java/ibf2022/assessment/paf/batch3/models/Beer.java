package ibf2022.assessment.paf.batch3.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

// DO NOT MODIFY THIS FILE.

public class Beer {

	private int beerId;
	private String beerName;
	private String beerDescription;

	private int breweryId;
	private String breweryName;

	public int getBeerId() {
		return beerId;
	}
	public void setBeerId(int beerId) {
		this.beerId = beerId;
	}
	public String getBeerName() {
		return beerName;
	}
	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}
	public String getBeerDescription() {
		return beerDescription;
	}
	public void setBeerDescription(String beerDescription) {
		this.beerDescription = beerDescription;
	}
	public int getBreweryId() {
		return breweryId;
	}
	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}
	public String getBreweryName() {
		return breweryName;
	}
	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}

	@Override
	public String toString() {
		return "Beer [beerId=" + beerId + ", beerName=" + beerName + ", beerDescription=" + beerDescription
				+ ", breweryId=" + breweryId + ", breweryName=" + breweryName + "]";
	}

	public static Beer create(SqlRowSet rs) {
		Beer beer = new Beer();
		beer.setBeerId(rs.getInt("id"));
		beer.setBeerName(rs.getString("beer_name"));
		beer.setBeerDescription(rs.getString("descript"));
		beer.setBreweryId(rs.getInt("brewery_id"));
		beer.setBreweryName(rs.getString("brewery_name"));
		return beer;
	}
}
