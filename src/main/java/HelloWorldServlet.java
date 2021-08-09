import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/hello-world")
public class HelloWorldServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        what kind of response we want
        response.setContentType("text/html");

        try{
            PrintWriter out = response.getWriter();
            out.println("Hello, World!");

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }



    }

}
