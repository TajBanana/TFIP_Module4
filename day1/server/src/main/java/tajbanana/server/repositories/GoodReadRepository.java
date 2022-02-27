package tajbanana.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import tajbanana.server.models.Book;

import java.util.ArrayList;
import java.util.List;

import static tajbanana.server.repositories.SQL.*;

@Repository
public class GoodReadRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public GoodReadRepository (JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public List<Book> getAllBooks() {
        final List<Book> books = new ArrayList<>();
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ALL_BOOKS);

        while (rs.next()) {
            Book book = Book.populate(rs);
            books.add(book);
        }
        return books;
    }

    public List<Book> getAllBooks(int limit) {
        return  getAllBooks(0, limit);
    }

    public List<Book> getAllBooks(int offset, int limit) {
        final List<Book> books = new ArrayList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ALL_BOOKS_BY_LIMIT_OFFSET, limit, offset);

        while (rs.next()) {
            final Book book = Book.populate(rs);
            books.add(book);
        }
        return books;
    }

    public List<Book> getBooksByTitle(String phrase) {
        final List<Book> books = new ArrayList<>();
        final String search = "%" + phrase + "%";
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BOOKS_BY_TITLE, search);

        while (rs.next()) {
            final Book book = Book.populate(rs);
            books.add(book);
        }
        return books;
    }

    public List<String> getBooksFormat() {
        final List<String> result = new ArrayList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BOOKS_FORMAT);

        while (rs.next()) {
            result.add(rs.getString("format").toLowerCase());
        }

        return result;
    }

}
