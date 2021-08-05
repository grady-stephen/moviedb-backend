import com.google.gson.Gson;
import data.DaoFactory;
import data.Movie;
import data.MoviesDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        try {
            //get object which can write to the response
            PrintWriter out = response.getWriter();

            //eventually get movies from the database
            Movie movie = new Movie(2, "King Kong", "1942", "Harry Carey", "Fay Ray", "Erlsa Banks",
                    "8675309", "there aint one", "cheap", "gorilla on skyscraper");

            MoviesDao moviesDao = DaoFactory.getMoviesDao();

            //run into a json string
            String movieString = new Gson().toJson(movie);


            //inject into response
            out.println(movieString);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            //get the stream of characters from the request (eventually becomes our movie)
            BufferedReader reader = request.getReader();
            //turn that stream into an array of Movies
            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            for (Movie movie : movies) {
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getActors());
                System.out.println(movie.getDirector());
                System.out.println(movie.getGenre());
                System.out.println(movie.getPlot());
                System.out.println(movie.getPoster());
                System.out.println("********************************************");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        out.println(new Gson().toJson("{message: \"Movies POST was successful\""));
        response.setStatus(200);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try {
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            for (Movie movie : movies) {
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getActors());
                System.out.println(movie.getDirector());
                System.out.println(movie.getGenre());
                System.out.println(movie.getPlot());
                System.out.println(movie.getPoster());
                System.out.println("********************************************");
            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(new Gson().toJson("{message: \"Movies PUT was successful\""));
        response.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");

        PrintWriter out = null;

        try {like
            out = response.getWriter();

            BufferedReader reader = request.getReader();

            int id = new Gson().fromJson(reader, int.class);

            System.out.println("The movie id to delete: " + id);

        }catch (Exception ex){
            System.out.println(ex.getMessage());

            //write a meaningful response body and set the status ode to 200
            out.println(new Gson().toJson("{message: \"Movies PUT was successful\""));
        }
    }


}
