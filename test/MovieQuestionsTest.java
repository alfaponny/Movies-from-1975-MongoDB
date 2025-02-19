import funcProg1.Movie;
import funcProg1.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieQuestionsTest {
	@Test
	void getAmountOfMovies() {
		List<Movie> testMovies = List.of(

		);
		assertEquals(3, Movie.amountOfMovies(testMovies));

	}

	@Test
	void getHighestRuntime(){
		List<Movie> testMovies = List.of(

		);
		assertEquals(testMovies.getRuntime(), 42);
		assertEquals(testMovies.getFirst(), "Eva");
	}
}
