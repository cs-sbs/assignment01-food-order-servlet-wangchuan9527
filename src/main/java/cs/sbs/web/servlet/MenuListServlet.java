package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MenuListServlet extends HttpServlet {

    private static final List<MenuItem> menu = new ArrayList<>();

    static {
        menu.add(new MenuItem("Fried Rice", 8));
        menu.add(new MenuItem("Fried Noodles", 9));
        menu.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");

        String nameFilter = req.getParameter("name");

        List<MenuItem> result = new ArrayList<>();
        if (nameFilter != null && !nameFilter.isEmpty()) {
            for (MenuItem item : menu) {
                if (item.getName().toLowerCase().contains(nameFilter.toLowerCase())) {
                    result.add(item);
                }
            }
        } else {
            result.addAll(menu);
        }

        PrintWriter out = resp.getWriter();
        if (result.isEmpty()) {
            out.println("No menu items found");
        } else {
            out.println("Menu List:");
            out.println();
            for (int i = 0; i < result.size(); i++) {
                MenuItem item = result.get(i);
                out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
            }
        }
    }
}
