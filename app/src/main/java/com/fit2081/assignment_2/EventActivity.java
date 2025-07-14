package com.fit2081.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.fit2081.assignment_2.entities.Event;
import com.fit2081.assignment_2.provider.EventViewModel;

import java.util.StringTokenizer;

public class EventActivity extends AppCompatActivity implements IMessage {

    EditText editTextEventId;
    EditText editTextEventName;
    EditText editTextCategoryId;
    Switch isActive;
    EditText editTextTicketsAvailable;
    SharedPreferences sP;
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // Initialize ViewModel
        eventViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(EventViewModel.class);

        editTextEventId = findViewById(R.id.editTextEventId);
        editTextEventName = findViewById(R.id.editTextEventName);
        editTextCategoryId = findViewById(R.id.editTextCategoryId);
        isActive = findViewById(R.id.isActive);
        editTextTicketsAvailable = findViewById(R.id.editTextTicketsAvailable);
        sP = getSharedPreferences(Keys.FILE_NAME, MODE_PRIVATE);

        ActivityCompat.requestPermissions(this, new String[]{"android.permission.SEND_SMS",
                "android.permission.RECEIVE_SMS", "android.permission.READ_SMS"}, 0);
        SMSReceiver.bindListener(this);
    }

    private void updateFields(String _eventName, String _categoryId, String _ticketsAvailable, boolean _isActive) {
        editTextEventName.setText(_eventName);
        editTextCategoryId.setText(_categoryId);
        editTextTicketsAvailable.setText(_ticketsAvailable);
        isActive.setChecked(_isActive);
    }

    @Override
    public void messageReceived(String message) {
        Log.d("Assignment_1_TAG", "MessageReceived: " + message);

        try {
            int commandIndex = message.indexOf(':');
            String command = message.substring(0, commandIndex);
            String params = message.substring(commandIndex + 1);
            StringTokenizer sT = new StringTokenizer(params, ";");
            if (command.equals("event")) {
                String eventName = sT.nextToken();
                String categoryId = sT.nextToken();
                String ticketsAvailable = sT.nextToken();
                String isActiveSt = sT.nextToken();
                if (isActiveSt.equals("TRUE") || isActiveSt.equals("FALSE")) {
                    boolean isActive = isActiveSt.equals("TRUE");
                    updateFields(eventName, categoryId, ticketsAvailable, isActive);
                } else {
                    throw new Exception("Invalid Format");
                }
            } else {
                Toast.makeText(this, "Unknown or invalid command", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Unknown or invalid command", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSaveClick(View view) {
        saveRecordSave();
    }

    private void saveRecordSave() {
        String eventIdId = Utils.generateEventId();
        String eventName = editTextEventName.getText().toString();
        String categoryId = editTextCategoryId.getText().toString();
        int ticketsAvailable;

        try {
            ticketsAvailable = Integer.parseInt(editTextTicketsAvailable.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Tickets available must be a valid integer", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isActiveChecked = isActive.isChecked();

        // Save to SharedPreferences
        SharedPreferences.Editor editor = sP.edit();
        editor.putString(Keys.EVENT_ID, eventIdId);
        editor.putString(Keys.EVENT_NAME, eventName);
        editor.putString(Keys.EVENT_CATEGORY_ID, categoryId);
        editor.putBoolean(Keys.EVENT_IS_ACTIVE, isActiveChecked);
        editor.putInt(Keys.EVENT_TICKETS_AVAILABLE, ticketsAvailable);
        editor.apply();

        editTextEventId.setText(eventIdId);

        // Save to Room database
        Event newEvent = new Event(eventName, categoryId, ticketsAvailable, isActiveChecked);
        eventViewModel.insert(newEvent);

        Toast.makeText(this, "Event saved: " + eventIdId + " to " + categoryId, Toast.LENGTH_SHORT).show();
    }
}
