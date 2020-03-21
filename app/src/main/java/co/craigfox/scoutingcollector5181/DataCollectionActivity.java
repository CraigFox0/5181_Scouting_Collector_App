package co.craigfox.scoutingcollector5181;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataCollectionActivity extends AppCompatActivity implements HeatMapDialog.NoticeDialogListener {

    EditText teamNumber;
    ToggleButton alliance;
    EditText matchNumber;
    CheckBox positionControl;
    CheckBox rotationControl;
    RadioGroup climbStatus;
    RadioButton noneButton;
    RadioButton parkButton;
    RadioButton climbButton ;
    RadioButton balanceButton;
    CheckBox dead;
    SeekBar missShots;
    SeekBar lowerShots;
    SeekBar upperShots;
    SeekBar innerShots;
    Button submitButton;

    ArrayList<Shot> shotData;
    UUID matchUUID;

    MatchDatabase matchDB;
    ShotDatabase shotDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        teamNumber = findViewById(R.id.editText_teamNumber);
        alliance = findViewById(R.id.toggleButton_color);
        matchNumber = findViewById(R.id.editText_matchNumber);
        positionControl = findViewById(R.id.checkBox_control_position);
        rotationControl = findViewById(R.id.checkBox_control_rotation);
        climbStatus = findViewById(R.id.radio_climb);
        noneButton = findViewById(R.id.radioButton_climb_none);
        parkButton = findViewById(R.id.radioButton_climb_park);
        climbButton = findViewById(R.id.radioButton_climb_climb);
        balanceButton = findViewById(R.id.radioButton_climb_balance);
        dead = findViewById(R.id.checkBox_dead);
        missShots = findViewById(R.id.seekBar_miss);
        lowerShots = findViewById(R.id.seekBar_lower);
        upperShots = findViewById(R.id.seekBar_upper);
        innerShots = findViewById(R.id.seekBar_inner);
        submitButton = findViewById(R.id.button_shot_submit);

        shotData = new ArrayList<>();
        matchUUID = UUID.randomUUID();

        matchDB = Room.databaseBuilder(getApplicationContext(), MatchDatabase.class, "match-info-database").allowMainThreadQueries().build();
        shotDB = Room.databaseBuilder(getApplicationContext(), ShotDatabase.class, "shot-info-database").allowMainThreadQueries().build();

        FloatingActionButton fab = findViewById(R.id.fab);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (missShots.getProgress() + lowerShots.getProgress() + upperShots.getProgress() + innerShots.getProgress() > 0) {
                    DialogFragment heatDialog = new HeatMapDialog();
                    heatDialog.show(getSupportFragmentManager(), "heat_map");
                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int climb = 0;
                int climbId = climbStatus.getCheckedRadioButtonId();
                if (climbId == parkButton.getId()) {
                    climb = 1;
                } else if (climbId == climbButton.getId()) {
                    climb = 2;
                }
                else if (climbId == balanceButton.getId()) {
                    climb = 3;
                }
                if (teamNumber.getText().toString().length() != 0 && matchNumber.getText().toString().length() != 0) {
                    Match match = new Match(matchUUID.toString(),
                            Integer.parseInt(teamNumber.getText().toString()),
                            Integer.parseInt(matchNumber.getText().toString()),
                            alliance.isChecked(),
                            positionControl.isChecked(),
                            rotationControl.isChecked(),
                            climb,
                            dead.isChecked()
                    );
                    matchDB.matchDao().insertAll(match);
                    shotDB.shotDao().insertAll(shotData.toArray(new Shot[]{}));
                    finish();
                }
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.e("MEOW", "Test");
        shotData.add(new Shot(UUID.randomUUID().toString(), matchUUID.toString(), ((HeatMapDialog) dialog).auton, ((HeatMapDialog) dialog).x, ((HeatMapDialog) dialog).y, missShots.getProgress(), lowerShots.getProgress(), upperShots.getProgress(), innerShots.getProgress()));
        missShots.setProgress(0);
        lowerShots.setProgress(0);
        upperShots.setProgress(0);
        innerShots.setProgress(0);
        Log.e("MEOW", ""+ innerShots.getProgress());
    }
}
