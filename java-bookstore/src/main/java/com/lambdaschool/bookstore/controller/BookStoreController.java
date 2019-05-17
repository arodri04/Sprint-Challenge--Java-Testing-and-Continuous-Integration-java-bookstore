package com.lambdaschool.bookstore.controller;


import com.lambdaschool.bookstore.exception.ResourceNotFoundException;
import com.lambdaschool.bookstore.model.Author;
import com.lambdaschool.bookstore.model.Book;
import com.lambdaschool.bookstore.model.ErrorDetail;
import com.lambdaschool.bookstore.model.User;
import com.lambdaschool.bookstore.service.AuthorService;
import com.lambdaschool.bookstore.service.BookService;
import com.lambdaschool.bookstore.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/bookstore")
public class BookStoreController
{
    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;
    @Autowired
    private UserService userService;





    @GetMapping(value = "/books", produces = {"application/json"})
    @ApiOperation(value = "returns All books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    public ResponseEntity<?> listAllBooks(@PageableDefault(page = 0, size = 5) Pageable pageable)
    {
        ArrayList<Book> mybooks = bookService.findAll(pageable);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findUserName(userName);
        if (authentication == null)
        {
            return new ResponseEntity<>("Couldnt Authenticate username", HttpStatus.FORBIDDEN);
        }
        if (user == null)
        {
            throw new ResourceNotFoundException("Could not find user");
        }
        return new ResponseEntity<>(mybooks, HttpStatus.OK);
    }

    @GetMapping(value = "/authors", produces = {"application/json"})
    @ApiOperation(value = "returns All Authors", response = Author.class, responseContainer = "List")
    public ResponseEntity<?> listAllAuthors()
    {
        ArrayList<Author> myauthors = authorService.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findUserName(userName);

        return new ResponseEntity<>(myauthors, HttpStatus.OK);
    }

    @DeleteMapping("/data/books/{id}")
    @ApiOperation(value = "Deletes Book By id", response = Book.class, responseContainer = "List")
    public ResponseEntity<?> deleteBookById(
            @PathVariable
                    long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(
            @RequestBody
                    Book updateBook,
            @PathVariable
                    long id)
    {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
