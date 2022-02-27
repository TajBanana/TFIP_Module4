package tajbanana.server.repositories;

//class that contains all the SQL commands

public class SQL {
    public static final String SQL_GET_ALL_BOOKS = "SELECT * FROM book2018";
    public static final String SQL_GET_BOOKS_BY_TITLE = "SELECT * FROM book2018 WHERE title LIKE ?";
    public static final String SQL_GET_ALL_BOOKS_BY_LIMIT_OFFSET = "SELECT * FROM book2018 LIMIT ? OFFSET ?";
    public static final String SQL_GET_BOOKS_FORMAT = "SELECT DISTINCT(format) FROM book2018 WHERE format NOT LIKE ''";
}
