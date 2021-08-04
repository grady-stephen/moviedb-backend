import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "TodoServlet", urlPatterns = "/new-address")
public class TodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        try{
            PrintWriter out = response.getWriter();
            out.println("<h1> hello big world </h1>");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
