package com.paz.common.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.paz.common.R;
import com.paz.common.api.GarageController;
import com.paz.common.databinding.ActivityGenericBinding;
import com.paz.common.model.GarageModel;
import com.paz.common.model.Session;
import com.paz.common.room.MyDB;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;


 public abstract class GenericGarageActivity extends AppCompatActivity {
    private static final String TAG = "pttt";
    private GarageModel garageModel;
    private ActivityGenericBinding binding;
    private Session currentSession;
    private final SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm" , Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGenericBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getGarageData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentSession = new Session();
        currentSession.setStart(System.currentTimeMillis());
        MyDB.getInstance().getLastSession(this::bindLastSession);
        MyDB.getInstance().getTotalSpendsTime(this::bindTotalTime);
    }

    private void bindLastSession(Session session) {

        String  str = session == null? "this is your first use" : format.format(session.getStart());
        binding.mainLBLLastSeen.setText(getString(R.string.lastSeen,str ));

    }

    private void bindTotalTime(long time) {
        binding.mainLBLTotalTime.setText(getString(R.string.totalTime, getPrettyTimeSpent(time)));

    }

    @Override
    protected void onStop() {
        super.onStop();
        currentSession.setEnd(System.currentTimeMillis());
        currentSession.setTotal(currentSession.getEnd() - currentSession.getStart());
        MyDB.getInstance().insertSession(currentSession);
        currentSession = null;
    }

    private void getGarageData() {
        Log.d(TAG, "getGarageData: ");
        new GarageController().fetchGarage(garage -> {
            this.garageModel = garage;
            bindGarageData();
            Log.d(TAG, "callback: ");

        });
    }

    private void bindGarageData() {
        binding.mainLBLAddress.setText(getString(R.string.garageAddress, garageModel.getAddress()));
        binding.mainLBLName.setText(getString(R.string.garageName, garageModel.getName()));
        binding.mainLBLOpen.setText(getString(R.string.garageOpen, String.valueOf(garageModel.isOpen())));
        String cars = Arrays.stream(garageModel.getCars()).map(Objects::toString).collect(Collectors.joining(", "));
        binding.mainLBLCars.setText(getString(R.string.garageCars, cars));
    }


    private String getPrettyTimeSpent(Long time) {

        //milliseconds
        long different = time;

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        StringBuilder sb = new StringBuilder();
        if (elapsedDays != 0)
            sb.append(elapsedDays).append("  days, ");
        if (elapsedHours != 0)

            sb.append(elapsedHours).
                    append("  hours, ");
        if (elapsedMinutes != 0)
            sb.append(elapsedMinutes).append("  minutes, ");
        if (elapsedSeconds != 0)
            sb.append(elapsedSeconds).append(" seconds");

        return sb.toString().isEmpty()? "first use" : sb.toString();
    }

    protected void setBackgroundColor(int color){
        binding.mainLAYBase.setBackgroundColor(color);
    }
     protected void setAppName(String name){
         binding.mainLBLAppName.setText(getString(R.string.appName,name));
     }

}