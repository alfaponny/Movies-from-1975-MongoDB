import funcProg1.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.mongodb.assertions.Assertions.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class MovieTest {
	private List<Movie> testMovies;

	@BeforeEach
	void setUp() {

		testMovies = List.of(
				new Movie("tt0111161",
						"The Shawshank Redemption",
						1994,
						List.of("Drama"),
						"Frank Darabont",
						List.of("Tim Robbins", "Morgan Freeman"),
						9.3,
						List.of("English"),
						142
				),

				new Movie("tt0068646",
						"The Godfather",
						1972,
						List.of("Crime", "Drama"),
						"Francis Ford Coppola",
						List.of("Marlon Brando", "Al Pacino", "James Caan"),
						9.2,
						List.of("English", "Italian", "Latin"),
						175
				),

				new Movie("tt1375666",
						"Inception",
						2010,
						List.of("Action", "Sci-Fi", "Thriller"),
						"Christopher Nolan",
						List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page", "Viggo Mortensen"),
						8.8,
						List.of("English", "Japanese", "French"),
						148
				),

				new Movie("tt0120737",
						"The Lord of the Rings",
						2001,
						List.of("Adventure", "Fantasy", "Drama"),
						"Peter Jackson",
						List.of("Elijah Wood", "Ian McKellen", "Viggo Mortensen"),
						8.8,
						List.of("English", "Sindarin"),
						178
				)
		);
	}

		@Test
		void getAmountOfMovies () {

			assertEquals(4, Movie.amountOfMovies(testMovies));
			assertNotEquals(2, Movie.amountOfMovies(testMovies));

		}

		@Test
		void testHighestRunTime () {

			int result = Movie.highestRunTime(testMovies);

			assertEquals(178, result);
			assertNotEquals(148, Movie.highestRunTime(testMovies));
		}

		@Test
		void testUniqueGenres () {
			int result = Movie.uniqueGenres(testMovies);

			assertEquals(7, result);
			assertNotEquals(2, Movie.uniqueGenres(testMovies));
		}

		@Test
		void testUniqueLanguages () {
			int result = Movie.uniqueLanguages(testMovies);

			assertEquals(6, result);
			assertNotEquals(2, Movie.uniqueLanguages(testMovies));
		}

		@Test
		void testLeastAmountOfActors () {
			String result = Movie.leastAmountOfActors(testMovies);

			assertEquals("The Shawshank Redemption", result);
			assertNotEquals("Inception", Movie.leastAmountOfActors(testMovies));

		}

		@Test
		void testHighestIMDBRating () {
			String result = Movie.highestIMDBRatingActors(testMovies);
			assertTrue(result.contains("Tim Robbins") && result.contains("Morgan Freeman"));
			assertFalse(result.contains("Leonardo DiCaprio"));
		}

		@Test
		void testAmountOfActors () {
			int result = Movie.amountOfActorsInMoreThanOneMovie(testMovies);
			assertEquals(1, result);
			assertNotEquals(0, Movie.amountOfActorsInMoreThanOneMovie(testMovies));
		}

		@Test
		void testMostFrequentActor () {
			String result = Movie.mostFrequentActor(testMovies);
			assertEquals("Viggo Mortensen", result);
			assertNotEquals("Ellen Page", Movie.mostFrequentActor(testMovies));
		}

		@Test
		void testDuplicatedTitles () {
			boolean result = Movie.duplicatedTitles(testMovies);
			assertEquals(false, result);
			assertNotEquals(true, Movie.duplicatedTitles(testMovies));
		}





}
