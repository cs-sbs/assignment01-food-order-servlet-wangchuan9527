package cs.sbs.web.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private static final List<Order> orders = new ArrayList<>();
    private static int nextId = 1001;

    private int id;
    private String customer;
    private String food;
    private int quantity;

    public Order(String customer, String food, int quantity) {
        this.id = nextId++;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getFood() {
        return food;
    }

    public int getQuantity() {
        return quantity;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static Order findById(int id) {
        for (Order o : orders) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }
}
