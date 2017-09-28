package com.example.steve.gotoread_ui_alpha20;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity extends AppCompatActivity {

    private List<Book> bookList = new ArrayList<Book>();
    private SwipeRefreshLayout swipeRefresh;
    VocabularyActivity.BookAdapter adapter = new VocabularyActivity.BookAdapter(bookList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volcabulary);


        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshBook();
            }
        });
    }





    private void refreshBook() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    protected void initData() {

        Book user1 = new Book("algebra", "n. 代数", "2017.1.21");
        bookList.add(user1);
        Book user2 = new Book("relational", "adj. 具有关联的", "2017.1.22");
        bookList.add(user2);
        Book user3 = new Book("terminology", "n. 术语", "2017.3.3");
        bookList.add(user3);
        Book user4 = new Book("property", "n. 特性", "2017.4.5");
        bookList.add(user4);
        Book user5 = new Book("entity", "n. 实体", "2017.8.8");
        bookList.add(user5);

    }

    class BookAdapter extends RecyclerView.Adapter<VocabularyActivity.BookAdapter.ViewHolder>  {
        private List<Book> mBookList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView author;
            TextView score;

            public ViewHolder(View view) {
                super(view);
                name = (TextView) view.findViewById(R.id.name);
                author = (TextView) view.findViewById(R.id.author);
                score = (TextView) view.findViewById(R.id.score);
            }
        }

        public BookAdapter(List<Book> bookList) {
            mBookList = bookList;

        }

        @Override
        public VocabularyActivity.BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(VocabularyActivity.BookAdapter.ViewHolder holder, int position) {
            Book book = mBookList.get(position);
            holder.name.setText(book.getName());
            holder.author.setText(book.getAuthor());
            holder.score.setText(book.getScore());
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }

    }
}
