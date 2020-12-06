package it.multi_installer.webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private SwipeRefreshLayout refreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        refreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swiperefresh);

        final ProgressDialog progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setMessage("Caricamento...");

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.multi-installer.it/");

        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView.reload();
                    }
                }
        );

        webView.setWebChromeClient(new WebChromeClient());
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



        webView.setWebViewClient(new WebViewClient() {

            /*
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }*/

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("mailto:")) {
                    //Handle mail Urls
                    startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(url)));
                } else if (url.startsWith("tel:")) {
                    //Handle telephony Urls
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(url)));
                } else if (url.startsWith("tg:")) {
                    Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/Multi_Installer_Bot"));
                    startActivity(telegram);
                } else if (url.startsWith("https://www.youtube.com")) {
                    Intent youtube = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.youtube.com/c/FabriTutorial"));
                    startActivity(youtube);
                } else if (url.startsWith("https://www.linkedin.com")) {
                    //Intent din = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.linkedin.com/in/fabrizio-amorelli-ab0a2bb9/"));
                    //startActivity(din);

                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/fabrizio-amorelli-ab0a2bb9/"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.setPackage("com.android.chrome");
                    i.setPackage("com.linkedin.android");
                    try {
                        startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is probably not installed
                        // Try with the default browser
                        i.setPackage(null);
                        startActivity(i);
                    }

                } else if (url.startsWith("https://github.com")) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Fabrizio04"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.setPackage("com.android.chrome");
                    i.setPackage("com.github.android");
                    try {
                        startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is probably not installed
                        // Try with the default browser
                        i.setPackage(null);
                        startActivity(i);
                    }

                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                final Uri uri = request.getUrl();
                if (uri.toString().startsWith("mailto:")) {
                    //Handle mail Urls
                    startActivity(new Intent(Intent.ACTION_SENDTO, uri));
                } else if (uri.toString().startsWith("tel:")) {
                    //Handle telephony Urls
                    startActivity(new Intent(Intent.ACTION_DIAL, uri));
                } else if (uri.toString().startsWith("tg:")) {
                    Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/Multi_Installer_Bot"));
                    startActivity(telegram);
                } else if (uri.toString().startsWith("https://www.youtube.com")) {
                    Intent youtube = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.youtube.com/c/FabriTutorial"));
                    startActivity(youtube);
                } else if (uri.toString().startsWith("https://www.linkedin.com")) {
                    //Intent din = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.linkedin.com/in/fabrizio-amorelli-ab0a2bb9/"));
                    //startActivity(din);

                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/fabrizio-amorelli-ab0a2bb9/"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.setPackage("com.android.chrome");
                    i.setPackage("com.linkedin.android");
                    try {
                        startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is probably not installed
                        // Try with the default browser
                        i.setPackage(null);
                        startActivity(i);
                    }

                } else if (uri.toString().startsWith("https://github.com")) {

                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Fabrizio04"));
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //i.setPackage("com.android.chrome");
                    i.setPackage("com.github.android");
                    try {
                        startActivity(i);
                    } catch (ActivityNotFoundException e) {
                        // Chrome is probably not installed
                        // Try with the default browser
                        i.setPackage(null);
                        startActivity(i);
                    }

                } else {
                    //Handle Web Urls
                    view.loadUrl(uri.toString());
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!progressBar.isShowing()) {
                    progressBar.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
                refreshLayout.setRefreshing(false);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }

                if(view!=null){

                    String htmlData = "<html>" +
                            "<body>" +
                            "<div align=\"center\">" +
                            "Errore nel caricamento del contenuto:<br>" + failingUrl +
                            "</div>" +
                            "</body>" +
                            "</html>";

                    //String htmlData ="<html><body><div align=\"center\" >\"This is the description for the load fail : \"+description+\"\nThe failed url is : \"+failingUrl+"\n"</div></body>";
                    view.loadUrl("about:blank");
                    view.loadDataWithBaseURL(null,htmlData, "text/html", "UTF-8",null);
                    view.invalidate();

                }
            }

        });

    }//onCreate

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()) {
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }

}