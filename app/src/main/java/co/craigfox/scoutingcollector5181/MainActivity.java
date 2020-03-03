package co.craigfox.scoutingcollector5181;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO Heatmap
//TODO Phone Connection
//TODO back button behaviour
//TODO fouls
//TODO change ToggleButton color for alliance
//TODO remove MatchData class (redundant)

public class MainActivity extends AppCompatActivity {

    MatchDatabase db;

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataCollectionActivity.class);
                startActivityForResult(intent, 69);
            }
        });

        db = Room.databaseBuilder(getApplicationContext(), MatchDatabase.class, "match-info-database").allowMainThreadQueries().build();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<MatchData> md = new ArrayList<>();
        for (Match mMatch: db.matchDao().getAll()) {
            MatchData data = new MatchData(mMatch.teamNumber, mMatch.matchNumber, mMatch.isRed, mMatch.positionControl, mMatch.rotationControl, mMatch.climbed, mMatch.dead, mMatch.missedShots, mMatch.lowerShots, mMatch.upperShots, mMatch.innerShots);
            data.setUid(UUID.fromString(mMatch.uid));
            md.add(data);
        }
        mAdapter = new DataAdapter(md);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        db = Room.databaseBuilder(getApplicationContext(), MatchDatabase.class, "match-info-database").allowMainThreadQueries().build();
        List<MatchData> md = new ArrayList<>();
        for (Match mMatch: db.matchDao().getAll()) {
            MatchData data = new MatchData(mMatch.teamNumber, mMatch.matchNumber, mMatch.isRed, mMatch.positionControl, mMatch.rotationControl, mMatch.climbed, mMatch.dead, mMatch.missedShots, mMatch.lowerShots, mMatch.upperShots, mMatch.innerShots);
            data.setUid(UUID.fromString(mMatch.uid));
            md.add(data);
        }
        mAdapter = new DataAdapter(md);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.clear_database) {
            db.matchDao().deleteAll();
            //TODO way to reset without restarting (b/c poor design and back button behaviour)
            recreate();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 69) {
            if (resultCode == RESULT_OK) {
                MatchData matchInfo = new MatchData(data);
                Match match = new Match();
                match.uid = UUID.randomUUID().toString();
                match.teamNumber = matchInfo.getTeamNumber();
                match.matchNumber = matchInfo.getMatchNumber();
                match.isRed = matchInfo.isRed();
                match.positionControl = matchInfo.isPositionControl();
                match.rotationControl = matchInfo.isRotationControl();
                match.climbed = matchInfo.getClimbed();
                match.dead = matchInfo.isDead();
                match.missedShots = matchInfo.getMissedShots();
                match.lowerShots = matchInfo.getLowerShots();
                match.upperShots = matchInfo.getUpperShots();
                match.innerShots = matchInfo.getInnerShots();

                db.matchDao().insertAll(match);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
