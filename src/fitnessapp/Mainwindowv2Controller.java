/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import jgpx.model.analysis.Chunk;
import jgpx.model.analysis.TrackData;
import jgpx.model.gpx.Track;
import jgpx.model.jaxb.GpxType;
import jgpx.model.jaxb.TrackPointExtensionT;
import jgpx.util.DateTimeUtils;

/**
 * FXML Controller class
 *
 * @author ccaballer96
 */
public class Mainwindowv2Controller implements Initializable {

    private ArrayList<String> trackListBckp;
    private ObservableList<String> trackList;
    private ObservableList<Chunk> chunks;
    private Stage stage;
    private Track track;
    private TrackData trackData;
    private HBox leftPan;
    @FXML
    private Label rstart;
    @FXML
    private Label rend;
    @FXML
    private Label dur;
    @FXML
    private Label exc;
    @FXML
    private Label dist;
    @FXML
    private Label amt;
    @FXML
    private Label mspd;
    @FXML
    private Label aspd;
    @FXML
    private Label maxHR;
    @FXML
    private Label minHR;
    @FXML
    private Label aHR;
    @FXML
    private Label maxPR;
    @FXML
    private Label aPR;
    @FXML
    private Button loadBtn;
    @FXML
    private ListView<String> listView;
    @FXML
    private AreaChart<Double, Double> heightArea;
    @FXML
    private LineChart<Double, Double> speedChart;
    @FXML
    private LineChart<?, ?> heartChart;
    @FXML
    private LineChart<?, ?> pedalChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        trackListBckp = new ArrayList<>();
    }

    @FXML
    private void load(ActionEvent event) throws JAXBException {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(loadBtn.getScene().getWindow());
        if (file == null) {
            return;
        }
        //date.setText("Loading " + file.getName());
        JAXBContext jaxbContext = JAXBContext.newInstance(GpxType.class, TrackPointExtensionT.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<Object> root = (JAXBElement<Object>) unmarshaller.unmarshal(file);
        GpxType gpx = (GpxType) root.getValue();

        if (gpx != null) {
            track = new Track(gpx.getTrk().get(0));
            trackData = new TrackData(track);
            stage.setTitle(track.getName());
            addRoute();
            System.out.println("GPX successfully loaded");
        } else {
            System.out.println("Error loading GPX from " + file.getName());
        }
    }

    public void initData(Stage stage) {
        this.stage = stage;
        rstart.setText(" ");
        rend.setText(" ");
        exc.setText(" ");
        dur.setText(" ");
        dist.setText(" ");
        amt.setText(" ");
        mspd.setText(" ");
        aspd.setText(" ");
        maxHR.setText(" ");
        minHR.setText(" ");
        aHR.setText(" ");
        maxPR.setText(" ");
        aPR.setText(" ");
        
        
    }

    private void addRoute() {
        trackListBckp.add(track.getName());
        trackList = FXCollections.observableArrayList(trackListBckp);
        listView.setItems(trackList);
        refreshLabels();
        refreshCharts();
    }

    private void refreshLabels() {
        rstart.setText(DateTimeUtils.format(trackData.getStartTime()));
        rend.setText(DateTimeUtils.format(trackData.getEndTime()));
        dur.setText(DateTimeUtils.format(trackData.getTotalDuration()));
        exc.setText(DateTimeUtils.format(trackData.getMovingTime()) + " (hh:mm:ss)");
        dist.setText(String.format("%.2f", trackData.getTotalDistance() / 1000) + " km");
        amt.setText(String.format("%.2f", trackData.getTotalAscent() + trackData.getTotalDescend()) + " m");
        mspd.setText(String.format("%.2f", trackData.getMaxSpeed()) + " km/h");
        aspd.setText(String.format("%.2f", trackData.getAverageSpeed()) + " km/h");
        maxHR.setText(Integer.toString(trackData.getMaxHeartrate()) + " bpm");
        minHR.setText(Integer.toString(trackData.getMinHeartRate()) + " bpm");
        aHR.setText(Integer.toString(trackData.getAverageHeartrate()) + " bpm");
        maxPR.setText(Double.toString(trackData.getMaxCadence()) + " ppm");
        aPR.setText(Double.toString(trackData.getAverageCadence()) + " ppm");
    }

    private void refreshCharts() {
        chunks = trackData.getChunks();
        XYChart.Series heightDist= new XYChart.Series();
        XYChart.Series speedDist= new XYChart.Series();
        XYChart.Series heartDist= new XYChart.Series();
        XYChart.Series pedalDist= new XYChart.Series();
        heightDist.setName("Height x Distance");
        speedDist.setName("Speed x Distance");  
        heartDist.setName("Heart Rate x Distance");
        pedalDist.setName("Pedal Rate x Distance");
        double dist = 0.0;
        for (int i = 0; i < chunks.size(); i++) {
            dist += chunks.get(i).getDistance();
            heightDist.getData().add(new XYChart.Data(dist, chunks.get(i).getLastPoint().getElevation()));
            speedDist.getData().add(new XYChart.Data(dist, chunks.get(i).getSpeed()));
            heartDist.getData().add(new XYChart.Data(dist, chunks.get(i).getAvgHeartRate()));
            pedalDist.getData().add(new XYChart.Data(dist, chunks.get(i).getAvgCadence()));            
        }
        heightArea.getData().add(heightDist);
        heightArea.setCreateSymbols(false);
        speedChart.getData().add(speedDist);
        speedChart.setCreateSymbols(false);
        heartChart.getData().add(heartDist);
        heartChart.setCreateSymbols(false);
        pedalChart.getData().add(pedalDist);
        pedalChart.setCreateSymbols(false);
    }
    
    public void clear(ActionEvent event) throws IOException{
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("mainwindowv2.fxml"));
        Parent root = myLoader.load();
        Mainwindowv2Controller window1 = myLoader.<Mainwindowv2Controller>getController();        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("Empty Route");
        window1.initData(stage);
        stage.show();
    }

}
