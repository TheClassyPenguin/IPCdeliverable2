/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Track;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import jgpx.model.analysis.Chunk;
import jgpx.model.analysis.TrackData;
import jgpx.model.jaxb.GpxType;
import jgpx.model.jaxb.TrackPointExtensionT;
import jgpx.util.DateTimeUtils;

/**
 *
 * @author Usuario
 */
public class mainwindowController implements Initializable {
    
    @FXML
    private Label date;
    @FXML
    private Button Load;
    @FXML
    private JFXTextArea title;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        date.setText("Hello World!");
    }
    
        private void load(ActionEvent event) throws JAXBException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Load.getScene().getWindow());
        if (file == null) {
            return;
        }
        date.setText("Loading " + file.getName());
        JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class, TrackPointExtensionT.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<Object> root = (JAXBElement<Object>) unmarshaller.unmarshal(file);
        GpxType gpx = (GpxType) root.getValue();
/*
        if (gpx != null) {
            trackData = new TrackData(new Track(gpx.getTrk().get(0)));
            showTrackInfo(trackData);
            date.setText("GPX successfully loaded");
        } else {
            date.setText("Error loading GPX from " + file.getName());
        }
        
        */
    }
    
      // Testing to see if the import works //
        private void showTrackInfo(TrackData trackData) {
        title.setText("Start time: " + DateTimeUtils.format(trackData.getStartTime()));
        title.appendText("\nEnd time: " + DateTimeUtils.format(trackData.getEndTime()));
        title.appendText(String.format("\nTotal Distance: %.0f m",trackData.getTotalDistance()));
        title.appendText("\nDuration: " + DateTimeUtils.format(trackData.getTotalDuration()));
        title.appendText("\nMoving time: " + DateTimeUtils.format(trackData.getMovingTime()));
        title.appendText(String.format("\nAverage Speed: %.2f m/s", trackData.getAverageSpeed()));
        title.appendText(String.format("\nAverage Cadence: %d", trackData.getAverageCadence()));
        title.appendText(String.format("\nAverage Heartrate: %d bpm", trackData.getAverageHeartrate()));
        title.appendText(String.format("\nTotal ascent: %.2f m", trackData.getTotalAscent()));
        title.appendText(String.format("\nTotal descend: %.2f m", trackData.getTotalDescend()));
        ObservableList<Chunk> chunks = trackData.getChunks();
        title.appendText("\nTrack containing " + chunks.size() + " points");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
