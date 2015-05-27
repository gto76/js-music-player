/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.TimedEvent;
import com.echonest.api.v4.Track;
import com.echonest.api.v4.TrackAnalysis;
import java.io.File;
import java.io.IOException;

/**
 * This example will demonstrate uploading an MP3, analyzing it and getting the
 * audio summary and the timing info for all of the beats
 *
 * @author plamere
 */
public class TrackAnalysisExample {

    public static void main(String[] args) throws EchoNestException {
        EchoNestAPI en = new EchoNestAPI("K8TNHHSAMCORDFDPX");

        String path = "../JamendoDataset/"+args[0]+".mp3";
		System.out.println("path = "+path);

        if (args.length > 2) {
            path = args[1];
        }

        File file = new File(path);

        if (!file.exists()) {
            System.err.println("Can't find " + path);
        } else {
            try {
				getData(file, en);
            } catch (IOException e) {
                System.err.println("Trouble uploading file");
			}
        }
    }

	private static void getData(File file, EchoNestAPI en) throws IOException {
		try {
			Track track = en.uploadTrack(file);
			track.waitForAnalysis(30000);
			if (track.getStatus() == Track.AnalysisStatus.COMPLETE) {
				track.showAll();
				System.out.println("Tempo: " + track.getTempo());
				System.out.println("Danceability: " + track.getDanceability());
				System.out.println("Speechiness: " + track.getSpeechiness());
				System.out.println("Liveness: " + track.getLiveness());
				System.out.println("Energy: " + track.getEnergy());
				System.out.println("Loudness: " + track.getLoudness());
				System.out.println("=====");
			} else {
				System.err.println("Trouble analysing track " + track.getStatus());
			}
	
		} catch (EchoNestException f) {
			getData(file, en);
		}

	}
}
