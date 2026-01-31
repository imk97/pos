package com.aieitconsultant.pointofsalesmobile.facades;

import com.aieitconsultant.pointofsalesmobile.global.inappstorage.InAppStorage;
import com.aieitconsultant.pointofsalesmobile.global.url.URL;
import com.aieitconsultant.pointofsalesmobile.global.webviewclient.MyWebViewClient;

public interface FuncFacades {
    public URL url = new URL();

    public MyWebViewClient ownWebView = new MyWebViewClient();

    public InAppStorage storage = new InAppStorage();
}
