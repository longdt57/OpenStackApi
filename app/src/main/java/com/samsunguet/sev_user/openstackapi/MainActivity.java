package com.samsunguet.sev_user.openstackapi;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;


import com.samsunguet.sev_user.openstackapi.api.IdentifyAPI;
import com.samsunguet.sev_user.openstackapi.api.StorageAPI;
import com.samsunguet.sev_user.openstackapi.log.MyLog;
import com.samsunguet.sev_user.openstackapi.object.Tenant;
import com.samsunguet.sev_user.openstackapi.object.Token;
import com.samsunguet.sev_user.openstackapi.object.User;



public class MainActivity extends Activity {

    public static final String URL_DEFAULT = "http://107.113.190.176";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLog.log("Start main activity");

        Tenant tenant = new Tenant("s2uet");
        Token token = new Token("12998130227a4f5a9b1bf577957f38e4");
        User user = new User("long", "abc13579",token, tenant, "http://107.113.190.176:8080/v1/AUTH_579b0354975e4676a547084080449aff");
        //IdentifyAPI requestToken = new IdentifyAPI("http://107.113.190.176:5000/v2.0/tokens", user);
        //new GetToken().execute(requestToken);
        MyLog.log(user.getToken().getId());
        new GetFoler().execute(new StorageAPI(user));

    }

    private class GetToken extends AsyncTask<IdentifyAPI, Void, User> {

        @Override
        protected User doInBackground(IdentifyAPI... params) {
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
    private class GetFoler extends  AsyncTask<StorageAPI, Void, Void>{
        @Override
        protected Void doInBackground(StorageAPI... params) {
            MyLog.log("start getfolders");
            params[0].getFolderandFileList("/Folder1");
            return null;
        }
    }
}
