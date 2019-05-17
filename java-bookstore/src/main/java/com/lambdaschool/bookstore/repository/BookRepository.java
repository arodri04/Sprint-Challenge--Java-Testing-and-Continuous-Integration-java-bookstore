package com.lambdaschool.bookstore.repository;

import com.lambdaschool.bookstore.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>
{
    @Modifying
    @Query(value = "DELETE FROM bookauthors WHERE bookid = :bookid", nativeQuery = true)
    void deleteBookFromBookAuthors(long bookid);

}
