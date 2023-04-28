package ibf2022.assessment.paf.batch3.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

public class Order {
    private String orderId;
    private Date date;
    private int breweryId;
    private List<OrderItem> orderItems = new LinkedList<>();

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getBreweryId() {
        return breweryId;
    }
    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public String toJsonString() {

        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        
        for (OrderItem orderItem : this.getOrderItems()) {
            jsonArrayBuilder.add(orderItem.toJsonObjectBuilder());
        }

        JsonArray jsonArray = jsonArrayBuilder.build();

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                                .add("orderId", this.getOrderId())
                                .add("date", this.getDate().toString())
                                .add("breweryId", this.getBreweryId())
                                .add("orders", jsonArray.toString());
        return jsonObjectBuilder.build().toString();
                                
    }
}
