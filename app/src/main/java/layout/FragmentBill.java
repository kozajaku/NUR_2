package layout;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import com.nur.griesmic.grizllym_asus.nur_2.*;

public class FragmentBill extends Fragment {
    private LayoutInflater inf;
    private MainActivity mainActivity;
    private BillListAdapter bla;
    private Button btnOrder;
    private Button btnComplete;
    private Button btnAddNote;
    private TextView totalPrice;
    private TextView newPrice;

    public void setParent(MainActivity parent){
        this.mainActivity = parent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inf = inflater;
        View v = inflater.inflate(R.layout.fragment_bill, container, false);
        ListView lv = (ListView) v.findViewById(R.id.listBill);

        btnOrder = ((Button)v.findViewById(R.id.btnOrder));
        btnComplete = ((Button)v.findViewById(R.id.btnComplete));
        newPrice = ((TextView)v.findViewById(R.id.newPrice));
        totalPrice = ((TextView)v.findViewById(R.id.totalPrice));
        btnAddNote = ((Button)v.findViewById(R.id.btnAddNote));


        bla = new BillListAdapter(getActivity(), mainActivity.getBill().list);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.order();
            }
        });
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onAddNoteClicked();
            }
        });
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onComplete();
            }
        });
        lv.setAdapter(bla);
        refresh();
        return v;
    }

    public void refresh(){
        mainActivity.setNew(mainActivity.bill.isNew());
        this.bla.notifyDataSetChanged();
        this.newPrice.setText(mainActivity.bill.getNewPrice());
        this.totalPrice.setText(mainActivity.bill.getOldPrice());
        this.btnOrder.setEnabled(mainActivity.bill.isNew());
        this.btnComplete.setEnabled(mainActivity.bill.isOld());
    }

    public class BillListAdapter extends ArrayAdapter<ItemInBill> {
        private Context cntx;
        public BillListAdapter(Context context, ArrayList<ItemInBill> objects){
            super(context,0,objects);
            this.cntx = context;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            View v = inf.inflate(R.layout.list_bill_item, null);

            final ItemInBill item = getItem(position);
            ImageView iv = ((ImageView)v.findViewById(R.id.billItem_statePic));
            if(item.state ==false)
                iv.setImageResource(android.support.design.R.drawable.abc_btn_check_to_on_mtrl_000);
            else
                iv.setImageResource(android.support.design.R.drawable.abc_btn_check_to_on_mtrl_015);

            ((TextView)v.findViewById(R.id.billItem_name)).setText(item.item.name);
            ((TextView)v.findViewById(R.id.billItem_totalPrice)).setText(item.getTotalPrice());

            ImageButton btnMore = (ImageButton) v.findViewById(R.id.billItem_more);
            if(item.state) {
                btnMore.setVisibility(View.INVISIBLE);
                btnMore.setEnabled(false);
            }
            else {
                btnMore.setEnabled(true);

                btnMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(cntx);
                        builder.setTitle("More Option");
                        builder.setItems(new String[]{"Increase Amount", "Reduce Amount", "Remove"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mainActivity.onItemModify(position, 1); break;
                                    case 1:
                                        mainActivity.onItemModify(position, -1); break;
                                    case 2:
                                        mainActivity.onItemRemove(position); break;
                                }
                            }
                        });
                        builder.create().show();
                    }
                });
            }

            return v;
        }
    }
}
