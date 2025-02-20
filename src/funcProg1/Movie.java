package funcProg1;

import org.bson.Document;

import java.util.*;
import java.util.stream.Collectors;

public final class Movie { //final klass, kan inte ärvas
	private final String id;
	private final String title;
	private final int year;
	private final int runtime;
	private final List<String> genres;
	private final String director;
	private final List<String> cast;
	private final double imdbRating;
	private final List<String> languages;

	// Constructor
	public Movie(String id, String title, int year, List<String> genres, String director, List<String> cast, double imdbRating, List<String> languages, int runtime) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.runtime = runtime;
		this.genres = genres != null ? List.copyOf(genres) : List.of(); //kopierar listorna för att förhindra ändringar
		this.director = director;
		this.cast = cast != null ? List.copyOf(cast) : List.of();
		this.imdbRating = imdbRating;
		this.languages = languages != null ? List.copyOf(languages) : List.of();
	}

	// Method to convert MongoDB document to FuncProg1.Movie object
	public static Movie fromDocument(Document doc) {
		return new Movie(
				doc.getObjectId("_id").toString(),
				doc.getString("title"),
				doc.getInteger("year", 0),
				doc.getList("genres", String.class),
				doc.getString("director"),
				doc.getList("cast", String.class),
				doc.get("imdb", Document.class) != null ? doc.get("imdb", Document.class).getDouble("rating") : 0.0,
				doc.getList("languages", String.class),
				doc.getInteger("runtime", 0)
		);
	}


	//antal filmer 1975
	public static int amountOfMovies(List<Movie> movieList) {

		return (int) movieList.stream().count();
	}

	//Film med högst runtime
	public static int highestRunTime(List<Movie> movieList) {

		return movieList.stream()
				.max(Comparator.comparingInt(Movie::getRuntime))
				.map(Movie::getRuntime).orElse(0);

	}

	//Antal unika genrer
	public static int uniqueGenres(List<Movie> movieList) {

		return (int) movieList.stream()
				.map(Movie::getGenres).flatMap(Collection::stream)
				.distinct().count();

	}

	//Antal unika språk
	public static int uniqueLanguages(List<Movie> movieList) {

		return (int) movieList.stream()
				.map(Movie::getLanguages).flatMap(Collection::stream)
				.distinct().count();
	}

	//Minst antal skådisar listade
	public static String leastAmountOfActors(List<Movie> movieList) {

		return movieList.stream()
				.min(Comparator.comparingInt(movie -> movie.getCast().size()))
				.map(Movie::getTitle)
				.orElse("");
	}

	//skådisar i filmen med högst IMDB-rating
	public static String highestIMDBRatingActors(List<Movie> movieList) {

		return movieList.stream()
				.max(Comparator.comparingDouble(Movie::getImdbRating))
				.map(movie -> String.join(", ", movie.getCast()))
				.orElse("");
	}

	//antal skådisar som är med i mer än en film
	public static int amountOfActorsInMoreThanOneMovie(List<Movie> movieList) {

		return (int) movieList.stream()
				.flatMap(movie -> movie.getCast().stream())
				.collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))
				.values().stream().filter(count -> count > 1).count();
	}

	//skådespelaren som medverkat i flest filmer
	public static String mostFrequentActor(List<Movie> movieList) {

				Map<String, Long> countActors = movieList.stream()
						.flatMap(movie -> movie.getCast().stream())
						.collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

		return countActors.entrySet().stream()
				.max(Comparator.comparingLong(Map.Entry::getValue))
				.map(Map.Entry::getKey).orElse("");
	}

	//duplicerade titlar
	public static boolean duplicatedTitles(List<Movie> movieList) {

		return movieList.stream()
				.map(Movie::getTitle)
				.collect(Collectors.groupingBy(title -> title, Collectors.counting()))
				.values().stream().anyMatch(count -> count > 1);

	}


	// Getters and Setters
	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public int getRuntime() {
		return runtime;
	}

	public List<String> getGenres() {
		return genres;
	}

	public String getDirector() {
		return director;
	}

	public List<String> getCast() {
		return cast;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public List<String> getLanguages() {
		return languages;
	}

	@Override
	public String toString() {
		return "FuncProg1.Movie{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", year=" + year +
				", runtime=" + runtime +
				", genres=" + genres +
				", director='" + director + '\'' +
				", cast=" + cast +
				", imdbRating=" + imdbRating +
				", languages=" + languages +
				'}';
	}
}
