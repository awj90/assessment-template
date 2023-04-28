package ibf2022.assessment.paf.batch3.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.assessment.paf.batch3.exceptions.DatabaseException;
import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.repositories.OrderRepository;

@Service
public class BeerService {

	@Autowired
	BeerRepository beerRepository;

	@Autowired
	OrderRepository orderRepository;

	public List<Style> getStyles() throws DatabaseException {
		try {
			return beerRepository.getStyles();
		} catch (DatabaseException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}

	public List<Beer> getBreweriesByBeer(int styleId) throws DatabaseException {
		try {
			return beerRepository.getBreweriesByBeer(styleId);
		} catch (DatabaseException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}

	public Optional<Brewery> getBeersFromBrewery(int breweryId) throws DatabaseException {
		try {
			return beerRepository.getBeersFromBrewery(breweryId);
		} catch (DatabaseException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(Order order) throws DatabaseException {
		// TODO: Task 5 
		try {
			String orderId = UUID.randomUUID().toString().substring(0, 8);
			order.setOrderId(orderId);
			orderRepository.insertOrder(order);
			return orderId;
		} catch (DatabaseException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}

}
