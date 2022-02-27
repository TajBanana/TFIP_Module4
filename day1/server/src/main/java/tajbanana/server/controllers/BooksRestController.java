package tajbanana.server.controllers;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tajbanana.server.models.Book;
import tajbanana.server.repositories.GoodReadRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksRestController {

    @Autowired
    private GoodReadRepository goodReadRepository;

    @GetMapping
//  TODO COMPLETE THE API CALL AND RETURN BOOKS
    public ResponseEntity<String> getAllBooks(
            @RequestParam(defaultValue = "10") Integer limit ,
            @RequestParam(defaultValue = "0") Integer offset) {

        List<Book> result = goodReadRepository.getAllBooks(offset,limit);
        System.out.println(result.toString());

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Book book : result) {
            arrayBuilder.add(book.bookToJson());
        }
        return ResponseEntity.ok(arrayBuilder.build().toString());
    }
}
