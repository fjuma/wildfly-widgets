package example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/inventory", loadOnStartup = 1)
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"employee"})
)
public class InventoryServlet extends HttpServlet {

    @EJB
    private Products products;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<html>");
            writer.println("  <head><title>WildFly Widget Inventory</title></head>");
            writer.println("<style>");
            writer.println("  table, th, td {");
            writer.println("    border: 1px solid black;");
            writer.println("    border-collapse: collapse;");
            writer.println("  }");
            writer.println("  th, td {");
            writer.println("    padding: 5px;");
            writer.println("    font-size: 18px");
            writer.println("  }");
            writer.println("</style>");
            writer.println("  <body>");
            writer.println("    <h2>WildFly Widgets Inventory</h2>");
            writer.println("    <p><font size=\"5\" color=\"blue\">Only users with the \"employee\" role can access this page.</font></p>");
            writer.println("    <table style=\"width:50%\">");
            writer.println("      <tr>");
            writer.println("        <th>Product</th>");
            writer.println("        <th>Price</th>");
            writer.println("        <th>Number in stock</th>");
            writer.println("      </tr>");
            for (Product product : products.getProducts()) {
                writer.println("  <tr>");
                writer.println("    <td>" + product.getName() + "</td>");
                writer.println("    <td>" + product.getPrice() + "</td>");
                writer.println("    <td>" + product.getNumInStock() + "</td>");
                writer.println("  </tr>");
            }
            writer.println("    </table>");
            writer.println("    <br/>");
            writer.println("    <form>");
            writer.println("      <input type=\"button\" value=\"Back\" onclick=\"window.location.href='./index.html'\" style=\"font-size : 20px;\"/>");
            writer.println("    </form>");
            writer.println("  </body>");
            writer.println("</html>");
        }
    }

}
