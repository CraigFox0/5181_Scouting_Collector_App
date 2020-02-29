package co.craigfox.scoutingcollector5181;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Match.class}, version = 1)
public abstract class MatchDatabase extends RoomDatabase {
    public abstract MatchDao matchDao();
}
