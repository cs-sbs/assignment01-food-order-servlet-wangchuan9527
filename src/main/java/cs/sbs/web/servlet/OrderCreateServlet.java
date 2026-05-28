package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderCreateServlet extends HttpServlet {

    static final List<Order> orders = new ArrayList<>();
    static final AtomicInteger nextId = new AtomicInteger(1001);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        if (customer == null || customer.isBlank()) {
            resp.getWriter().println("Error: customer name is required");
            return;
        }
        if (food == null || food.isBlank()) {
            resp.getWriter().println("Error: food name is required");
            return;
        }
        if (quantityStr == null || quantityStr.isBlank()) {
            resp.getWriter().println("Error: quantity is required");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            resp.getWriter().println("Error: quantity must be a valid number");
            return;
        }

        if (quantity <= 0) {
            resp.getWriter().println("Error: quantity must be a positive number");
            return;
        }

        int id = nextId.getAndIncrement();
        Order order = new Order(id, customer, food, quantity);
        orders.add(order);

        resp.getWriter().println("Order Created: " + id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        var out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"zh-CN\">");
        out.println("<head><meta charset=\"UTF-8\"><title>Order List</title></head>");
        out.println("<body>");
        out.println("<h2>All Orders</h2>");
        if (orders.isEmpty()) {
            out.println("<p>No orders yet.</p>");
        } else {
            for (Order o : orders) {
                out.println("<p>Order #" + o.getId()
                        + " (<a href=\"/order/" + o.getId() + "\">Click to view details</a>)</p>");
            }
        }
        out.println("<p><a href=\"/\">Back to Home</a></p>");
        out.println("</body></html>");
    }
}
