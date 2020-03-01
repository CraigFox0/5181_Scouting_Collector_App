package co.craigfox.scoutingcollector5181;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    EditText missShots;
    EditText lowerShots;
    EditText upperShots;
    EditText innerShots;
    EditText missAutonShots;
    EditText lowerAutonShots;
    EditText upperAutonShots;
    EditText innerAutonShots;

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
        missShots = findViewById(R.id.editText_miss);
        lowerShots = findViewById(R.id.editText_lower);
        upperShots = findViewById(R.id.editText_upper);
        innerShots = findViewById(R.id.editText_inner);
        missAutonShots = findViewById(R.id.editText_auton_miss);
        lowerAutonShots = findViewById(R.id.editText_auton_lower);
        upperAutonShots = findViewById(R.id.editText_auton_upper);
        innerAutonShots = findViewById(R.id.editText_auton_inner);
        FloatingActionButton fab = findViewById(R.id.fab);

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
                if (teamNumber != null && matchNumber != null) {
                    MatchData matchInfo = new MatchData(Integer.parseInt(teamNumber.getText().toString()),
                            Integer.parseInt(matchNumber.getText().toString()),
                            alliance.isChecked(),
                            positionControl.isChecked(),
                            rotationControl.isChecked(),
                            climb,
                            dead.isChecked(),
                            Integer.parseInt(0 + missShots.getText().toString()),
                            Integer.parseInt(0 + lowerShots.getText().toString()),
                            Integer.parseInt(0 + upperShots.getText().toString()),
                            Integer.parseInt(0 + innerShots.getText().toString()),
                            Integer.parseInt(0 + missAutonShots.getText().toString()),
                            Integer.parseInt(0 + lowerAutonShots.getText().toString()),
                            Integer.parseInt(0 + upperAutonShots.getText().toString()),
                            Integer.parseInt(0 + innerAutonShots.getText().toString())
                    );
                    setResult(RESULT_OK, matchInfo.toIntent());
                    finish();
                }
            }
        });
    }
}
