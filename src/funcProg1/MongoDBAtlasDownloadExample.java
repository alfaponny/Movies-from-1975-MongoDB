package funcProg1;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


public class MongoDBAtlasDownloadExample {
    public MongoDBAtlasDownloadExample() {

        //Skriv in rätt url!
        String uri = "mongodb+srv://tempuser:SecretPassword@cluster0.xjd3w.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            // Skriver ut alla filmer
           /*for (Movie movie : movieList) {
                System.out.println(movie);
            } */

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften
           // int numberOfMovies = MovieQuestions.amountOfMovies(movieList);
            System.out.println("Total amount of movies made in 1975: " + Movie.amountOfMovies(movieList));

            System.out.println("Highest runtime in 1975: " + Movie.highestRunTime(movieList));

            System.out.println("Unique genres in 1975: " + Movie.uniqueGenres(movieList));

            System.out.println("Unique languages in 1975: " + Movie.uniqueLanguages(movieList));

            System.out.println("Least amount of actors in films of 1975: " + Movie.leastAmountOfActors(movieList));

            System.out.println("The actors in the film with the highest ImDB-rating: " + Movie.highestIMDBRatingActors(movieList));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}
