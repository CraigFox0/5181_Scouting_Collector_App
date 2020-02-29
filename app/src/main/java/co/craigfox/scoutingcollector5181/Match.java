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
    public boolean isRed;

    @ColumnInfo(name = "color_control")
    public boolean colorControl;

    @ColumnInfo(name = "rotation_control")
    public boolean rotationControl;

    @ColumnInfo(name = "climbed")
    public int climbed;

    @ColumnInfo(name = "dead")
    public boolean dead;

    @ColumnInfo(name = "missed_shots")
    public int missedShots;

    @ColumnInfo(name = "lower_shots")
    public int lowerShots;

    @ColumnInfo(name = "upper_shots")
    public int upperShots;

    @ColumnInfo(name = "inner_shots")
    public int innerShots;
}
