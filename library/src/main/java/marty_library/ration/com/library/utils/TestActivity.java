package marty_library.ration.com.library.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import marty_library.ration.com.library.R;

public class TestActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter adapter = new ArrayAdapter(){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(R.id.text);

//                textView.setText(getItem(position));

                return view;
            }
        };

        listView.setAdapter(adapter);
        adapter.add("");
        adapter.notifyDataSetChanged();


        RecyclerView.Adapter adapter1 = new RecyclerView.Adapter() {
            ArrayList<String > items = new ArrayList<>();
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                return new RecyclerView.ViewHolder(inflater.inflate(R.layout.dialog_loading,viewGroup,false)){

                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                viewHolder.itemView.setBackgroundColor(getColor(R.color.alpha_50_main_orange));
            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        };

        recyclerView.setAdapter(adapter1);
    }
}
