package co.craigfox.scoutingcollector5181;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Shot.class}, version = 1)
public abstract class ShotDatabase extends RoomDatabase {
    public abstract ShotDao shotDao();
}

