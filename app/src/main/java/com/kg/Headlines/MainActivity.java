/**
 * By Gaurav Kumar
 * a news application providing top news headlines with functionality to get news by different category
 * also to search any news query
 * and save news so to read later
 * follow MVVM architecture
 */



 package com.kg.Headlines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kg.Headlines.NewsArticle.Article;
import com.kg.Headlines.NewsArticle.Headlines;
import com.kg.Headlines.room.SavedArticle;
import com.kg.Headlines.room.SavedArticleActivity;
import com.kg.Headlines.room.SavedViewModel;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity implements ScreenSlidePageFragmentB.FragmentBlistener, ScreenSlidePageFragment.FragmentAlistener {

    private RecyclerView recyclerView;
    Adapter adapter;
    LinearLayoutManager manager;
    private Button button;
    private EditText editQuery;
    private ArrayList<Article> articleArrayList=new ArrayList<>();
     private ViewPager2 viewPager;

     SavedViewModel viewModel;
     private FragmentStateAdapter pagerAdapter;
     private ScreenSlidePageFragment fragment;
     private ScreenSlidePageFragmentB fragmentB;

     NewsViewModel newsViewModel;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.pager);
        pagerAdapter=new ScreenSlidePagerActivity.ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        recyclerView=findViewById(R.id.recyclerView);
        editQuery=findViewById(R.id.edit_query);
        button=findViewById(R.id.button);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        viewModel=new ViewModelProvider(this).get(SavedViewModel.class);
        adapter=new Adapter(MainActivity.this, new Adapter.clickListener() {
            @Override
            public void onclick(Article article) {
                SavedArticle savedArticle=new SavedArticle(article.getSource().getName(),article.getTitle(),article.getPublishedAt(),article.getUrl(),article.getUrlToImage());
                viewModel.insert(savedArticle);
                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
            }
        });

       // recyclerView.setAdapter(adapter);
        newsViewModel= new ViewModelProvider(MainActivity.this).get(NewsViewModel.class);
       retrieveData("general");


       getSupportActionBar().setTitle("News Headlines");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               newsViewModel.getData2(editQuery.getText().toString().trim()).observe(MainActivity.this, new Observer<Headlines>() {
                   @Override
                   public void onChanged(Headlines headlines) {
                       List<Article> articles=headlines.getArticles();
                       articleArrayList.clear();
                       articleArrayList.addAll(articles);
                       recyclerView.setAdapter(adapter);
                       adapter.notifyDataSetChanged();
                       fragment=new ScreenSlidePageFragment();
                       editQuery.setText("");
                       editQuery.setHint("Search");
                     //  fragment.update();
                   }
               });
            }
        });






    }

     public void onInputA()
     {
         fragmentB=new ScreenSlidePageFragmentB();
         fragmentB.updateB();

     }

     public void onInputB()
     {
         fragment=new ScreenSlidePageFragment();
         fragment.updateA();
     }

     @Override
     public void intentClick() {
         Intent intent=new Intent(MainActivity.this,SavedArticleActivity.class);
         startActivity(intent);
     }


     public void retrieveData(String query)
    {





        newsViewModel.getData(query).observe(MainActivity.this, new Observer<Headlines>() {
            @Override
            public void onChanged(Headlines headlines) {

                List<Article> articles=headlines.getArticles();
                articleArrayList.clear();
                articleArrayList.addAll(articles);

                adapter.setArticles(articleArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });




    }


 }
