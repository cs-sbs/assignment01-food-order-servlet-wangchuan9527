package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.getWriter().println("Error: order ID is required");
            return;
        }

        String idStr = pathInfo.substring(1);
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            resp.getWriter().println("Error: invalid order ID");
            return;
        }

        Order found = null;
        for (Order o : OrderCreateServlet.orders) {
            if (o.getId() == id) {
                found = o;
                break;
            }
        }

        if (found == null) {
            resp.getWriter().println("Error: order not found");
            return;
        }

        resp.getWriter().println("Order Detail");
        resp.getWriter().println();
        resp.getWriter().println("Order ID: " + found.getId());
        resp.getWriter().println("Customer: " + found.getCustomer());
        resp.getWriter().println("Food: " + found.getFood());
        resp.getWriter().println("Quantity: " + found.getQuantity());
    }
}
