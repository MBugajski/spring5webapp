package com.mbugajski.spring5webapp.bootstrap;

import com.mbugajski.spring5webapp.domain.Author;
import com.mbugajski.spring5webapp.domain.Book;
import com.mbugajski.spring5webapp.domain.Publisher;
import com.mbugajski.spring5webapp.repositories.AuthorRepository;
import com.mbugajski.spring5webapp.repositories.BookRepository;
import com.mbugajski.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123155");
        Publisher aw = new Publisher("Addison-Wesley", "Boston, Massachusetts, USA");

        publisherRepository.save(aw);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(aw);
        aw.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(aw);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "1231512");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(aw);
        aw.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(aw);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println(aw.getName() + " number of books: " + aw.getBooks().size());
    }
}
