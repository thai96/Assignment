package com.example.assignment.create_alarm;

import android.os.Build;
import android.widget.TimePicker;

public class TimePickerUtil {
    public static int getTimePickerHour(TimePicker tp){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return tp.getHour();
        }else{
            return tp.getCurrentHour();
        }
    }

    public static int getTImePickerMinute(TimePicker tp){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return tp.getMinute();
        }
        else{
            return tp.getCurrentMinute();
        }
    }
}