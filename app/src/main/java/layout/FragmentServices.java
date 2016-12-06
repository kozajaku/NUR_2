package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nur.griesmic.grizllym_asus.nur_2.MainActivity;
import com.nur.griesmic.grizllym_asus.nur_2.R;

public class FragmentServices extends Fragment {
    private MainActivity mainActivity;

    public FragmentServices() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_services, container, false);
        Button btn_guestBook = (Button) v.findViewById(R.id.btn3);
        btn_guestBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onGuestBookClicked();
            }
        });

        ((Button) v.findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.makeToast("Service will arrive shortly.", Toast.LENGTH_SHORT);
            }
        });
        ((Button)v.findViewById(R.id.btn4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mainActivity.context, "Not implemented yet.", Toast.LENGTH_SHORT).show();
                mainActivity.onScanTableClicked();
            }
        });
        return v;
    }

    public void setParent(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
