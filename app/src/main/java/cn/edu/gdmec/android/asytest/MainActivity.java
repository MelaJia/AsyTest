package cn.edu.gdmec.android.asytest;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity{
     Button btn,bt_next;
     TextView textView;
     Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        bt_next=findViewById(R.id.next);
        textView=findViewById(R.id.text);
        context=this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ReadURL("http://www.baidu.com");
            }
        });
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,HandlerActivity.class);
                startActivity(intent);

            }
        });
    }

    public void ReadURL(String url) {
        new AsyncTask<String, Void, String>(){

            @Override
            protected String doInBackground(String... arg0) {
                try {
                    URL url=new URL(arg0[0]);
                   URLConnection urlConnection=url.openConnection();
                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(isr);
                    String line="";
                    StringBuilder sb=new StringBuilder();
                    while ((line=br.readLine())!=null){
                        sb.append(line);
                    }
                    br.close();
                    is.close();
                    return sb.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                textView.setText(s);
                super.onPostExecute(s);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(String s) {
                super.onCancelled(s);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(url);

    }


}
