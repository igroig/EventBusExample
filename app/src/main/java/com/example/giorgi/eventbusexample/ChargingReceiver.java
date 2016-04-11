package com.example.giorgi.eventbusexample;

/**
 * Created by giorgi on 4/11/2016.
 */
import de.greenrobot.event.EventBus;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;

import com.squareup.otto.Bus;

public class ChargingReceiver extends BroadcastReceiver {

    private EventBus bus = EventBus.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        String event = null;

        // Get current time
        Time now = new Time();
        now.setToNow();
        String timeOfEvent = now.format("%H:%M");

        String eventData = "@" + timeOfEvent + " this device started ";
        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            event=eventData+"charging.";
        } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            event=eventData+"discharging.";
        }

        // Post the event
        bus.post(event);
    }

}