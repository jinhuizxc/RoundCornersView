package com.jh.roundcornersview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jh.cornerslibrary.RoundCornersTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 利用 Glide 实现图片的圆角效果
 * https://github.com/xiaweizi/RoundCornersView
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<CornerBean> mList;
    private MyAdapter mAdapter;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<>();
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        imageView = (ImageView) findViewById(R.id.image);
        mAdapter = new MyAdapter();
        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        // 测试imageView
        test();


    }

    private void test() {
        RoundCornersTransformation transformation =
                new RoundCornersTransformation(MainActivity.this,
                        dp2px(25),
                        RoundCornersTransformation.CornerType.ALL);
        Glide.with(MainActivity.this)
                .load(R.drawable.alasijia)
                .bitmapTransform(transformation)
                .into(imageView);
    }

    private void initData() {
        CornerBean bean9 = new CornerBean();
        bean9.resId = R.drawable.alasijia;
        bean9.type = RoundCornersTransformation.CornerType.ALL;
        mList.add(bean9);

        CornerBean bean1 = new CornerBean();
        bean1.resId = R.drawable.hashiqi;
        bean1.type = RoundCornersTransformation.CornerType.LEFT_TOP;
        mList.add(bean1);

        CornerBean bean2 = new CornerBean();
        bean2.resId = R.drawable.hashiqi1;
        bean2.type = RoundCornersTransformation.CornerType.RIGHT_TOP;
        mList.add(bean2);

        CornerBean bean3 = new CornerBean();
        bean3.resId = R.drawable.haishiqi2;
        bean3.type = RoundCornersTransformation.CornerType.LEFT_BOTTOM;
        mList.add(bean3);

        CornerBean bean4 = new CornerBean();
        bean4.resId = R.drawable.hashiqi3;
        bean4.type = RoundCornersTransformation.CornerType.RIGHT_BOTTOM;
        mList.add(bean4);

        CornerBean bean5 = new CornerBean();
        bean5.resId = R.drawable.bomei1;
        bean5.type = RoundCornersTransformation.CornerType.LEFT;
        mList.add(bean5);

        CornerBean bean6 = new CornerBean();
        bean6.resId = R.drawable.bomei2;
        bean6.type = RoundCornersTransformation.CornerType.TOP;
        mList.add(bean6);

        CornerBean bean7 = new CornerBean();
        bean7.resId = R.drawable.bomei3;
        bean7.type = RoundCornersTransformation.CornerType.RIGHT;
        mList.add(bean7);

        CornerBean bean8 = new CornerBean();
        bean8.resId = R.drawable.bomei;
        bean8.type = RoundCornersTransformation.CornerType.BOTTOM;
        mList.add(bean8);

    }

    private class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = View.inflate(MainActivity.this, R.layout.item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            RoundCornersTransformation transformation =
                    new RoundCornersTransformation(MainActivity.this,
                            dp2px(25),
                            mList.get(position).type);
            Glide.with(MainActivity.this)
                    .load(mList.get(position).resId)
                    .bitmapTransform(transformation)
                    .into(holder.mImageView);

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    private int dp2px(int dpValue) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = (int) (dpValue * (displayMetrics.densityDpi / 160f));
        return px;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
