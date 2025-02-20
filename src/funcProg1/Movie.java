package funcProg1;

import org.bson.Document;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.List;
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
		);}


		//antal filmer 1975
		public static int amountOfMovies (List < Movie > movieList) {

			return (int) movieList.stream().count(); //gör en stream av movieList. Räknar antal element i strömmen.
		}

		//Film med högst runtime
		public static int highestRunTime (List < Movie > movieList) {

			return movieList.stream()
					.max(Comparator.comparingInt(Movie::getRuntime))//hittar filmen med högsta speltiden
					.map(Movie::getRuntime).orElse(0); //tar ut en int av speltiden. 0 om ingen film hittas

		}

		//Antal unika genrer
		public static int uniqueGenres (List < Movie > movieList) {

			return (int) movieList.stream()
					.map(movie -> movie.getGenres())//en lista för varje genre för varje film
					.flatMap(genres -> genres.stream())//plattar listan till en enda ström (genrer är flera)
					.distinct() //tar bort dubletter
					.count();//räknar unika genrer
		}




		public static int uniqueSearch (List < Movie > movieList, UniqueSearchInterface extractor){
			return (int) movieList.stream()
					.map(movie -> extractor.extract(movie))//samma logik som övre
					.flatMap(list -> list.stream())
					.distinct()
					.count();
		}
	//då de senaste två metoderna är väldigt lika skapade jag en högre ordningens funktion för dessa två.

	public static UniqueSearchInterface languageSearch = (Movie movie) -> movie.getLanguages();
	public static UniqueSearchInterface genreSearch = (Movie movie) -> movie.getGenres();

		//Antal unika språk
		public static int uniqueLanguages (List < Movie > movieList) {
			return (int) movieList.stream()
					.map(movie -> movie.getLanguages())
					.flatMap(languages -> languages.stream())
					.distinct()
					.count();
		}


		//Minst antal skådisar listade
		public static String leastAmountOfActors (List < Movie > movieList) {

			return movieList.stream()
					.min(Comparator.comparingInt(movie -> movie.getCast().size()))//hittar film med minst antal skådisar
					.map(movie -> movie.getTitle())//tar ut titeln
					.orElse(""); //om ingen film existerar kommer det tillbaka en tom sträng
		}

		//skådisar i filmen med högst IMDB-rating
		public static String highestIMDBRatingActors (List < Movie > movieList) {

			return movieList.stream()
					.max(Comparator.comparingDouble(movie -> movie.getImdbRating()))//hittar filmen med högst IMDB
					.map(movie -> String.join(", ", movie.getCast()))//joinar skådislistan till en sträng
					.orElse("");
		}

		//antal skådisar som är med i mer än en film
		public static int amountOfActorsInMoreThanOneMovie (List < Movie > movieList) {

			return (int) movieList.stream()
					.flatMap(movie -> movie.getCast().stream()) //plattar ut alla skådespelare
					.collect(Collectors.groupingBy(actor -> actor, Collectors.counting()))// samlar ihop skådisarna i en grupp, räknar dem, gör en lista
					.values().stream().filter(count -> count > 1).count(); //values räknar antal och sen filtrear antal som är med i mer än en film
		}

		//skådespelaren som medverkat i flest filmer
		public static String mostFrequentActor (List < Movie > movieList) {
			//omvandlar movieList.stream() till en Map
			Map<String, Long> countActors = movieList.stream()    //String som primärnyckelvärdet. Objektet är en long
					.flatMap(movie -> movie.getCast().stream()) //plattar ut alla skådespelare
					.collect(Collectors.groupingBy(actor -> actor, Collectors.counting())); //samlar ihop skådisar, räknar dem, gör dem till nyckel

			return countActors.entrySet().stream()       //entrySet gör att vi kan fortsätta med strömmen
					.max(Comparator.comparingLong(Map.Entry::getValue)) //räknar ut den som förekommer mest
					.map(Map.Entry::getKey).orElse(""); //tar fram den skådisen som förekommer mest
		}

		//duplicerade titlar
		public static boolean duplicatedTitles (List < Movie > movieList) {

			return movieList.stream()
					.map(Movie::getTitle) //tar fram alla filmtitlar
					.collect(Collectors.groupingBy(title -> title, Collectors.counting()))//räknar hur många ggr varje titel återkommer
					.values().stream().anyMatch(count -> count > 1); //ommer tillbaka true om någon titel förekommer två gånger

		}


		// Getters and Setters
		public String getId () {
			return id;
		}

		public String getTitle () {
			return title;
		}

		public int getYear () {
			return year;
		}

		public int getRuntime () {
			return runtime;
		}

		public List<String> getGenres () {
			return genres;
		}

		public String getDirector () {
			return director;
		}

		public List<String> getCast () {
			return cast;
		}

		public double getImdbRating () {
			return imdbRating;
		}

		public List<String> getLanguages () {
			return languages;
		}

		@Override
		public String toString () {
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



