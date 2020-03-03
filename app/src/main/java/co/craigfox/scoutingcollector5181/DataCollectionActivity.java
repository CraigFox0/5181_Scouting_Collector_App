package co.craigfox.scoutingcollector5181;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DataCollectionActivity extends AppCompatActivity {

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
        FloatingActionButton fab = findViewById(R.id.fab);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new HeatMapDialog();
                newFragment.show(getSupportFragmentManager(), "heat_map");
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
                    MatchData matchInfo = new MatchData(Integer.parseInt(teamNumber.getText().toString()),
                            Integer.parseInt(matchNumber.getText().toString()),
                            alliance.isChecked(),
                            positionControl.isChecked(),
                            rotationControl.isChecked(),
                            climb,
                            dead.isChecked(),
                            missShots.getProgress(),
                            lowerShots.getProgress(),
                            upperShots.getProgress(),
                            innerShots.getProgress()
                    );
                    setResult(RESULT_OK, matchInfo.toIntent());
                    finish();
                }
            }
        });
    }
}
