package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.nur.griesmic.grizllym_asus.nur_2.*;

import java.util.ArrayList;

public class FragmentOrder extends Fragment {
    private LayoutInflater inf;
    private SavedTabsListAdapter mSavedTabsListAdapter;
    private ExpandableListView elv;
    private MainActivity mainActivity;
    private EditText search;
    private ImageButton img_btn;
    private LinearLayout search_bar;

    public void setParent(MainActivity parent){
        this.mainActivity = parent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inf = inflater;
        View v = inflater.inflate(R.layout.fragment_order, null);
        elv = (ExpandableListView) v.findViewById(R.id.expandableListView);
        search = (EditText) v.findViewById(R.id.search_text);
        search_bar = (LinearLayout) v.findViewById(R.id.search_bar);

        ((ImageButton) v.findViewById(R.id.search_bar_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.toggleSearchBar();
            }
        });

        //search_bar.setVisibility(View.GONE);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString().trim().toLowerCase();
                setFilter(keyword);
            }
        });
        img_btn = (ImageButton) v.findViewById(R.id.cancel_search);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
                mainActivity.setSearch("");
            }
        });
        mSavedTabsListAdapter = new SavedTabsListAdapter();
        mSavedTabsListAdapter.init();
        elv.setAdapter(mSavedTabsListAdapter);
        elv.setGroupIndicator(null);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void showSearchBar(boolean show){
        if(show)
            search_bar.setVisibility(View.VISIBLE);
        else
            search_bar.setVisibility(View.GONE);

    }
    
    public void setFilter(String keyword){
        mSavedTabsListAdapter.setFilter(keyword);
    }

    public class SavedTabsListAdapter extends BaseExpandableListAdapter {
        private String[] orgGroups = { "Polévky", "Překdrmy", "Bezmasá jídla", "Drůbež", "Steaky", "Přílohy", "Saláty", "Alkoholické nápoje", "Nealkoholické nápoje", "Teplé nápoje"};
        /*private Item[][] orgChildren = {
                { new Item("Boršť","3 dcl",0.9), new Item("Zelňačka","3 dcl",0.9), new Item("Kuřecí vývar","3 dcl",0.9), new Item("Kulajda","3 dcl",0.9), new Item("Česnečka","3 dcl",0.9) },
                { new Item("Kaviár","50 g",19.9), new Item("Křenové rolky","3 ks",4.9) },
                { new Item("Smažený sýr","200 g",1.9), new Item("Nakládaný Hermelín","80 g", 3.9) },
                { new Item("Kuřecí kapsa","250 g", 7.9), new Item("Kuřecí nudličky","150 g", 4.9) },
                { new Item("Hovězí steak","200 g", 7.9), new Item("Panenka","200 g", 7.9) },
                { new Item("Hranolky","100 g",1.9), new Item("Krokety","100 g",1.9), new Item("Brambory","100 g", 1.9), new Item("Rýže","100 g", 1.9) },
                { new Item("Salát Ceasar","150 g",3.9), new Item("Kuřecí Salát","150 g", 3.9), new Item("Těstovinový","200 g", 4.9) },
                { new Item("Pivo","0.5 l",1.9), new Item("Víno červené","2 dcl",1.9), new Item("Víno bílé","2 dcl",1.9), new Item("Captain Morgen","2 cl", 2.9) },
                { new Item("Voda perlivá","0.33 l", 1.9), new Item("Voda neperlivá","0.33 l", 1.9), new Item("Džus Jablko","0.33 l", 1.9), new Item("Džus Pomeranč","0.33 l", 1.9), new Item("Džus Ananas","0.33 l", 1.9), new Item("Džus Multivitamín","0.33 l", 1.9) },
                { new Item("Latte","0.2 l", 2.9), new Item("Machiato","0.2 l", 2.9), new Item("Americano","0.2 l", 2.9), new Item("Espresso","0.2 l", 2.9), new Item("Capucchino","0.2 l", 2.9), new Item("Kakao","0.2 l", 2.9), new Item("Čaj černý","0.2 l", 2.9), new Item("Čaj zelený","0.2 l", 2.9), new Item("Čaj ovocný","0.2 l", 2.9)}
        };*/
        
        private ArrayList<Item[]> orgChildrenList;
        private String[] groups = orgGroups.clone();
        private ArrayList<Item[]> children;

        public void init(){
            orgChildrenList = new ArrayList<Item[]>();
            orgChildrenList.add(new Item[]{ new Item("Boršť","3 dcl",0.9), new Item("Zelňačka","3 dcl",0.9), new Item("Kuřecí vývar","3 dcl",0.9), new Item("Kulajda","3 dcl",0.9), new Item("Česnečka","3 dcl",0.9) });
            orgChildrenList.add(new Item[]{ new Item("Kaviár","50 g",19.9), new Item("Křenové rolky","3 ks",4.9) });
            orgChildrenList.add(new Item[]{ new Item("Smažený sýr","200 g",1.9), new Item("Nakládaný Hermelín","80 g", 3.9) });
            orgChildrenList.add(new Item[]{ new Item("Kuřecí kapsa","250 g", 7.9), new Item("Kuřecí nudličky","150 g", 4.9) });
            orgChildrenList.add(new Item[]{ new Item("Hovězí steak","200 g", 7.9), new Item("Panenka","200 g", 7.9) });
            orgChildrenList.add(new Item[]{ new Item("Hranolky","100 g",1.9), new Item("Krokety","100 g",1.9), new Item("Brambory","100 g", 1.9), new Item("Rýže","100 g", 1.9) });
            orgChildrenList.add(new Item[]{ new Item("Salát Ceasar","150 g",3.9), new Item("Kuřecí Salát","150 g", 3.9), new Item("Těstovinový","200 g", 4.9) });
            orgChildrenList.add(new Item[]{ new Item("Pivo","0.5 l",1.9), new Item("Víno červené","2 dcl",1.9), new Item("Víno bílé","2 dcl",1.9), new Item("Captain Morgen","2 cl", 2.9) });
            orgChildrenList.add(new Item[]{ new Item("Voda perlivá","0.33 l", 1.9), new Item("Voda neperlivá","0.33 l", 1.9), new Item("Džus Jablko","0.33 l", 1.9), new Item("Džus Pomeranč","0.33 l", 1.9), new Item("Džus Ananas","0.33 l", 1.9), new Item("Džus Multivitamín","0.33 l", 1.9) });
            orgChildrenList.add(new Item[]{ new Item("Latte","0.2 l", 2.9), new Item("Machiato","0.2 l", 2.9), new Item("Americano","0.2 l", 2.9), new Item("Espresso","0.2 l", 2.9), new Item("Capucchino","0.2 l", 2.9), new Item("Kakao","0.2 l", 2.9), new Item("Čaj černý","0.2 l", 2.9), new Item("Čaj zelený","0.2 l", 2.9), new Item("Čaj ovocný","0.2 l", 2.9)});

            children = new ArrayList<>(orgChildrenList);
            groups = orgGroups.clone();
        }
        
        public void setFilter(String keyword){
            children.clear();
            ArrayList<String> groupsTmp = new ArrayList<>();
            int c = 0;
            
            for(Item[] items : orgChildrenList){
                ArrayList<Item> tmp = new ArrayList<Item>();
                for(Item item : items)
                    if(item.name.toLowerCase().contains(keyword))
                        tmp.add(item);
                if(tmp.size() > 0) {
                    children.add(tmp.toArray(new Item[0]));
                    groupsTmp.add(orgGroups[c]);
                }
                c++;
            }


            if(keyword == ""){
                for(int i = 0; i < groupsTmp.size(); i++)
                    elv.collapseGroup(i);
            } else {
                for(int i = 0; i < groupsTmp.size(); i++)
                    elv.expandGroup(i);
            }

            groups = groupsTmp.toArray(new String[0]);
            notifyDataSetChanged();
        }

        @Override
        public void onGroupExpanded(int groupPosition) {
            if(search.toString().trim() == "") {
                for (int c = 0; c < groups.length; c++) {
                    if (c == groupPosition)
                        elv.expandGroup(c);
                    else
                        elv.collapseGroup(c);
                }
            }
            else {
                super.onGroupExpanded(groupPosition);
            }
        }

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return children.get(i).length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return children.get(i)[i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(FragmentOrder.this.getActivity());
            textView.setTextSize(3,11);
            textView.setText(getGroup(i).toString());
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            View v = inf.inflate(R.layout.list_item, null);
            final Item item = children.get(i)[i1];

            ((TextView)v.findViewById(R.id.listItemName)).setText(item.name);
            ((TextView)v.findViewById(R.id.listItemNote)).setText(item.note);
            ((TextView)v.findViewById(R.id.listItemPrice)).setText(item.getPrice());


            ((ImageButton)v.findViewById(R.id.listItemAdd)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.onItemAdd(new Item(item.name, item.note, item.price));
                }
            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.onItemSelected(item.name, item.detail);
                }
            });

            return v;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
