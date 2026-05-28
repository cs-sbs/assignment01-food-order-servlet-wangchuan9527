package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        if (customer == null || customer.isEmpty()
                || food == null || food.isEmpty()
                || quantityStr == null || quantityStr.isEmpty()) {
            resp.getWriter().println("Error: missing required parameters");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            resp.getWriter().println("Error: quantity must be a valid number");
            return;
        }

        Order order = new Order(customer, food, quantity);
        Order.addOrder(order);

        resp.getWriter().println("Order Created: " + order.getId());
    }
}
