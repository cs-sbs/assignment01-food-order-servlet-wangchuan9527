package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuListServlet extends HttpServlet {

    private final List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        menuItems.add(new MenuItem("Fried Rice", 8));
        menuItems.add(new MenuItem("Fried Noodles", 9));
        menuItems.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String nameFilter = req.getParameter("name");
        PrintWriter out = resp.getWriter();

        out.println("Menu List:");
        out.println();

        boolean hasFilter = nameFilter != null && !nameFilter.isBlank();
        int index = 1;
        for (MenuItem item : menuItems) {
            if (!hasFilter
                    || item.getName().toLowerCase().contains(nameFilter.toLowerCase())) {
                out.println(index + ". " + item.getName() + " - $" + item.getPrice());
                index++;
            }
        }

        if (hasFilter && index == 1) {
            out.println("No items found.");
        }
    }
}
