/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp.resources;

import java.time.Duration;
import java.util.Calendar;

/**
 *
 * @author Usuario
 */
public class Session {

    private int sessionID;
    private Calendar sessionTime;

    private Duration sessionLength;
    private Duration exerciseLength;

    private double distance;
    private double slopeUp;
    private double slopeDown;
    private double maxSpeed;
    private double averageSpeed;

    private int heartRate;
    private int maxPedalRate;
    private int averagePedalRate;

    public Session(int sessionID, Calendar sessionTime, Duration sessionLength, Duration exerciseLength, double distance, double slopeUp, double slopeDown, double maxSpeed, double averageSpeed, int heartRate, int maxPedalRate, int averagePedalRate) {
        this.sessionID = sessionID;
        this.sessionTime = sessionTime;
        this.sessionLength = sessionLength;
        this.exerciseLength = exerciseLength;
        this.distance = distance;
        this.slopeUp = slopeUp;
        this.slopeDown = slopeDown;
        this.maxSpeed = maxSpeed;
        this.averageSpeed = averageSpeed;
        this.heartRate = heartRate;
        this.maxPedalRate = maxPedalRate;
        this.averagePedalRate = averagePedalRate;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public Calendar getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Calendar sessionTime) {
        this.sessionTime = sessionTime;
    }

    public Duration getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(Duration sessionLength) {
        this.sessionLength = sessionLength;
    }

    public Duration getExerciseLength() {
        return exerciseLength;
    }

    public void setExerciseLength(Duration exerciseLength) {
        this.exerciseLength = exerciseLength;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSlopeUp() {
        return slopeUp;
    }

    public void setSlopeUp(double slopeUp) {
        this.slopeUp = slopeUp;
    }

    public double getSlopeDown() {
        return slopeDown;
    }

    public void setSlopeDown(double slopeDown) {
        this.slopeDown = slopeDown;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getMaxPedalRate() {
        return maxPedalRate;
    }

    public void setMaxPedalRate(int maxPedalRate) {
        this.maxPedalRate = maxPedalRate;
    }

    public int getAveragePedalRate() {
        return averagePedalRate;
    }

    public void setAveragePedalRate(int averagePedalRate) {
        this.averagePedalRate = averagePedalRate;
    }
    
    
    
}

