package com.example.steve.gotoread_ui_alpha20;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChooseBookActivity extends AppCompatActivity {

    private List<Book> bookList = new ArrayList<Book>();
    private SwipeRefreshLayout swipeRefresh;
    ChooseBookActivity.BookAdapter adapter = new ChooseBookActivity.BookAdapter(bookList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


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

        Book user1 = new Book("失落的秘符", "丹.布朗", "4.7");
        bookList.add(user1);
        Book user2 = new Book("牧羊少年奇幻之旅", "保罗.柯爱略", "4.8");
        bookList.add(user2);
        Book user3 = new Book("三国演义", "罗贯中", "4.9");
        bookList.add(user3);
        Book user4 = new Book("人类简史", "尤瓦尔·赫拉利", "4.6");
        bookList.add(user4);
        Book user5 = new Book("三个火枪手", "大仲马", "4.9");
        bookList.add(user5);
        /*
        BookComment user6 = new BookComment("夜微凉", "好神奇的一个故事一场冒险 当我们定下一个目标全世界都会以不同形式帮你完成 上帝也会让你经历种种磨难之后将宝藏呈现 很好的一本书", 3);
        commentList.add(user6);
        BookComment user7 = new BookComment("菊", "没有能够深刻领会本书的含义吧，其中阐述的思想有些难以接受，似是宗教含义太重？但是其中有些话还是很经典的", 3);
        commentList.add(user7);
        BookComment user8 = new BookComment("一片冰心", "很受启发。虽然这是一本鼓励人去追随梦想的书，但是读的时候内心却无比平静，就好像你在田野间散步，四周是怡人的风景。追随天空的道路可能也是如此，你的身体经历了沧桑，但是心却如同漫步旷野，悠哉悠哉吧。", 3);
        commentList.add(user8);
        BookComment user9 = new BookComment("sweeeeetyyyyyyyy", "天命一直在那里，尽管可能需要兜兜转转一大圈，甚至是回到原地。所以要相信得到的东西本身就应该属于你，又或许你所不满的生活状态正是别人梦寐以求的一种存在。可能需要外界的点醒，但一切探索和追寻需要自己。", 3);
        commentList.add(user9);
        BookComment user10 = new BookComment("最最最最最夏天", "拖好久然后一口气看完， 生活，世界之魂，爱情，命运…人生之书难免逃不掉鸡汤的嫌疑，但有感触，还是本好书， 最后一句，苏爆了～ “我来了，法蒂玛。”", 3);
        commentList.add(user10);
        */

    }

    class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>  {
        private List<Book> mBookList;

         class ViewHolder extends RecyclerView.ViewHolder {
            View bookView;
            TextView name;
            TextView author;
            TextView score;

            public ViewHolder(View view) {
                super(view);
                bookView = view;
                name = (TextView) view.findViewById(R.id.name);
                author = (TextView) view.findViewById(R.id.author);
                score = (TextView) view.findViewById(R.id.score);
            }
        }

        public BookAdapter(List<Book> bookList) {
            mBookList = bookList;

        }

        @Override
        public ChooseBookActivity.BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.book_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            holder.bookView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = holder.getAdapterPosition();
                    Book book = mBookList.get(position);
                    Intent intent = new Intent(ChooseBookActivity.this, ScrollingActivity.class);
                    startActivity(intent);
                }
            });


            return holder;
        }

        @Override
        public void onBindViewHolder(ChooseBookActivity.BookAdapter.ViewHolder holder, int position) {
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
