package ibf2022.assessment.paf.batch3.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

@Repository
public class BeerRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		
		List<Style> styles = new LinkedList<>();

		String query = "select beers.style_id, count(beers.style_id), styles.style_name from beers join styles on beers.style_id = styles.id group by style_id order by count(beers.style_id) desc, styles.style_name asc";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
		
		while (rs.next()) {
            styles.add(Style.create(rs));
        }
        return styles;
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(int styleId) {
		// TODO: Task 3

		List<Beer> beers = new LinkedList<>();

		String query = "select beers.id, beers.name as beer_name, beers.descript, beers.brewery_id, breweries.name as brewery_name from beers join breweries on beers.brewery_id = breweries.id where beers.style_id = ? order by beers.name asc";
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query, styleId);
		
		while (rs.next()) {
            beers.add(Beer.create(rs));
        }
        return beers;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(/* You can add any number of parameters here */) {
		// TODO: Task 4

		return Optional.empty();
	}
}
