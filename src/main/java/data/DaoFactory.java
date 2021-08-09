package data;

public class DaoFactory {
    private static MoviesDao moviesDao;
    private static Config config = new Config();

    public enum ImplType {MYSQL, IN_MEMORY}; // 2 values

    public static MoviesDao getMoviesDao(ImplType implementationType) {

        switch (implementationType) {
            case IN_MEMORY: { //yet we have one switch case. We'll get to that!

                // if its null it doesnt exisaat so we create it, but only if its null
                if(moviesDao == null){
                    moviesDao = new InMemoryMoviesDao();
                }
            }
            case MYSQL: {
                // if its null it doesnt exisaat so we create it, but only if its null
                if(moviesDao == null){
                    moviesDao = new MySqlMoviesDao(config);
                }
            }
        }
        return moviesDao;
    }
}