package com.example.leavestrail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private static final String LEAVE_PREFS_NAME = "LeavePrefs";
    private static final String SICK_LEAVE = "Sick Leave";
    private static final String CASUAL_LEAVE = "Casual Leave";
    private static final String MEDICAL_LEAVE = "Medical Leave";

    private LeaveType sickLeave;
    private LeaveType casualLeave;
    private LeaveType medicalLeave;

    private EditText etSickLeave;
    private EditText etCasualLeave;
    private EditText etMedicalLeave;

    private Button btnApplyLeave;
    private BottomSheetDialog bottomSheetDialog;

    private SharedPreferences leavePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leavePrefs = getSharedPreferences(LEAVE_PREFS_NAME, MODE_PRIVATE);

        sickLeave = new LeaveType(SICK_LEAVE, 12);
        casualLeave = new LeaveType(CASUAL_LEAVE, 13);
        medicalLeave = new LeaveType(MEDICAL_LEAVE, 15);

        etSickLeave = findViewById(R.id.etSickLeave);
        etCasualLeave = findViewById(R.id.etCasualLeave);
        etMedicalLeave = findViewById(R.id.etMedicalLeave);

        updateLeaveDetails(etSickLeave, sickLeave);
        updateLeaveDetails(etCasualLeave, casualLeave);
        updateLeaveDetails(etMedicalLeave, medicalLeave);

        btnApplyLeave = findViewById(R.id.btnApplyLeave);

        btnApplyLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
    }

    private void showBottomSheet() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_leave, null);

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(bottomSheetView);

        RadioGroup rgLeaveOptions = bottomSheetView.findViewById(R.id.rgLeaveOptions);
        Button btnApply = bottomSheetView.findViewById(R.id.btnApply);

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgLeaveOptions.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton radioButton = bottomSheetDialog.findViewById(selectedId);
                    String selectedLeaveType = radioButton.getText().toString();
                    applyLeave(selectedLeaveType);

                    bottomSheetDialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Select a type of leave", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottomSheetDialog.show();
    }

    private void applyLeave(String leaveType) {
        LeaveType selectedLeaveType = null;

        switch (leaveType) {
            case SICK_LEAVE:
                selectedLeaveType = sickLeave;
                break;
            case CASUAL_LEAVE:
                selectedLeaveType = casualLeave;
                break;
            case MEDICAL_LEAVE:
                selectedLeaveType = medicalLeave;
                break;
        }

        if (selectedLeaveType != null && selectedLeaveType.getAvailableLeaves() > 0) {
            selectedLeaveType.reduceLeaveCount();
            String message = leaveType + " leave applied\n" +
                    "Leaves left: " + selectedLeaveType.getAvailableLeaves();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            updateLeaveDetails(findViewById(getLeaveTypeEditTextId(leaveType)), selectedLeaveType);

            saveLeaveDetails(selectedLeaveType);
        } else {
            Toast.makeText(this, "No more " + leaveType + " leaves available", Toast.LENGTH_SHORT).show();
        }
    }

    private int getLeaveTypeEditTextId(String leaveType) {
        switch (leaveType) {
            case SICK_LEAVE:
                return R.id.etSickLeave;
            case CASUAL_LEAVE:
                return R.id.etCasualLeave;
            case MEDICAL_LEAVE:
                return R.id.etMedicalLeave;
            default:
                return 0;
        }
    }

    private void updateLeaveDetails(EditText editText, LeaveType leaveType) {
        String leaveInfo = "Total Leaves: " + leaveType.getTotalLeaves() + "\n" +
                "Leaves Used: " + leaveType.getUsedLeaves() + "\n" +
                "Leaves Left: " + leaveType.getAvailableLeaves();

        editText.setText(leaveInfo);
    }

    private void saveLeaveDetails(LeaveType leaveType) {
        SharedPreferences.Editor editor = leavePrefs.edit();
        editor.putInt(leaveType.getName() + "_Used", leaveType.getUsedLeaves());
        editor.apply();
    }

    private void loadLeaveDetails(LeaveType leaveType) {
        int usedLeaves = leavePrefs.getInt(leaveType.getName() + "_Used", 0);
        leaveType.setUsedLeaves(usedLeaves);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLeaveDetails(sickLeave);
        loadLeaveDetails(casualLeave);
        loadLeaveDetails(medicalLeave);
        updateLeaveDetails(etSickLeave, sickLeave);
        updateLeaveDetails(etCasualLeave, casualLeave);
        updateLeaveDetails(etMedicalLeave, medicalLeave);
    }
}
