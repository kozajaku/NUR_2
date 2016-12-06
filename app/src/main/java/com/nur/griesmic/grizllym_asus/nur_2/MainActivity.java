package com.nur.griesmic.grizllym_asus.nur_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import layout.FragmentBill;
import layout.FragmentOrder;
import layout.FragmentServices;

public class MainActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private boolean isSearchBarVisible = false;


    public Bill bill;
    public Context context;

    public Bill getBill(){ return this.bill; }

    private FragmentBill fragmentBill;
    private FragmentOrder fragmentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context = this;

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        bill = new Bill();

        onScanTableClicked();
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
        builder.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In luctus sodales lacus sodales malesuada. Suspendisse eu purus eros. Donec sagittis blandit tortor, et interdum leo tempor in. Etiam sollicitudin purus et sem lobortis facilisis. Vivamus rhoncus risus a lectus rhoncus, quis molestie orci bibendum. Praesent a hendrerit ante, non consectetur est. Curabitur dapibus, urna nec mollis condimentum, eros sem vehicula justo, in consectetur lectus libero eu ligula. Sed at venenatis augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nullam id nunc dui. Suspendisse gravida semper fringilla. Donec eleifend commodo pretium. Integer lacus neque, lobortis vitae ultricies vel, sagittis id purus. Proin in elementum elit. Integer in imperdiet augue. Ut convallis sapien vitae ex tempor, ac dapibus sem molestie.");
        switch (id){
            /*case R.id.action_search:
                toggleSearchBar();
                break;*/
            case R.id.action_us:
                builder.setTitle("About Us");
                builder.create().show();
                break;
            case R.id.action_app:
                builder.setTitle("About App");
                builder.create().show();
                break;
            case R.id.action_faq:
                builder.setTitle("F.A.Q.");
                builder.create().show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleSearchBar() {
        isSearchBarVisible = !isSearchBarVisible;
        fragmentOrder.showSearchBar(isSearchBarVisible);
    }

    public void onItemSelected(String title, String detail){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);

        ImageView iv = new ImageView(context);
        iv.setLayoutParams(new LinearLayout.LayoutParams(582, 441));
        iv.setPadding(0,15,0,0);
        iv.setImageResource(R.drawable.food_01);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);

        TextView tv = new TextView(context);
        tv.setText(detail);
        tv.setPadding(20,10,20,20);

        ll.addView(iv);
        ll.addView(tv);
        builder.setView(ll);
        builder.create().show();
    }
    public void onItemAdd(Item item){
        this.bill.add(item);
        fragmentBill.refresh();
        Toast.makeText(getApplicationContext(), "Item " + item.name + " added.", Toast.LENGTH_SHORT).show();
    }

    public void onItemModify(int position, int amount){
        this.bill.modify(position, amount);
        fragmentBill.refresh();
    }

    public void onItemRemove(int position){
        this.bill.remove(position);
        fragmentBill.refresh();
    }

    public void order() {
        if(!bill.isNew()) {
            Toast.makeText(this, "No items to Order!", Toast.LENGTH_LONG).show();
            return;
        }
        this.bill.makeOrder();
        fragmentBill.refresh();
        Toast.makeText(getApplicationContext(), "Order made. Total Price: " + this.bill.getTotalPrice(), Toast.LENGTH_SHORT).show();
    }

    public void onGuestBookClicked(){
        Intent intent = new Intent(this, GuestBookActivity.class);
        startActivity(intent);
    }

    public void onScanTableClicked(){
        Intent intent = new Intent(this, TitleActivity.class);
        startActivity(intent);
    }

    public void setNew(boolean aNew) {
        mSectionsPagerAdapter.setIsNew((aNew));
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    public void onAddNoteClicked() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Note");
        EditText et = new EditText(context);
        builder.setView(et);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Note will be send next time You order.", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    public void onComplete() {
        if(!bill.isOld()){
            Toast.makeText(this, "No ordered items to pay.", Toast.LENGTH_LONG).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Method of Paying:");
        builder.setItems(new String[]{"Cash", "Card"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    default:
                        Toast.makeText(context, "Service will arrive shortly.", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    public void setSearch(String keyword) {
        fragmentOrder.setFilter(keyword);
    }

    public void makeToast(String text, int length) {
        Toast.makeText(context, text, length).show();
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        private boolean isNew = false;

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if(fragmentOrder == null) {
                        fragmentOrder = new FragmentOrder();
                        fragmentOrder.setParent(MainActivity.this);
                    }
                    return fragmentOrder;
                case 1:
                    if(fragmentBill == null) {
                        fragmentBill = new FragmentBill();
                        fragmentBill.setParent(MainActivity.this);
                    }
                    return fragmentBill;
                case 2:
                    FragmentServices fs = new FragmentServices();
                    fs.setParent(MainActivity.this);
                    return fs;
            }
            return null;
        }

        @Override
        public int getCount() { return 3; }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Order";
                case 1:
                    return (isNew)? "-> Bill <-" : "Bill";
                case 2:
                    return "Services";
            }
            return null;
        }

        public void setIsNew(boolean isNew){
            this.isNew = isNew;
        }
    }
}
