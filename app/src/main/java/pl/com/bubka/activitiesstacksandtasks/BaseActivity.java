package pl.com.bubka.activitiesstacksandtasks;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    /***
     * Gives information about, and interacts with, activities, services, and the containin process.
     * Methods in this class are for debugging or informational purposes and they should not be used to affect
     * any runtime behavior of your app.
     */
    public static ActivityManager activityManager;
    private static String packageName = "pl.com.bubka.activitiesstacksandtasks.";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(activityManager==null){
            activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        }
//        packageName = getApplicationContext().getPackageName();
    }

    public void startActivity(Activity activity, Class activityClass) {
        Intent intent = new Intent(activity, activityClass); // name variables with full name, not for example 'i' - leave one letter vars for loops

//        if(activityClass.equals(ActivityB.class)) {
//            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // code overrides the manifesto, but not always! its not safe to assume that. Looks like android maintains some priority order
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        }

        startActivity(intent);
    }

    public static String getAppTaskState(){
       StringBuilder stringBuilder = new StringBuilder();
        int totalNumberOfTasks = activityManager.getAppTasks().size();
        stringBuilder.append("Total number of tasks: " + totalNumberOfTasks + "\n");

        List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();

        for(ActivityManager.AppTask task : tasks) {
            // getTaskInfo() - deprecated, but taskId throws No field taskId ????? -> something with /system/framework/framework.jar from lolipipop
            stringBuilder.append("Task Id: "+ task.getTaskInfo().id +", Number of Activities : "+ task.getTaskInfo().numActivities +"\n");//Number of Activities in task - stack
            stringBuilder.append("BaseActivity:"+ task.getTaskInfo().baseActivity.getClassName().replace(packageName,"")+"\n");
            stringBuilder.append("TopActivity: " + task.getTaskInfo().topActivity.getClassName().replace(packageName, "") + "\n");

        }

        return stringBuilder.toString();
    }
}
