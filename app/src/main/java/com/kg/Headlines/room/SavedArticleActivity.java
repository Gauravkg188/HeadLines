package com.kg.Headlines.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.kg.Headlines.R;

import java.util.List;

public class SavedArticleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SavedAdapter adapter;
     SavedViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_article);
        recyclerView=findViewById(R.id.recyclerSaved);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter=new SavedAdapter(SavedArticleActivity.this);
        recyclerView.setAdapter(adapter);
        viewModel=new ViewModelProvider(this).get(SavedViewModel.class);

        viewModel.getArticles().observe(SavedArticleActivity.this, new Observer<List<SavedArticle>>() {
            @Override
            public void onChanged(List<SavedArticle> savedArticles) {
                adapter.setArticles(savedArticles);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                final SavedArticle article = adapter.getArticle(viewHolder.getAdapterPosition());
                if (direction == ItemTouchHelper.LEFT) {

                    viewModel.delete(article);
                    Toast.makeText(SavedArticleActivity.this, "article Deleted", Toast.LENGTH_SHORT).show();
                    Snackbar.make(recyclerView, null, Snackbar.LENGTH_LONG)
                            .setAction("Undo delete", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    viewModel.insert(article);
                                    Toast.makeText(SavedArticleActivity.this, "undo successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                }

            }
        }).attachToRecyclerView(recyclerView);

    }
}