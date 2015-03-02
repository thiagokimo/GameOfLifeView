package io.kimo.examples.gameoflifeview.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.kimo.examples.gameoflifeview.BaseActivity;
import io.kimo.examples.gameoflifeview.R;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    public Fragment getMainFragment() {
        return null;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public View getLayoutView() {
        return null;
    }

    @Override
    public String getActivityTitle() {
        return "Game of Life View Examples";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureRecyclerView();
    }

    private void configureRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SimpleRecyclerAdapter());
    }

    private class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

        public List<ExampleOption> examplesOptions;

        public SimpleRecyclerAdapter() {
            configureOptions();
        }

        @Override
        public int getItemViewType(int position) {
            return examplesOptions.get(position).getType();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view;
            int viewType = getItemViewType(i);

            if(viewType == ExampleOption.TYPE_ITEM) {
                view = layoutInflater.inflate(R.layout.item_example, viewGroup, false);
            } else {
                view = layoutInflater.inflate(R.layout.item_subheader, viewGroup, false);
            }

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.text.setText(examplesOptions.get(i).getTitle());
        }

        @Override
        public int getItemCount() {
            return examplesOptions == null ? 0 : examplesOptions.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView text;

            public ViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }

        private void configureOptions() {
            examplesOptions = new ArrayList<>();

            examplesOptions.add(new ExampleOption(ExampleOption.TYPE_SEPARATOR, "Through Code"));
            examplesOptions.add(new ExampleOption(ExampleOption.TYPE_ITEM, "Default Game of Life View"));
            examplesOptions.add(new ExampleOption(ExampleOption.TYPE_ITEM, "Custom colors"));

            examplesOptions.add(new ExampleOption(ExampleOption.TYPE_SEPARATOR, "Through XML"));
            examplesOptions.add(new ExampleOption(ExampleOption.TYPE_ITEM, "Default Game of Life View"));
            examplesOptions.add(new ExampleOption(ExampleOption.TYPE_ITEM, "Custom colors"));
        }
    }

    public class ExampleOption {

        public static final int TYPE_SEPARATOR = 0;
        public static final int TYPE_ITEM = 1;

        private int type;
        private String title;

        public ExampleOption(int type, String title) {
            this.type = type;
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }
    }
}
