package ibf2022.assessment.paf.batch3.models;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.constraints.Min;

public class OrderItem {
    private int beerId;

    @Min(value=1, message="Fields entered must be greater than 0")
    private int quantity;

    public OrderItem() {}

    public OrderItem(int beerId, @Min(value = 1, message = "Fields entered must be greater than 0") int quantity) {
        this.beerId = beerId;
        this.quantity = quantity;
    }

    public int getBeerId() {
        return beerId;
    }
    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public JsonObjectBuilder toJsonObjectBuilder() {
        return Json.createObjectBuilder()
                    .add("beerId", this.getBeerId())
                    .add("quantity", this.getQuantity());
    }
}
