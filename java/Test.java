import java.util.List;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.Artist;
import com.echonest.api.v4.EchoNestException;


class Test {
	public static String API_KEY = "K8TNHHSAMCORDFDPX";

	public static void main(String[] args) throws EchoNestException {
		EchoNestAPI echoNest = new EchoNestAPI(API_KEY);
		List<Artist> artists = echoNest.searchArtists("Weezer");

		if (artists.size() > 0) {
			Artist weezer = artists.get(0);
			System.out.println("Similar artists for " + weezer.getName());
			for (Artist simArtist : weezer.getSimilar(10)) {
				System.out.println("   " + simArtist.getName());
			}
		}
	}
}
