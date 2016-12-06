package com.nur.griesmic.grizllym_asus.nur_2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ScanActivity extends AppCompatActivity {
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        this.ctx = this;
        ImageButton imgBtn_scan = (ImageButton) findViewById(R.id.qr_scan);
        Button btn_cancel = (Button) findViewById(R.id.qr_scan_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, TitleActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        imgBtn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
