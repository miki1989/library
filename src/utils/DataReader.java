package utils;

import data.Book;
import data.Magazine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {
    private Scanner scanner = new Scanner(System.in);
    private ConsolePrinter printer;

    public DataReader(ConsolePrinter printer){
        this.printer = printer;
    }

    public void close(){
        scanner.close();
    }

    public int getInt() throws NumberFormatException {
        int number = 0;
        try{
            number = scanner.nextInt();
        }catch (InputMismatchException e){
            throw new NumberFormatException("Liczba wprowadzona nie jest poprawna");
        } finally {
            scanner.nextLine();
        }

        return number;
    }

    public LibraryUser createLibraryUser(){
        printer.printLine("Imie");
        String firstName = scanner.nextLine();
        printer.printLine("Nazwisko");
        String lastName = scanner.nextLine();
        printer.printLine("Pesel");
        String pesel = scanner.nextLine();
        return new LibraryUser(firstName,lastName,pesel);
    }

    public Book readAndCreateBook() throws InputMismatchException{
        printer.printLine("Tytuł");
        String title = scanner.nextLine();
        printer.printLine("Autor");
        String author = scanner.nextLine();
        printer.printLine("Wydawnictwo");
        String publisher = scanner.nextLine();
        printer.printLine("ISBN");
        String isbn = scanner.nextLine();
        printer.printLine("Rok wydania");

        int releaseDate = 0;
        int pages = 0;
        try {
            releaseDate = scanner.nextInt();
            scanner.nextLine();
            printer.printLine("Ilość stron");
            pages = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            scanner.nextLine();
            throw e;
        }


        return new Book(releaseDate, title,publisher,author,pages,isbn);
    }

    public Magazine readAndCreateMagazine() throws  InputMismatchException{
        printer.printLine("Tytuł");
        String title = scanner.nextLine();
        printer.printLine("Wydawnictwo");
        String publisher = scanner.nextLine();
        printer.printLine("Język");
        String language = scanner.nextLine();
        printer.printLine("Rok wydania");
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            year = scanner.nextInt();
            scanner.nextLine();
            printer.printLine("Miesiąc");
            month = scanner.nextInt();
            scanner.nextLine();
            printer.printLine("Dzien");
            day = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            scanner.nextLine();
            throw e;
        }

        return new Magazine(year,title,publisher,month,day,language);
    }

    public String getString() {
        return scanner.nextLine();
    }
}
