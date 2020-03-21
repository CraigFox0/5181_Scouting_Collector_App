package co.craigfox.scoutingcollector5181;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Shot {
    @PrimaryKey @NonNull
    public String uid;

    @ColumnInfo(name = "match_uid")
    String matchUid;

    @ColumnInfo(name = "x_coord")
    int xCoord;

    @ColumnInfo(name = "y_coord")
    int yCoord;

    @ColumnInfo(name = "auton")
    boolean auton;

    @ColumnInfo(name = "shots_miss")
    int missShots;

    @ColumnInfo(name = "shots_lower")
    int lowerShots;

    @ColumnInfo(name = "shots_upper")
    int upperShots;

    @ColumnInfo(name = "shots_inner")
    int innerShots;

    Shot(@NonNull String uid, String matchUid, boolean auton, int xCoord, int yCoord, int missShots, int lowerShots, int upperShots, int innerShots) {
        this.uid =  uid;
        this.matchUid = matchUid;
        this.auton = auton;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.missShots = missShots;
        this.lowerShots = lowerShots;
        this.upperShots = upperShots;
        this.innerShots = innerShots;
    }
}
