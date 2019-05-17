package com.lambdaschool.bookstore.service;


import com.lambdaschool.bookstore.model.Author;
import com.lambdaschool.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorrepo;

    @Override
    public ArrayList<Author> findAll()
    {
        ArrayList<Author> list = new ArrayList<>();
        authorrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
