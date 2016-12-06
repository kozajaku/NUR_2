package com.nur.griesmic.grizllym_asus.nur_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TitleActivity extends AppCompatActivity {

    private Button btn_scan;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.context = this;
        btn_scan = (Button) findViewById(R.id.activity2_scan);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScanActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (id){
            case R.id.action_us:
                builder.setTitle("About Us");
                break;
            case R.id.action_app:
                builder.setTitle("About App");
                break;
            case R.id.action_faq:
                builder.setTitle("F.A.Q.");
                break;
            default:
                break;
        }
        builder.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In luctus sodales lacus sodales malesuada. Suspendisse eu purus eros. Donec sagittis blandit tortor, et interdum leo tempor in. Etiam sollicitudin purus et sem lobortis facilisis. Vivamus rhoncus risus a lectus rhoncus, quis molestie orci bibendum. Praesent a hendrerit ante, non consectetur est. Curabitur dapibus, urna nec mollis condimentum, eros sem vehicula justo, in consectetur lectus libero eu ligula. Sed at venenatis augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam id nunc dui. Suspendisse gravida semper fringilla. Donec eleifend commodo pretium. Integer lacus neque, lobortis vitae ultricies vel, sagittis id purus. Proin in elementum elit. Integer in imperdiet augue. Ut convallis sapien vitae ex tempor, ac dapibus sem molestie.");
        builder.create().show();

        return super.onOptionsItemSelected(item);
    }

}
