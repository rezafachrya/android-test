package com.example.bnilist.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.bnilist.BuildConfig;
import com.example.bnilist.R;
import com.example.bnilist.downloader.FileDownloader;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    private static final int WRITE_PERMISSION = 1001;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.btnTerbengkalai)
    MaterialButton btnTerbengkalai;
    @BindView(R.id.btnBermasalah)
    MaterialButton btnBermasalah;

    String url = "http://192.168.43.144:8080/ams/files/doc/AsetTerbengkalai.pdf";
    String keyword = "AsetTerbengkalai.pdf";

    String urlFileBermasalah = "http://192.168.43.144:8080/ams/files/doc/AsetBermasalah.pdf";
    String fileNameBermasalah = "AsetBermasalah.pdf";

    private static final String TAG = "InfoActivity";
//    private static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

//    private static boolean hasPermissions(Context context, String... permissions) {
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
////            for (String permission : permissions) {
////                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
////                    return false;
////                }
////            }
////        }
////        return true;
////    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        initComponent();

        Log.v(TAG, "onCreate() Method invoked ");

//        ActivityCompat.requestPermissions(InfoActivity.this, PERMISSIONS, 112);
    }

    protected void initComponent() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("BNI PFA");
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });

        btnTerbengkalai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(InfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        String fileName = keyword;
                        downloadFile(fileName, url);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
                    }
                } else {
                    String fileName = keyword;
                    downloadFile(fileName, url);
                }
            }
        });

        btnBermasalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(InfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        String fileName = fileNameBermasalah;
                        downloadFile(fileName, urlFileBermasalah);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
                    }
                } else {
                    String fileName = fileNameBermasalah;
                    downloadFile(fileName, urlFileBermasalah);
                }
            }
        });
    }

    private void downloadFile(String fileName, String url) {
        Uri downloadURI = Uri.parse(url);
        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        try {
            if (manager != null) {
                DownloadManager.Request request = new DownloadManager.Request(downloadURI);
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                        .setTitle(fileName)
                        .setDescription("Downloading " + fileName)
                        .setAllowedOverMetered(true)
                        .setAllowedOverRoaming(true)
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                        .setMimeType(getMimeType(downloadURI));
                manager.enqueue(request);
                Toast.makeText(this, "Download Started", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, downloadURI);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            Log.e("ERROR:MAIN", "E: " + e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                String fileName = keyword;
                downloadFile(fileName, url);
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getMimeType(Uri uri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(uri));
    }

//    public void download(View view) {
//        Log.v(TAG, "download() Method invoked ");
//
//        if (!hasPermissions(InfoActivity.this, PERMISSIONS)) {
//
//            Log.v(TAG, "download() Method DON'T HAVE PERMISSIONS ");
//
//            Toast t = Toast.makeText(getApplicationContext(), "You don't have write access !", Toast.LENGTH_LONG);
//            t.show();
//
//        } else {
//            Log.v(TAG, "download() Method HAVE PERMISSIONS ");
//
////            new DownloadFile().execute("http://maven.apache.org/maven-1.x/maven.pdf", "maven.pdf");
//            new DownloadFile().execute("http://www.axmag.com/download/pdfurl-guide.pdf", "mantap.pdf");
//
//        }
//
//        Log.v(TAG, "download() Method completed ");
//    }

//    public void view(View view) {
//        Log.v(TAG, "view() Method invoke ");
//
//        if (!hasPermissions(InfoActivity.this, PERMISSIONS)) {
//            Log.v(TAG, "download() Method DON`T HAVE PERMISSIONS ");
//
//            Toast t = Toast.makeText(getApplicationContext(), "You don`t have read access !", Toast.LENGTH_LONG);
//            t.show();
//        } else {
//            File d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//            File pdfFile = new File(d, "mantap.pdf");
//
//            Log.v(TAG, "view() Method pdfFile " + pdfFile.getAbsolutePath());
//
//            Uri path = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", pdfFile);
//
//
//            Log.v(TAG, "view() Method path " + path);
//
//            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
//            pdfIntent.setDataAndType(path, "application/pdf");
//            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//            try {
//                startActivity(pdfIntent);
//            } catch (ActivityNotFoundException e) {
//                Toast.makeText(InfoActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
//            }
//        }
//        Log.v(TAG, "view() Method completed ");
//    }
//
//    public void request(View view) {
//        ActivityCompat.requestPermissions(InfoActivity.this, PERMISSIONS, 112);
//    }
//
//    private class DownloadFile extends AsyncTask<String, Void, Void> {
//
//        @Override
//        protected Void doInBackground(String... strings) {
//            Log.v(TAG, "doInBackground() Method invoked ");
//
//            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
//            String fileName = strings[1];  // -> maven.pdf
//            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
////            File folder = mContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//
//
//            File pdfFile = new File(folder, fileName);
//            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsolutePath());
//            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsoluteFile());
//
//            try {
//                pdfFile.createNewFile();
//                Log.v(TAG, "doInBackground() file created" + pdfFile);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.e(TAG, "doInBackground() error" + e.getMessage());
//                Log.e(TAG, "doInBackground() error" + e.getStackTrace());
//
//
//            }
//            FileDownloader.downloadFile(fileUrl, pdfFile);
//            Log.v(TAG, "doInBackground() file download completed");
//
//            return null;
//        }
//    }
}
