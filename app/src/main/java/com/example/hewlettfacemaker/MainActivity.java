package com.example.hewlettfacemaker;
//@Hewlett De Lara

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, Spinner.OnItemSelectedListener{

    protected Face face;
    protected SeekBar redSeekBar, blueSeekBar, greenSeekBar;
    protected TextView redTextView, blueTextView, greenTextView;
    protected RadioButton hairButton, eyesButton, skinButton;
    int checkClicked;
    FaceView faceView;
    Spinner hairSpinner;
    ArrayAdapter<String> adapter;
    Button buttonRandom;
    String [] arraySpinner = new String[] {
            "No Selection",
            "Bowl Cut",
            "Afro",
            "Buzz Cut"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the FaceView and new Face
        faceView = (FaceView) findViewById(R.id.faceView);
        face = new Face ();
        faceView.setFace(face);

        // Initialize the drop-down spinner and its listener
        hairSpinner = (Spinner) findViewById(R.id.hairSpinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(adapter);
        hairSpinner.setSelection(0);
        hairSpinner.setOnItemSelectedListener(this);

        // Initialize button and its listener
        buttonRandom = (Button) findViewById(R.id.randomFaceButton);
        buttonRandom.setOnClickListener(this);

        // Initialize the three radio buttons and their listeners
        hairButton = (RadioButton) findViewById(R.id.hairRadioButton);
        hairButton.setOnClickListener(this); // calling onClick() method
        eyesButton = (RadioButton) findViewById(R.id.eyesRadioButton);
        eyesButton.setOnClickListener(this); // calling onClick() method
        skinButton = (RadioButton) findViewById(R.id.skinRadioButton);
        skinButton.setOnClickListener(this); // calling onClick() method

        // Initialize the three SeekBars, sets the max of SeekBars to 255,
        //and call their listeners.
        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        redSeekBar.setMax(255);
        redSeekBar.setOnSeekBarChangeListener(this);
        redTextView = (TextView) findViewById(R.id.redTextView);
        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        blueSeekBar.setMax(255);
        blueSeekBar.setOnSeekBarChangeListener(this);
        blueTextView = (TextView) findViewById(R.id.blueTextView);
        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        greenSeekBar.setMax(255);
        greenSeekBar.setOnSeekBarChangeListener(this);
        greenTextView = (TextView) findViewById(R.id.greenTextView);
    }

    // Creates a listener for object selected on the spinner
    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        //sets the position of the item selected to a specific hair style (1=bowl cut, 2=afro, or 3=buzz cut)
        face.setHairStyle(position);
        faceView.invalidate();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // Empty since no actions are done when nothing is selected
    }

    // Creates the listeners for the clicks of individual buttons
    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {

            case R.id.hairRadioButton: //if the hair button is clicked, the hair color is represented by the SeekBars
                redSeekBar.setProgress(this.face.getHairColor(0));
                greenSeekBar.setProgress(this.face.getHairColor(1));
                blueSeekBar.setProgress(this.face.getHairColor(2));
                checkClicked = 0; //equal to zero to record that hair button has been clicked
                break;

            case R.id.eyesRadioButton: //if the eyes button is clicked, the eye color is represented by the SeekBars
                redSeekBar.setProgress(this.face.getEyeColor(0));
                greenSeekBar.setProgress(this.face.getEyeColor(1));
                blueSeekBar.setProgress(this.face.getEyeColor(2));
                checkClicked = 1; //equal to one to record that eye button has been clicked
                break;

            case R.id.skinRadioButton: //if the skin button is clicked, the skin color is represented by the SeekBars
                redSeekBar.setProgress(this.face.getSkinColor(0));
                greenSeekBar.setProgress(this.face.getSkinColor(1));
                blueSeekBar.setProgress(this.face.getSkinColor(2));
                checkClicked = 2; //equal to two to record that skin button has been clicked
                break;
            case R.id.randomFaceButton: //if the random button is clicked, a new random face is drawn
                face.Randomize();
                faceView.invalidate();
                break;
            default:
                break;
        }
    }

    // Creates the listeners for the three SeekBars
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.redSeekBar:
                redTextView.setText("Red: " + progress);
                switch(checkClicked)
                {
                    case 0:
                        face.setHairColor(progress, 0);
                        break;
                    case 1:
                        face.setEyeColor(progress, 0);
                        break;
                    case 2:
                        face.setSkinColor(progress, 0);
                        break;
                }
                break;
            case R.id.blueSeekBar:
                blueTextView.setText("Blue: " + progress);
                switch(checkClicked)
                {
                    case 0:
                        face.setHairColor(progress, 2);
                        break;
                    case 1:
                        face.setEyeColor(progress, 2);
                        break;
                    case 2:
                        face.setSkinColor(progress, 2);
                        break;
                }
                break;
            case R.id.greenSeekBar:
                greenTextView.setText("Green: " + progress);
                switch(checkClicked)
                {
                    case 0:
                        face.setHairColor(progress, 1);
                        break;
                    case 1:
                        face.setEyeColor(progress, 1);
                        break;
                    case 2:
                        face.setSkinColor(progress, 1);
                        break;
                }
                break;

        }
        faceView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Does nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Does nothing
    }
}