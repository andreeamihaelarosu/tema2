package ro.andreearosu.Assignment2;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    public List<String> getAvailableGenres(BookGenre type){

        List<String> genres = new ArrayList();

        if(type.equals(BookGenre.FANTASY)){
        	genres.add("The Chronicles of Narnia");
        	genres.add(("Assassin's Apprentice"));
        	genres.add(("His Dark Materials"));
        	genres.add(("The Neverending Story"));
        	genres.add(("Alanna: The First Adventur"));
        	genres.add(("Tigana"));
        	genres.add(("A Game of Thrones (A Song of Ice and Fire, #1)"));

        } else if(type.equals(BookGenre.MYSTERY)){
        	genres.add("The Girl with the Dragon Tattoo (Millennium, #1)");
        	genres.add("And Then There Were None");
        	genres.add("Angels & Demons (Robert Langdon, #1)");
        	genres.add("Rebecca");
        	genres.add("The Godfather");
        	genres.add("In Cold Blood");

        }else if(type.equals(BookGenre.FICTION)){
        	genres.add("The Hunger Games");
        	genres.add("Pride and Prejudice");
        	genres.add("The Book Thief");
        	genres.add("Gone with the Wind");
        	genres.add("The Giving Tree");
        	genres.add("Wuthering Heights");
        }else {
        	genres.add("No Genres Available");
        }
    return genres;
    }
}