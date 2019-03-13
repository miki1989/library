package utils;

import data.Book;
import data.Library;
import data.Magazine;
import data.Publication;

public class LibraryUtils {

    public static void printMagazines(Library library){
        Publication[]publications = library.getPublications();
        int publicationsNumber = library.getPublicationsNumber();
        int countMagazines = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine){
                System.out.println(publications[i]);
                countMagazines++;
            }
        }

        if (countMagazines == 0){
            System.out.println("Brak magazynów");
        }
    }

    public static void printBooks(Library library){
        Publication[]publications = library.getPublications();
        int publicationsNumber = library.getPublicationsNumber();
        int countBooks = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Book){
                System.out.println(publications[i]);
                countBooks++;
            }
        }

        if (countBooks == 0){
            System.out.println("Brak książek");
        }
    }
}
