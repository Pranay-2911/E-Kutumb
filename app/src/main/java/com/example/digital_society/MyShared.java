package com.example.digital_society;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class MyShared
{
    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    public static final String KEY_ID = "id";
    public static final String KEY_ROLE = "role";

    public MyShared(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("digital_mypref",context.MODE_PRIVATE);
    }


    public void setPreference(String value,String role)
    {
        editor = sp.edit();
        editor.putString(KEY_ID,value);
        editor.putString(KEY_ROLE,role);
        Log.d("mydata","inside the prefernce file "+value);
        editor.commit();
    }


    public void Isloggin(Boolean status)
    {
        editor = sp.edit();
        editor.putBoolean("status",status);
        editor.commit();
    }


    public HashMap<Boolean,Boolean> checkLogin()
    {
        HashMap hm=new HashMap();
        Log.d("mydata","---> status "+sp.getBoolean("status",false));
        hm.put("status",sp.getBoolean("status",false));
        return  hm;

    }


    public String geLoggedId()
    {
        return  sp.getString(KEY_ID,null);
    }


    public HashMap<String , String> getdata()
    {
        HashMap hm = new HashMap();

        hm.put(KEY_ID,sp.getString(KEY_ID,"DEFAULT"));
        hm.put(KEY_ROLE,sp.getString(KEY_ROLE,"DEFAULT"));
        Log.d("mydata","ID ----> "+hm.get(KEY_ID));
        Log.d("mydata","KEYROLE --->"+hm.get(KEY_ROLE));
        return hm;
    }


    public void cleardata()
    {
        Log.d("mydata","==> clear");
        editor = sp.edit();
        editor.clear();
        editor.commit();
    }

}
