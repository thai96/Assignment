package com.example.assignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.assignment.viewModels.CreateAlarmViewModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CreateAlarmFragment extends Fragment {
    @BindView(R.id.fragment_createalarm_timePicker)
    TimePicker timePicker;

    @BindView(R.id.fragment_createalarm_title)
    EditText title;

    @BindView(R.id.cancelButton)
    Button cancelButton;

    @BindView(R.id.fragment_createalarm_scheduleAlarm)
    Button scheduleAlarm;

    @BindView(R.id.fragment_createalarm_recurring)
    CheckBox recurring;

    @BindView(R.id.fragment_createalarm_checkMon)
    CheckBox mon;

    @BindView(R.id.fragment_createalarm_checkTue)
    CheckBox tue;

    @BindView(R.id.fragment_createalarm_checkWed)
    CheckBox wed;

    @BindView(R.id.fragment_createalarm_checkThu)
    CheckBox thu;

    @BindView(R.id.fragment_createalarm_checkFri)
    CheckBox fri;

    @BindView(R.id.fragment_createalarm_checkSat)
    CheckBox sat;

    @BindView(R.id.fragment_createalarm_checkSun)
    CheckBox sun;

    @BindView(R.id.fragment_createalarm_recurring_options)
    LinearLayout recurringOptions;

    @BindView(R.id.fragment_createalarm_tonechooser)
    Spinner toneChooser;

    Map<String, Integer> toneMap = new HashMap<String, Integer>();
    ArrayList<String> musicList = new ArrayList<String>();

    private CreateAlarmViewModel createAlarmViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createAlarmViewModel = ViewModelProviders.of(this).get(CreateAlarmViewModel.class);
        listRaw();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createalarm, container, false);

        ButterKnife.bind(this, view);

        recurring.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recurringOptions.setVisibility(View.VISIBLE);
                } else {
                    recurringOptions.setVisibility(View.GONE);
                }
            }
        });

        scheduleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleAlarm();
                Navigation.findNavController(view).navigate(R.id.action_createAlarmFragment_to_alarmsListFragment);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_createAlarmFragment_to_alarmsListFragment);
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, musicList);

        toneChooser.setAdapter(adapter);
    }

    private void scheduleAlarm() {
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);

        Alarm alarm = new Alarm(
                alarmId,
                TimePickerUtil.getTimePickerHour(timePicker),
                TimePickerUtil.getTImePickerMinute(timePicker),
                title.getText().toString(),
                true,
                recurring.isChecked(),
                mon.isChecked(),
                tue.isChecked(),
                wed.isChecked(),
                thu.isChecked(),
                fri.isChecked(),
                sat.isChecked(),
                sun.isChecked(),
                toneMap.get(toneChooser.getSelectedItem().toString())
        );

        createAlarmViewModel.insert(alarm);

        alarm.schedule(getContext());
    }

    public void listRaw() {
        Field[] fields = R.raw.class.getFields();
        for (int count = 0; count < fields.length; count++) {
            String filename = fields[count].getName();
            int id = getResources().getIdentifier(filename, "raw", requireActivity().getPackageName());
            toneMap.put(filename, id);
            musicList.add(filename);
        }
    }

}