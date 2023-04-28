package ibf2022.assessment.paf.batch3.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Style> getStyles() {
		return beerRepository.getStyles();
	}

	public List<Beer> getBreweriesByBeer(int styleId) {
		return beerRepository.getBreweriesByBeer(styleId);
	}

	public Optional<Brewery> getBeersFromBrewery(int breweryId) {
		return beerRepository.getBeersFromBrewery(breweryId);
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public String placeOrder(Order order) {
		// TODO: Task 5 
		String orderId = UUID.randomUUID().toString().substring(0, 8);
		order.setOrderId(orderId);
		orderRepository.insertOrder(order);
		return orderId;
	}

}
