package co.craigfox.scoutingcollector5181;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DataCollectionActivity extends AppCompatActivity {

    EditText teamNumber;
    ToggleButton alliance;
    EditText matchNumber;
    CheckBox colorControl;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        teamNumber = findViewById(R.id.editText_teamNumber);
        alliance = findViewById(R.id.toggleButton_color);
        matchNumber = findViewById(R.id.editText_matchNumber);
        colorControl = findViewById(R.id.checkBox_control_color);
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
                            colorControl.isChecked(),
                            rotationControl.isChecked(),
                            climb,
                            dead.isChecked(),
                            Integer.parseInt(0 + missShots.getText().toString()),
                            Integer.parseInt(0 + lowerShots.getText().toString()),
                            Integer.parseInt(0 + upperShots.getText().toString()),
                            Integer.parseInt(0 + innerShots.getText().toString())
                    );
                    setResult(RESULT_OK, matchInfo.toIntent());
                    finish();
                }
            }
        });
    }
}
