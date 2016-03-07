package com.samsunguet.sev_user.openstackapi;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;


import com.samsunguet.sev_user.openstackapi.api.OpenStackAPI;
import com.samsunguet.sev_user.openstackapi.log.MyLog;
import com.samsunguet.sev_user.openstackapi.object.Tenant;
import com.samsunguet.sev_user.openstackapi.object.User;



public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLog.log("Start main activity");

        Tenant tenant = new Tenant("s2uet");
        User user = new User("long", "abc13579", tenant);
        OpenStackAPI requestToken = new OpenStackAPI("http://107.113.190.176:5000/v2.0/tokens", user);
        new GetToken().execute(requestToken);


    }

    private class GetToken extends AsyncTask<OpenStackAPI, Void, User> {

        @Override
        protected User doInBackground(OpenStackAPI... params) {
            MyLog.log("start async");
            params[0].setTokenandStorageurl();
            return params[0].getUser();
        }

        @Override
        protected void onPostExecute(User user) {
            //super.onPostExecute(user);
            MyLog.log(user.toString());
        }
    }
}
