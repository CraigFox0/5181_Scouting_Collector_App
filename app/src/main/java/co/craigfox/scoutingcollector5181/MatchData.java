package co.craigfox.scoutingcollector5181;

import android.content.Intent;

import androidx.annotation.NonNull;

import java.util.UUID;

public class MatchData {
    private UUID uid;
    private int teamNumber;
    private int matchNumber;
    private boolean isRed;
    private boolean positionControl;
    private boolean rotationControl;
    private int climbed;
    private boolean dead;
    //temp method for shots
    private int missedShots;
    private int lowerShots;
    private int upperShots;
    private int innerShots;

    public MatchData(int teamNumber, int matchNumber, boolean isRed, boolean positionControl, boolean rotationControl, int climbed, boolean dead, int missedShots, int lowerShots, int upperShots, int innerShots) {
        this.teamNumber = teamNumber;
        this.matchNumber = matchNumber;
        this.isRed = isRed;
        this.positionControl = positionControl;
        this.rotationControl = rotationControl;
        this.climbed = climbed;
        this.dead = dead;
        //temp method for shots
        this.missedShots = missedShots;
        this.lowerShots = lowerShots;
        this.upperShots = upperShots;
        this.innerShots = innerShots;
    }

    public MatchData(Intent intentData) {
        this(intentData.getIntExtra("teamNumber", 0),
                intentData.getIntExtra("matchNumber", 0),
                intentData.getBooleanExtra("isRed", true),
                intentData.getBooleanExtra("positionControl", false),
                intentData.getBooleanExtra("rotationControl", false),
                intentData.getIntExtra("climbed", 0),
                intentData.getBooleanExtra("dead", false),
                intentData.getIntExtra("missedShots", 0),
                intentData.getIntExtra("lowerShots", 0),
                intentData.getIntExtra("upperShots", 0),
                intentData.getIntExtra("innerShots", 0)
        );
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public int getClimbed() {
        return climbed;
    }

    public boolean isPositionControl() {
        return positionControl;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isRed() {
        return isRed;
    }

    public boolean isRotationControl() {
        return rotationControl;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int getInnerShots() {
        return innerShots;
    }

    public int getLowerShots() {
        return lowerShots;
    }

    public int getMissedShots() {
        return missedShots;
    }

    public int getUpperShots() {
        return upperShots;
    }

    public Intent toIntent() {
        Intent data = new Intent();
        data.putExtra("teamNumber", teamNumber);
        data.putExtra("matchNumber", matchNumber);
        data.putExtra("isRed", isRed);
        data.putExtra("positionControl", positionControl);
        data.putExtra("rotationControl", rotationControl);
        data.putExtra("climbed", climbed);
        data.putExtra("dead", dead);
        data.putExtra("missedShots", missedShots);
        data.putExtra("lowerShots", lowerShots);
        data.putExtra("upperShots", upperShots);
        data.putExtra("innerShots", innerShots);
        return data;
    }

    public void setUid(UUID id) {
        uid = id;
    }

    public UUID getUid() {
        return uid;
    }

    @Override @NonNull
    public String toString() {
        String x = "";
        x = x + teamNumber + ", ";
        x = x + matchNumber + ", ";
        x = x + isRed + ", ";
        x = x + positionControl + ", ";
        x = x + rotationControl + ", ";
        x = x + climbed + ", ";
        x = x + dead + ", ";
        //temp shots method
        x = x + missedShots + ", ";
        x = x + lowerShots + ", ";
        x = x + upperShots + ", ";
        x = x + innerShots;
        return x;
    }
}
