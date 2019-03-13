package app;

import data.Book;
import data.Library;
import data.Magazine;
import data.Publication;
import data.comparator.AlphabeticalTitleComparator;
import exception.*;
import utils.*;

import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;

public class LibraryControl {


    //zmianna do komunikacji z uzytkownikiem
    private FileManager fileManager;
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);

    //biblioteka przechowujaca dane
    private Library library = new Library();
    private LibraryUtils libraryUtils;

    LibraryControl(){
        fileManager = new FileManagerBuilder(printer, dataReader).build();

        try{
            library = fileManager.importData();
            printer.printLine("Zaimportowane dane z pliku");
        } catch (DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę");
            library = new Library();
        }
    }

    private void exit(){
        try{
            fileManager.exportData(library);
            printer.printLine("Export danych do pliku zakończony powodzeniem");
        } catch (DataExportException e){
            printer.printLine(e.getMessage());
        }
        dataReader.close();
        printer.printLine("Koniec programu");
    }

    //glowna petla programu z opcjami i interakcja
    void controlLoop() {
        Option option;

        do {
            printOptions();
            option = getOption();
            switch (option) {
                case ADDBOOK:
                    addBook();
                    break;
                case ADDMAGAZINE:
                    addMagazine();
                    break;
                case PRINTBOOKS:
                    printBooks();
                    break;
                case PRINTMAGAZINES:
                    printMagazines();
                    break;
                case DELETEBOOK:
                    deleteBook();
                    break;
                case DELETEMAGAZINE:
                    deleteMagazine();
                    break;
                case ADDUSER:
                    addUser();
                    break;
                case PRINTUSERS:
                    printUsers();
                    break;
                case FINDBOOK:
                    findBook();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji, wprowadź ponownie: ");
            }
        } while (option != Option.EXIT);
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage() + ", podaj ponownie:");
            } catch (InputMismatchException ignored) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:");
            }
        }

        return option;
    }

    private void printOptions(){
        System.out.println("Wybierz opcję");
        for (Option o:Option.values()) {
            System.out.println(o);
        }
    }
    private void addBook(){
        try{
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książki ");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności");
        }

    }

    private void addUser(){
        LibraryUser libraryUser = dataReader.createLibraryUser();
        try {
            library.addUser(libraryUser);
        } catch (UserAlreadyExistsException e){
            printer.printLine(e.getMessage());
        }
    }
    private void printBooks(){

        printer.printBooks(library.getSortedPublications(Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)));
    }

    private void addMagazine(){
        try{
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć magazynu");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągniętu limi pojemności");
        }
    }
    private void printMagazines(){

        printer.printMagazines(library.getSortedPublications(Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER)));
    }

    private void  printUsers(){
        printer.printUsers(library.getSortedUsers(Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER)));
    }

    private void deleteMagazine(){
        try{
            Magazine magazine = dataReader.readAndCreateMagazine();
            if (library.removePublication(magazine)){
                printer.printLine("Usunięto magazyn");
            }
            else {
                printer.printLine("Brak wskazanego magazynu");
            }
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć magazynu");
        }
    }

    private void deleteBook(){
        try{
            Book book = dataReader.readAndCreateBook();
            if (library.removePublication(book)){
                printer.printLine("Usunięto książkę");
            }
            else {
                printer.printLine("Brak wskazanej książki");
            }
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książki");
        }
    }

    private void findBook(){
        printer.printLine("Podaj tytuł publikacji");
        String title = dataReader.getString();
        String notFoundMessage = "Brak publikacji o tym tytule";
        library.findPublicationByTitle(title)
                .map(Publication::toString)
                .ifPresentOrElse(System.out::println, () -> System.out.println(notFoundMessage));
    }
}
