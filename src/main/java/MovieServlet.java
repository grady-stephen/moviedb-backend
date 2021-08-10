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
import java.sql.SQLException;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/JSON");

        try {
            // eventually get movies from the database
            PrintWriter out = response.getWriter();

//            Movie movie = new Movie(11, "Dostana", "2008",
//                    "Tarun Mansukhani", "Abhishek Bachchan, John Abraham", "tt1185420",
//                    "https://m.media-amazon.com/images/M/MV5BOTE0NDU1ZTctYjRjYS00OTEyLTkzOWQtNmRiNDg5ZDU1ODBiXkEyXkFqcGdeQXVyNjQ2MjQ5NzM@._V1_SX300.jpg", "Comedy, Drama, Romance",
//                    "Two straight guys pretend to be gay in order to secure a Miami apartment. When both of them fall for their roommate Neha, hilarity ensues as they strive to convince one and all that they're gay whilst secretly trying to win her heart");

            // make movies dao
            MoviesDao moviesDao = DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL);

            // turn into JSON
            String movieString = new Gson().toJson(moviesDao.all());

            // inject into response
            out.println(movieString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/JSON");
        PrintWriter out = null;
        try {
            out = response.getWriter();
//            get the stream of characters from the request(eventually becomes our movie)
            BufferedReader reader = request.getReader();

            // turn that stream into an array of movies
            Movie[] movies = new Gson().fromJson(reader, Movie[].class);

            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).insertAll(movies);
            for(Movie movie : movies){
                System.out.println(movie.getId());
                System.out.println(movie.getTitle());
                System.out.println(movie.getPlot());
                System.out.println(movie.getDirector());
                System.out.println(movie.getActors());
                System.out.println(movie.getGenre());
                System.out.println(movie.getRating());
                System.out.println(movie.getYear());
                System.out.println(movie.getPoster());
                System.out.println("----------------------------");
            }

        } catch( Exception e){
            System.out.println(e.getMessage());
        }

        // write a message on successful POST (200)
        out.println( new Gson().toJson("{message: \"Movies POST was Successful\"}"));
        response.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/JSON");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            Movie movie = new Gson().fromJson(request.getReader(), Movie.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).update(movie);
        } catch (SQLException e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(400);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \"Movie UPDATE was successful\"}"));
        response.setStatus(200);

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/JSON");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            var id = new Gson().fromJson(request.getReader(), int.class);
            DaoFactory.getMoviesDao(DaoFactory.ImplType.MYSQL).destroy(id);

        } catch (Exception e) {
            out.println(new Gson().toJson(e.getLocalizedMessage()));
            response.setStatus(500);
            e.printStackTrace();
            return;
        }

        out.println(new Gson().toJson("{message: \"Movie DELETE was successful\"}"));
        response.setStatus(200);
    }
}
