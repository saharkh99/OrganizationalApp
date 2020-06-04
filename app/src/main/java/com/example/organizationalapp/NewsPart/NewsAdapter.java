package com.example.organizationalapp.NewsPart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.organizationalapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private onItemClickListener mlistener;
    public interface onItemClickListener{
        void onItemClick(int postion);
    }
public  void setOnItemClickListener(onItemClickListener listener){
    mlistener=listener;
}
    private Context context;
    private List<News> news;
    public  NewsAdapter(Context context, List<News>news){
       this.context=context;
       this.news=news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_layout, parent,false);
        return new NewsViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        News news1=news.get(position);
        holder.des.setText(news1.getDescription());
        holder.title.setText(news1.getTitle());
        holder.date.setText(news1.getDate());
        holder.tag.setText(news1.getTag());
      //  holder.img.setImageDrawable(context.getDrawable(news.getImg()));
        holder.img.setImageResource(news1.getImg());


    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public   class NewsViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView title;
        private TextView date;
        private TextView tag;
        private TextView des;
        private CardView cv;

        public NewsViewHolder(@NonNull View itemView,final onItemClickListener listener ) {
            super(itemView);
            cv=itemView.findViewById(R.id.news_cardview);
            img=itemView.findViewById(R.id.img_news);
            title=itemView.findViewById(R.id.tv_title_news);
            date=itemView.findViewById(R.id.tv_date_news);
            tag=itemView.findViewById(R.id.tv_tag_news);
            des=itemView.findViewById(R.id.tv_description_news);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listener!=null){
                        int postion=getAdapterPosition();
                        listener.onItemClick(postion);
                    }
                }
            });
        }
    }

}
