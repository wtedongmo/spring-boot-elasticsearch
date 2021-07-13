package com.twb;

import com.twb.book.model.Author;
import com.twb.book.model.Book;
import com.twb.book.repository.AuthorRepository;
import com.twb.book.service.BookService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Author author = new Author("101", "Rambabu", "Yde");
        authorRepository.save(author);

        bookService.save(new Book("1004", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017", author));
        bookService.save(new Book("1005", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017", author));
        bookService.save(new Book("1006", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017", author));

        //fuzzey search
        Page<Book> books = bookService.findByAuthor("Rambabu", PageRequest.of(0, 10));

        //List<Book> books = bookService.findByTitle("Elasticsearch Basics");

        books.forEach(x -> System.out.println(x));


    }

    @Bean
    public boolean createTestIndex(RestHighLevelClient restHighLevelClient) throws Exception {
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("hello-world");
            restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT); // 1
        } catch (Exception ignored) {
        }

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("hello-world");
        createIndexRequest.settings(
                Settings.builder().put("index.number_of_shards", 1)
                        .put("index.number_of_replicas", 0));
        restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT); // 2

        return true;
    }

}