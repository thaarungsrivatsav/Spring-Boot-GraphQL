package com.example.GraphQL.service.dataFetcher;

import com.example.GraphQL.modal.Book;
import com.example.GraphQL.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDataFetcher implements DataFetcher<Optional<Book>> {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Optional<Book> get(DataFetchingEnvironment dataFetchingEnvironment)
    {
        String isn = dataFetchingEnvironment.getArgument("id");

        return bookRepository.findById(isn);
    }
}
