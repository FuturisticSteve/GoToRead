package com.example.steve.gotoread_ui_alpha20;

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

public class ScrollingActivity extends AppCompatActivity {
    private List<BookComment> commentList = new ArrayList<BookComment>();
    private SwipeRefreshLayout swipeRefresh;
    ScrollingActivity.CommentAdapter adapter = new ScrollingActivity.CommentAdapter(commentList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
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
                refreshComment();
            }
        });

    }

    private void refreshComment() {
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

        BookComment user1 = new BookComment("云淡风轻", "不同的心境自然有不同的解读。于我，更加确定了一件事，如果内心一直告诉自己不安于现在的状态，一直渴求着另一种生活，那么那就是一种征兆，尽管要克服习惯了的安逸，面对未知改变的恐慌，以及追梦过程中的艰难困苦。", 1);
        commentList.add(user1);
        BookComment user2 = new BookComment("我本善良", "这是我最喜欢的一本书， 命中注定是他教会我最大的道理， 我们必须去接受任何事实， 这是不能逃避的， 然而需要考验我们的就是该怎么去解决问题。", 2);
        commentList.add(user2);
        BookComment user3 = new BookComment("转念、成空」", "终于看完咯，很喜欢的一本书，圣地亚哥的坚持不懈，让人佩服不已，值得我们去学习，有梦就大胆去追，因为你的努力，你的坚持，你所付出的一切，总会让你走上成功的道路！", 3);
        commentList.add(user3);
        BookComment user4 = new BookComment("叫我学习甜", "起了很久之前看过的一本书《秘密》，当你想完成一件事的时候，你就会动用宇宙的力量，合力来帮你。还有，永远不要忽视最初的梦想~", 3);
        commentList.add(user4);
        BookComment user5 = new BookComment("Nick s", "以寓言的方式，将追梦的故事缓缓道出，充满正能量。虽然带有些玄幻的色彩，但我也相信那些生活的预兆，他们告诉你应该怎么做。缺点是感觉小说的变化过快，内在的逻辑不够强，更像作者内心自在的独白。", 3);
        commentList.add(user5);
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

    }

    static class CommentAdapter extends RecyclerView.Adapter<ScrollingActivity.CommentAdapter.ViewHolder> {
        private List<BookComment> mCommentList;

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView userid;
            TextView comment;

            public ViewHolder(View view) {
                super(view);
                userid = (TextView) view.findViewById(R.id.userid);
                comment = (TextView) view.findViewById(R.id.comment);
            }
        }

        public CommentAdapter(List<BookComment> commentList) {
            mCommentList = commentList;

        }

        @Override
        public ScrollingActivity.CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.comment_item, parent, false);
            ScrollingActivity.CommentAdapter.ViewHolder holder = new ScrollingActivity.CommentAdapter.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ScrollingActivity.CommentAdapter.ViewHolder holder, int position) {
            BookComment bookComment = mCommentList.get(position);
            holder.userid.setText(bookComment.getUserid());
            holder.comment.setText(bookComment.getComment());
        }

        @Override
        public int getItemCount() {
            return mCommentList.size();
        }

    }
}
