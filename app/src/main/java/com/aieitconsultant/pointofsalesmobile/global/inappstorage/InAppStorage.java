package com.aieitconsultant.pointofsalesmobile.global.inappstorage;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InAppStorage {

    private String filename;

    public InAppStorage() {
        this.filename = "credentials";
    }

    public void store(Context context) {
//        String filename = this.filename;
        Log.d("File Contents", "store: " + this.filename);
        String fileContents = "ugsdasgFUhgfUGhgyfSDgfxgfDgfgffhFhXGDdgfdJkhfcghxfjHGGFXDXfsdgFH"; //hash for credentials
        try (FileOutputStream fos = context.openFileOutput(this.filename, Context.MODE_PRIVATE)) {
//            fos.write(fileContents.toByteArray());
            fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
            Log.d("File Contents", "Data berjaya disimpan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(Context context) throws FileNotFoundException {
//        Boolean check = Boolean.FALSE;
        String contents = null;
        File file = new File(context.getFilesDir(), "tmpFile");
        if (!file.exists()) {
            Log.d("File Contents", "File is not exist");
//            check = Boolean.FALSE;
        }
        FileInputStream fis = context.openFileInput(this.filename);
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            Log.d("File Contents", stringBuilder.toString());
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
            Log.d("File Contents", e.getMessage().toString());
//            check = Boolean.FALSE;
        } finally {
            contents = stringBuilder.toString();
//            check = Boolean.TRUE;
        }
        return contents;
    }
}
