package data;

import exception.PublicationAlreadyExistsException;
import exception.UserAlreadyExistsException;
import utils.LibraryUser;
import utils.LibraryUtils;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    /*
    public static final int MAXPUBLICATIONS = 2000;
    private static final long serialVersionUID = -6455669901981144862L;
    private Publication[]publications = new Publication[INITIAL_CAPACITY];
    private int publicationsNumber;
    private static final int INITIAL_CAPACITY = 1;
    */

    //zmieniony typ
    private Map<String, Publication> publications = new HashMap<>();
    //dodane
    private Map<String, LibraryUser> users = new HashMap<>();

    public Optional<Publication>findPublicationByTitle(String title){
        return Optional.ofNullable(publications.get(title));
    }

    //zmieniony typ zwracany
    public Map<String, Publication> getPublications()
    {
        return publications;
    }

    //dodany getter
    public Map<String, LibraryUser> getUsers(){
        return users;
    }

    //dodana metoda i rzucany nowy typ wyjątku
    public void addUser(LibraryUser user){
        if (users.containsKey(user.getPesel()))
            throw new UserAlreadyExistsException("Użytkownik ze wskazanym pesel już istnieje " + user.getPesel());
            users.put(user.getPesel(),user);
    }

    public Collection<Publication>getSortedPublications(Comparator<Publication> comparator){
        ArrayList<Publication> list = new ArrayList<>(this.publications.values());
        list.sort(comparator);
        return list;
    }

    public Collection<LibraryUser>getSortedUsers(Comparator<LibraryUser> comparator){
        ArrayList<LibraryUser> list = new ArrayList<>(this.users.values());
        list.sort(comparator);
        return list;
    }
    /*
    public void setPublications(Publication[] publications) {
        this.publications = publications;
    }

    public int getPublicationsNumber() {
        return publicationsNumber;
    }

    public void setPublicationsNumber(int publicationsNumber) {
        this.publicationsNumber = publicationsNumber;
    }

    public Library(){
        publications = new Publication[MAXPUBLICATIONS];
    }


    public void addBook(Book book){
        addPublication(book);
    }
    public void addMagazine(Magazine magazine){
        addPublication(magazine);
    }
    */

    public void addPublication(Publication publication){
        if (publications.containsKey(publication.getTitle()))
            throw new PublicationAlreadyExistsException("Publikacja o takim tytule już istnieje "+ publication.getTitle());
        publications.put(publication.getTitle(),publication);
    }

    public boolean removePublication(Publication publication){
        if (publications.containsValue(publication)){
            publications.remove(publication.getTitle());
            return true;
        }
        else {
            return false;
        }
    }


}
