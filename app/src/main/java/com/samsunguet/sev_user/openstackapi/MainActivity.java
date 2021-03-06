package com.samsunguet.sev_user.openstackapi;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;


import com.samsunguet.sev_user.openstackapi.api.IdentifyAPI;
import com.samsunguet.sev_user.openstackapi.api.StorageAPI;
import com.samsunguet.sev_user.openstackapi.log.MyLog;
import com.samsunguet.sev_user.openstackapi.object.MyContact;
import com.samsunguet.sev_user.openstackapi.object.Tenant;
import com.samsunguet.sev_user.openstackapi.object.Token;
import com.samsunguet.sev_user.openstackapi.object.User;

import java.net.MalformedURLException;


public class MainActivity extends Activity {

    public static final String URL_DEFAULT = "http://107.113.190.176";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MyLog.log("Start main activity");
//
//        Tenant tenant = new Tenant("s2uet");
//        Token token = new Token("017c311b8f1343cc8e85f800e9145383");
//        User user = new User("long", "abc13579",token, tenant, "http://107.113.190.176:8080/v1/AUTH_579b0354975e4676a547084080449aff");
//        //IdentifyAPI requestToken = new IdentifyAPI("http://107.113.190.176:5000/v2.0/tokens", user);
//       // new GetToken().execute(requestToken);
//        MyLog.log(user.getToken().getId());
//        new GetFoler().execute(new StorageAPI(user));

        MyContact contact = new MyContact(this);
        MyLog.log(contact.getContactInJson());
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
