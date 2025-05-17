package com.example.GraphQL.service;

import com.example.GraphQL.modal.Book;
import com.example.GraphQL.repository.BookRepository;
import com.example.GraphQL.service.dataFetcher.AllBooksDataFetcher;
import com.example.GraphQL.service.dataFetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphQLService {

    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;

    @Autowired
    private BookDataFetcher bookDatafetcher;

    @Autowired
    BookRepository bookRepository;


    @PostConstruct
    public void loadSchema(){
        try {
            loadDataIntoHSQL();
            File schemaFile = resource.getFile();
            TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
            RuntimeWiring runtimeWiring = buildRunTimeWiring();
            GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
            graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDataIntoHSQL(){
        Stream.of(
            new Book("123","book 1","Kindle Edition",new String[]{"Mr.A","Mr.B"},"23 Nov 2003"),
                new Book("1432","book 2","Base Edition",new String[]{"Mr.C","Mr.B"},"28 Dec 2004"),
                new Book("12123","book 3","Kindle Edition",new String[]{"Mr.A","Mr.D"},"12 Oct 2012"),
                new Book("123123","book 4","Kindle Edition",new String[]{"Mr.E","Mr.D"},"13 Jan 2012"),
                new Book("34354","book 5","Kindle Edition",new String[]{"Mr.A","Mr.D"},"25 Nov 2012")
        ).forEach(book->{
            bookRepository.save(book);
        });
    }
    private RuntimeWiring buildRunTimeWiring()
    {
        return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring->typeWiring
                    .dataFetcher("allBooks",allBooksDataFetcher)
                    .dataFetcher("book",bookDatafetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
