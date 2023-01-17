package example;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBAccessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/add", loadOnStartup = 1)
@ServletSecurity(
        @HttpConstraint(rolesAllowed = {"employee"})
)
public class AddServlet extends HttpServlet {

    @EJB
    private Products products;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String name = req.getParameter("productname");
        String price = req.getParameter("productprice");
        String numInStock = req.getParameter("productstock");

        try {
            products.addProduct(name, Double.valueOf(price), Integer.valueOf(numInStock));
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<html>");
                writer.println("  <head><title>Update WildFly Widget Inventory</title></head>");
                writer.println("  <body>");
                writer.println("    <h2>Update WildFly Widgets Inventory</h2>");
                writer.println("    <p><font size=\"5\" color=\"blue\">New product was successfully added!</font></p>");
                writer.println("    <br/>");
                writer.println("    <form>");
                writer.println("      <input type=\"button\" value=\"Back\" onclick=\"window.location.href='./index.html'\" style=\"font-size : 16px;\"/>");
                writer.println("    </form>");
                writer.println("  </body>");
                writer.println("</html>");
            }
        } catch (EJBAccessException e) {
            try (PrintWriter writer = resp.getWriter()) {
                writer.println("<html>");
                writer.println("  <head><title>Update WildFly Widget Inventory</title></head>");
                writer.println("  <body>");
                writer.println("    <h2>Update WildFly Widgets Inventory</h2>");
                writer.println("    <p><font size=\"5\" color=\"red\">New product was not added. Only an admin can add a new product!</font></p>");
                writer.println("    <br/>");
                writer.println("    <form>");
                writer.println("      <input type=\"button\" value=\"Back\" onclick=\"window.location.href='./index.html'\" style=\"font-size : 16px;\"/>");
                writer.println("    </form>");
                writer.println("  </body>");
                writer.println("</html>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<html>");
            writer.println("  <head><title>Update WildFly Widget Inventory</title></head>");
            writer.println("<style>");
            writer.println("  input {");
            writer.println("    font-size: 24px");
            writer.println("  }");
            writer.println("</style>");
            writer.println("  <body>");
            writer.println("    <h2>Update WildFly Widgets Inventory</h2>");
            writer.println("    <p><font size=\"5\" color=\"blue\">Only users with the \"admin\" role can successfully add a product.</font></p>");
            writer.println("    <form action=\"./add\" method=\"post\">");
            writer.println("      <font size=\"5\">Product name:</font>");
            writer.println("      <br/>");
            writer.println("      <input type=\"text\" name=\"productname\">");
            writer.println("      <br/>");
            writer.println("      <br/>");
            writer.println("      <font size=\"5\">Product price:</font>");
            writer.println("      <br/>");
            writer.println("      <input type=\"text\" name=\"productprice\">");
            writer.println("      <br/>");
            writer.println("      <br/>");
            writer.println("      <font size=\"5\">Product stock:</font>");
            writer.println("      <br/>");
            writer.println("      <input type=\"text\" name=\"productstock\">");
            writer.println("      <br/>");
            writer.println("      <br/>");
            writer.println("      <input type=\"submit\" value=\"Submit\">");
            writer.println("    </form>");
            writer.println("  </body>");
            writer.println("</html>");
        }
    }

}
