package funcProg1;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;



public class MovieQuestions {

	//antal filmer 1975
	public static int amountOfMovies(List<Movie> movieList){
		return (int) movieList.stream().count();
	}
	//Film med högst runtime
	public static int highestRunTime(List<Movie> movieList){
		return movieList.stream().max(Comparator.comparingInt(Movie :: getRuntime)).map(Movie :: getRuntime).orElse(0);

	}
	//Antal unika genrer
	public static int uniqueGenres(List<Movie> movieList){

		return (int) movieList.stream().map(Movie :: getGenres).flatMap(Collection::stream).distinct().count();

	}
	//Antal unika språk
	public static int uniqueLanguages(List<Movie> movieList){
		return (int) movieList.stream().map(Movie :: getLanguages).flatMap(Collection::stream).distinct().count();
	}




}
