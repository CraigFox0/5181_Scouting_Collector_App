package co.craigfox.scoutingcollector5181;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShotDao {
    @Query("SELECT * FROM `Shot`")
    List<Shot> getAll();

    @Query("SELECT * FROM `Shot` WHERE uid IN (:userIds)")
    List<Shot> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM `Shot` WHERE uid LIKE :uid")
    Shot findByMatch(String uid);

    @Query("DELETE FROM 'Shot'")
    void deleteAll();

    @Insert
    void insertAll(Shot... shots);

    @Delete
    void delete(Shot shot);
}
