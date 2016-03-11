package com.samsunguet.sev_user.openstackapi.object;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.samsunguet.sev_user.openstackapi.log.MyLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sev_user on 3/11/2016.
 */
public class MyContact {
    public static final String NAME     = "name";
    public static final String PHONE    = "phone";
    public static final String DATA_ID       = "data_id";


    Context mContext;

    public MyContact(Context context){
        mContext = context;
    }

    public String getContactInJson(){
        JSONArray jsonArray = new JSONArray();
        Cursor phones = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String data_id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.Data._ID));
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put(NAME, name);
                jsonObject.put(PHONE, phoneNumber);
                jsonObject.put(DATA_ID, data_id);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                MyLog.log(e.toString());
            }

        }
        phones.close();
        return jsonArray.toString();
    }

    public boolean SynctoPhone(){
        return false;
    }

}
