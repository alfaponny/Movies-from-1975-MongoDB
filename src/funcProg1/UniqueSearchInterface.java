package funcProg1;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@FunctionalInterface
public interface UniqueSearchInterface {
	List<String> extract(Movie movie);
}
