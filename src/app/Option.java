package app;

import exception.NoSuchOptionException;

import java.util.NoSuchElementException;

public enum Option {
    EXIT(0, "Wyjście z programu"),
    ADDBOOK(1, "Dodanie ksiązki"),
    PRINTBOOKS(2, "Wyświetlenie ksiązek"),
    ADDMAGAZINE(3, "Dodanie magazynu"),
    PRINTMAGAZINES(4, "Wyświetlenie magazynów"),
    DELETEBOOK(5, "Usuń książkę"),
    DELETEMAGAZINE(6, "usuń magazyn"),
    ADDUSER(7, "Dodaj czytelnika"),
    PRINTUSERS(8, "Wyświetl czytelników"),
    FINDBOOK(9, "Znajdź książkę");

    private int value;
    private String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }

    public static Option createFromInt(int option)throws NoSuchOptionException {
        Option result = null;
        try{
            result = Option.values()[option];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new NoSuchOptionException("Brak opcji o takim id " + option);
        }

        return result;
    }
}
