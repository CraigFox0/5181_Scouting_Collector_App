package co.craigfox.scoutingcollector5181;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Match {
    @PrimaryKey @NonNull
    public String uid;

    @ColumnInfo(name = "team_number")
    public int teamNumber;

    @ColumnInfo(name = "match_number")
    public int matchNumber;

    @ColumnInfo(name = "is_red")
    boolean isRed;

    @ColumnInfo(name = "position_control")
    boolean positionControl;

    @ColumnInfo(name = "rotation_control")
    boolean rotationControl;

    @ColumnInfo(name = "climbed")
    int climbed;

    @ColumnInfo(name = "dead")
    boolean dead;

    public Match(@NonNull String uid, int teamNumber, int matchNumber, boolean isRed, boolean positionControl, boolean rotationControl, int climbed, boolean dead) {
        this.uid = uid;
        this.teamNumber = teamNumber;
        this.matchNumber = matchNumber;
        this.isRed = isRed;
        this.positionControl = positionControl;
        this.rotationControl = rotationControl;
        this.climbed = climbed;
        this.dead = dead;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    int getMatchNumber() {
        return matchNumber;
    }

    int getTeamNumber() {
        return teamNumber;
    }
}
