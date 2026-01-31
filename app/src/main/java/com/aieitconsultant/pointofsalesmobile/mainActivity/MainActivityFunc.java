package com.aieitconsultant.pointofsalesmobile.mainActivity;

import android.content.Context;

import com.aieitconsultant.pointofsalesmobile.facades.FuncFacades;
import com.aieitconsultant.pointofsalesmobile.global.url.URL;
import com.aieitconsultant.pointofsalesmobile.global.webviewclient.MyWebViewClient;

import java.io.FileNotFoundException;

public class MainActivityFunc implements FuncFacades {

    //Get status url override
    public MyWebViewClient shouldOverrideURL() {
        return FuncFacades.ownWebView;
    }

    //Get full address
    public String ownURL() {
        return FuncFacades.url.getFullAddress();
    }

    public void storeFile(Context context) {
        FuncFacades.storage.store(context);
    }

    public String getFile(Context context) throws FileNotFoundException {
        return FuncFacades.storage.get(context);
    }
}
