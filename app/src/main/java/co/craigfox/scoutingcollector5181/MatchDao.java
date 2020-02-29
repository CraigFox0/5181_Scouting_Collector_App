package co.craigfox.scoutingcollector5181;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MatchDao {
    @Query("SELECT * FROM `Match`")
    List<Match> getAll();

    @Query("SELECT * FROM `Match` WHERE uid IN (:userIds)")
    List<Match> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM `Match` WHERE team_number LIKE :teamNum AND " +
            "match_number LIKE :matchNum LIMIT 1")
    Match findByMatch(int teamNum, int matchNum);

    @Insert
    void insertAll(Match... matches);

    @Delete
    void delete(Match match);
}
