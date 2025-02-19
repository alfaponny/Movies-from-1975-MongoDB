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
          /* for (Movie movie : movieList) {
                System.out.println(movie);
            } */

            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften
            int numberOfMovies = MovieQuestions.amountOfMovies(movieList);
            System.out.println("Total amount of movies made in 1975: " + numberOfMovies);

            int highestRunTime = MovieQuestions.highestRunTime(movieList);
            System.out.println("Highest runtime in 1975: " + highestRunTime);

            int uniqueGenres = MovieQuestions.uniqueGenres(movieList);
            System.out.println("Unique genres in 1975: " + uniqueGenres);

            int uniqueLanguages = MovieQuestions.uniqueLanguages(movieList);
            System.out.println("Unique languages in 1975: " + uniqueLanguages);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        MongoDBAtlasDownloadExample m = new MongoDBAtlasDownloadExample();
    }
}
