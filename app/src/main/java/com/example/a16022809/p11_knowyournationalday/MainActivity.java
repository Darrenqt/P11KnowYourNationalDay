package com.example.a16022809.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);
        al = new ArrayList<String>();
        al.add("Singapore National Day is on 9 Aug");
        al.add("Singapore  is 53 years old");
        al.add("Theme is #OneNation Together");

        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void onResume() {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout passPhrase = (LinearLayout) inflater.inflate(R.layout.accesscode, null);
        final EditText etAccesscode = (EditText) passPhrase.findViewById(R.id.editTextAccessCode);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please Login")
                .setView(passPhrase)
                .setNegativeButton("No Access Code", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(etAccesscode.getText().toString().equals("738964")){

                        } else {
                            finish();
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemQuit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quit?")

                    .setPositiveButton("QUIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if (item.getItemId() == R.id.itemSend) {

            String [] list = new String [] {"Email", "SMS"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")

                    .setItems(list, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0 ){
                                Snackbar sb = Snackbar.make(getWindow().getDecorView().getRootView(), "Send using email?", Snackbar.LENGTH_SHORT);

                                sb.setAction("Send Email!", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent email = new Intent(Intent.ACTION_SEND);
                                        // Put essentials like email address, subject & body text
                                        email.putExtra(Intent.EXTRA_EMAIL,
                                                new String[]{"jason_lim@rp.edu.sg"});
                                        email.putExtra(Intent.EXTRA_SUBJECT,
                                                "Test Email from C347");
                                        email.putExtra(Intent.EXTRA_TEXT,
                                                "");
                                        // This MIME type indicates email
                                        email.setType("message/rfc822");
                                        // createChooser shows user a list of app that can handle
                                        // this MIME type, which is, email
                                        startActivity(Intent.createChooser(email,
                                                "Choose an Email client :"));
                                    }
                                });

                                sb.show();

                            } else {
                                Snackbar sb = Snackbar.make(getWindow().getDecorView().getRootView(), "Send using sms?", Snackbar.LENGTH_SHORT);

                                sb.setAction("Send SMS!", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this, "SMS Sent?", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                sb.show();
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
//        else if (item.getItemId() == R.id.itemQuiz) {
//            LayoutInflater inflater = (LayoutInflater)
//                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            LinearLayout passPhrase = (LinearLayout) inflater.inflate(R.layout.passphrase, null);
//            final EditText etPassphrease = (EditText) passPhrase.findViewById(R.id.editTextPassPhrase);
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Please Enter")
//                    .setView(passPhrase)
//                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(MainActivity.this, "You had entered " + etPassphrease.getText().toString(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        }
        return super.onOptionsItemSelected(item);
    }

}
