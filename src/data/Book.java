package data;

import utils.DataReader;

import java.util.Objects;

public class Book extends Publication{

    public static final String TYPE = "Książka";

    @Override
    public String toCsv() {
        return (TYPE + ";") +
                getTitle() +";" + getPublisher() + ";"+ getYear() + ";" + author + ";" + pages + ";" + isbn;
    }

    private static final long serialVersionUID = -1324076107873350933L;
    private String author;
    private int pages;
    private String isbn;

    public Book(int year, String title, String publisher, String author, int pages, String isbn) {
        super(year, title, publisher);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, pages, isbn);
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder(32);
        print.append(getTitle());
        print.append(",");
        print.append(getAuthor());
        print.append(",");
        print.append(getYear());
        print.append(",");
        print.append(getPages());
        print.append(",");
        print.append(getPublisher());
        print.append(",");
        print.append(getIsbn());
        return print.toString();
    }
}
