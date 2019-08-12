package pl.com.bubka.activitiesstacksandtasks;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityB extends BaseActivity implements View.OnClickListener {

    private static final String TAG=ActivityA.class.getSimpleName();
    private static int instanceCounter=0;
    private int currentInstanceValue;

    private Button buttonStartActivityA;
    private Button buttonStartActivityB;
    private Button buttonStartActivityC;
    private Button buttonStartActivityD;
    private TextView textViewTaskInfo;
    private TextView textViewInstanceValue;


    public ActivityB(){
        super();
        instanceCounter++;
        currentInstanceValue=instanceCounter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        buttonStartActivityA= findViewById(R.id.buttonStartActivityA);
        buttonStartActivityB= findViewById(R.id.buttonStartActivityB);
        buttonStartActivityC= findViewById(R.id.buttonStartActivityC);
        buttonStartActivityD= findViewById(R.id.buttonStartActivityD);

        textViewTaskInfo= findViewById(R.id.textViewTaskInfo);
        textViewInstanceValue= findViewById(R.id.textViewInstanceValue);
        textViewInstanceValue.append(",Current instance: "+currentInstanceValue);

        buttonStartActivityA.setOnClickListener(this);
        buttonStartActivityB.setOnClickListener(this);
        buttonStartActivityC.setOnClickListener(this);
        buttonStartActivityD.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.buttonStartActivityA: startActivity(this, ActivityA.class); break;
            case R.id.buttonStartActivityB: startActivity(this, ActivityB.class); break;
            case R.id.buttonStartActivityC: startActivity(this, ActivityC.class); break;
            case R.id.buttonStartActivityD: startActivity(this, ActivityD.class); break;
            default: break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Instances: " + currentInstanceValue);
        textViewTaskInfo.setText(getAppTaskState());
    }

}
