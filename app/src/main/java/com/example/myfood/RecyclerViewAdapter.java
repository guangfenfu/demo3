package com.example.myfood;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Dish> mData;
    private List<DownLoadTask> downLoadTasks = new ArrayList<>();


    public RecyclerViewAdapter(Context mContext, List<Dish> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_view_dish, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        // show camera first
        myViewHolder.tv_dish_name.setText(mData.get(i).getName());
        myViewHolder.img_dish.setImageResource(android.R.drawable.ic_menu_camera);

        // fire up AnysncTask
        CardItem cardItem = new CardItem(myViewHolder, mData.get(i).getUrl());
        DownLoadTask task = new DownLoadTask();
        downLoadTasks.add(task);
        task.execute(cardItem);

        //set CardView onClickListner
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DishActivity.class);
                intent.putExtra("id", mData.get(i).getId());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_dish_name;
        ImageView img_dish;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);

            tv_dish_name = (TextView)itemView.findViewById(R.id.dish_name_id);
            img_dish = (ImageView) itemView.findViewById(R.id.dish_img_id);
            cardView = (CardView)itemView.findViewById(R.id.cardview_id);


        }
    }

    //
    public class CardItem {
        private MyViewHolder holder;
        private URL url;
        private Bitmap bmp;

        public CardItem(MyViewHolder holder, URL url) {
            this.holder = holder;
            this.url = url;
        }

        public MyViewHolder getHolder() {
            return holder;
        }

        public URL getUrl() {
            return url;
        }

        public Bitmap getBmp() {
            return bmp;
        }

        public void setBmp(Bitmap bmp) {
            this.bmp = bmp;
        }
    }


    private class DownLoadTask extends AsyncTask<CardItem, Void, CardItem>{

        @Override
        protected CardItem doInBackground(CardItem... cardItems) {
            CardItem cardItem = cardItems[0];

            URL url = cardItem.getUrl();
            HttpURLConnection connection = null;
            try{
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                cardItem.setBmp(bmp);

            }catch (IOException e){
                e.printStackTrace();
            }finally {
                connection.disconnect();
            }
            return cardItem;
        }

        @Override
        protected void onPostExecute(CardItem cardItem) {
            cardItem.getHolder().img_dish.setImageBitmap(cardItem.getBmp());
        }
    }

    public void cancelTasks(){

        for(DownLoadTask task : downLoadTasks){
            task.cancel(true);
        }
        downLoadTasks.clear();
    }


}
