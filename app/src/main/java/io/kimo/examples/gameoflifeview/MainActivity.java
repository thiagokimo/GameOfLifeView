package io.kimo.examples.gameoflifeview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    public static final int DEFAULT_GOL_VIEW_CODE = 0;
    public static final int GOL_VIEW_XML = 1;
    public static final int CUSTOM_COLORS = 2;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.configureToolbar(this, false);
        setTitle("Game of Life View Examples");
        configureRecyclerView();
    }

    private void configureRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SimpleRecyclerAdapter());
    }

    private class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

        public List<String> examplesOptions;

        public SimpleRecyclerAdapter() {
            configureOptions();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.item_example, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.text.setText(examplesOptions.get(i));
        }

        @Override
        public int getItemCount() {
            return examplesOptions == null ? 0 : examplesOptions.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

            private TextView text;

            public ViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (getPosition()) {
                    case DEFAULT_GOL_VIEW_CODE:
                        startActivity(new Intent(MainActivity.this, ThroughCodeActivity.class));
                        break;
                    case GOL_VIEW_XML:
                        startActivity(new Intent(MainActivity.this, ThroughXMLActivity.class));
                        break;
                    case CUSTOM_COLORS:
                        startActivity(new Intent(MainActivity.this, CustomParamsActivity.class));
                        break;
                }
            }
        }

        private void configureOptions() {
            examplesOptions = new ArrayList<>();

            examplesOptions.add("Default view");
            examplesOptions.add("Through XML");
            examplesOptions.add("With custom parameters");
        }
    }
}
