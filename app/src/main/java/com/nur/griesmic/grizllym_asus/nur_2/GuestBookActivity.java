package com.nur.griesmic.grizllym_asus.nur_2;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuestBookActivity extends AppCompatActivity {
    private Context cntx;
    private ListView lv;
    private LayoutInflater inf;

    private GuestBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cntx = this;
        inf = this.getLayoutInflater();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        lv = (ListView) findViewById(R.id.book_lv);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(cntx);
                LinearLayout ll = new LinearLayout(cntx);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                final EditText ed = new EditText(cntx);
                ll.addView(ed);
                final RatingBar rb = new RatingBar(cntx);
                rb.setNumStars(5);
                rb.setStepSize(1f);
                rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL));
                ll.addView(rb);
                builder.setView(ll);
                builder.setTitle("Review");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ed.getText().toString().trim() != "") {
                            Toast.makeText(cntx, "Review Added.", Toast.LENGTH_SHORT).show();
                            adapter.add(new Review(0, ed.getText().toString(), (int)rb.getRating(), new Date()));
                            adapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(cntx, "You could write at least something.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
            }
        });

        ArrayList<Review> list = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            list.add(new Review());

        adapter = new GuestBookAdapter(cntx, R.layout.guest_book_list_item, list);

        lv.setAdapter(adapter);
    }

    public class GuestBookAdapter extends ArrayAdapter<Review>{
        private Context cntx;
        public GuestBookAdapter(Context context, int resource, List<Review> objects) {
            super(context, resource, objects);
            this.cntx = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //LayoutInflater inf = (LayoutInflater)cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inf.inflate(R.layout.guest_book_list_item, null);

            TextView text = (TextView) v.findViewById(R.id.guest_book_item_text);
            TextView date = (TextView) v.findViewById(R.id.guest_book_item_date);
            RatingBar rb = (RatingBar) v.findViewById(R.id.guest_book_item_rating);

            text.setText(getItem(position).getText());
            date.setText(getItem(position).getDate());
            rb.setRating(getItem(position).getRating());

            return v;
        }
    }
}
