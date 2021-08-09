package data;
import java.sql.SQLException;
import java.util.List;

public interface MoviesDao {
    List<Movie> all() throws SQLException; // all returns a list of movie objs
    Movie findOne(int id); // takes in one id returns a movie obj
    void insert(Movie movie); // takes in a movie and updates movies
    void insertAll(Movie[] movies) throws SQLException; // takes in an array of movies to insert
    void update(Movie movie) throws SQLException; // takes in a movie to update its obj
    void destroy(int id) throws SQLException; // takes in an id to delete that movie obj
}