package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.models.Author;
import guru.springframework.spring5webapp.models.Book;
import guru.springframework.spring5webapp.models.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Spring is going to look for instances of this type, when he fineds them he will run them
@Component // Spring managed component
public class BootStrapData implements CommandLineRunner {

    // this is dependency injection
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

        // setup default data

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher("Book publisher", "Poortwachter 11", "Amsterdam", "North Holand", "1186MB");
        publisherRepository.save(publisher);


        Author eric = new Author("Eric", "Evens");
        Book ddd = new Book("Domain driven design", "12345");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // ---------

        Author rad = new Author("Rad", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "6576576987");
        rad.getBooks().add(noEJB);
        noEJB.getAuthors().add(rad);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rad);
        bookRepository.save(noEJB);

        publisherRepository.save(publisher);


        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher count:" + publisherRepository.count());
        System.out.println("Publisher number of books: " +publisher.getBooks().size());
    }
}
