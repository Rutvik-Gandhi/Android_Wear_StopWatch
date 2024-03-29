package www.rutvikgandhiassignment1.com;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declaring necessary variables
    private Button startButton01, stopButton01, resetButton01;
    private TextView timeTextView01;
    private boolean isrunning = false; // Variable to track if the timer is running
    private int seconds = 0; // Variable to keep track of the seconds elapsed

    // Initializing the handler
    private Handler handler01 = new Handler(); // Handler to manage timing events

    @SuppressLint("MissingId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Setting the content view to the corresponding layout

        // Initializing UI elements
        startButton01 = findViewById(R.id.Startbutton);
        stopButton01 = findViewById(R.id.Stopbutton);
        resetButton01 = findViewById(R.id.Resetbutton);
        timeTextView01 = findViewById(R.id.TimeTextView);

        // Setting click listeners for the buttons
        startButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrPauseTimer();
            }
        });

        stopButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        resetButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
    }

    // Function to start or pause the timer based on the current state
    private void startOrPauseTimer() {
        if (!isrunning) {
            isrunning = true;
            startButton01.setText("Pause");
            startTimer(); // Starting the timer
        } else {
            isrunning = false;
            startButton01.setText("Resume");
        }
    }

    // Function to initiate the timer
    private void startTimer() {
        handler01.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isrunning) {
                    seconds++; // Incrementing the seconds
                    updateTimerText(); // Updating the displayed timer text
                    startTimer(); // Initiating the timer again
                }
            }
        }, 1000); // Delay of 1000ms (1 second)
    }

    // Function to stop the timer
    private void stopTimer() {
        isrunning = false;
        startButton01.setText("Start");
    }

    // Function to reset the timer
    private void resetTimer() {
        isrunning = false;
        startButton01.setText("Start");
        seconds = 0; // Resetting seconds to 0
        updateTimerText(); // Updating the displayed timer text
    }

    // Function to update the text of the timer TextView
    private void updateTimerText() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, secs);
        timeTextView01.setText(timeString);
    }
}
