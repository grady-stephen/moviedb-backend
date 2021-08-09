package data;

public class DaoFactory {
    private static MoviesDao moviesDao;
//    private static Config config = new Config();

    public enum ImplType {MYSQL, IN_MEMORY}; // 2 values

    public static MoviesDao getMoviesDao(ImplType implementationType) {

        switch (implementationType) {
            case IN_MEMORY: { //yet we have one switch case. We'll get to that!
                moviesDao = new InMemoryMoviesDao();
                if(moviesDao == null){
                    moviesDao = new InMemoryMoviesDao();
                }
            }
        }
        return moviesDao;
    }
}