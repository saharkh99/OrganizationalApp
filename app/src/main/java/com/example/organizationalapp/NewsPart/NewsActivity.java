package com.example.organizationalapp.NewsPart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.organizationalapp.R;

public class NewsActivity extends AppCompatActivity {

    TextView titleTV,dateTV,tagTV,desTV;
    ImageView imgTV;
    String title,date,tag,des;
    int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getData();
        findView();
        inint();

    }

    private void inint() {
        titleTV.setText(title);
        desTV.setText(des);
        dateTV.setText(date);
        tagTV.setText(tag);
        imgTV.setImageResource(img);
    }

    private void findView() {
        titleTV=findViewById(R.id.title_news);
        tagTV=findViewById(R.id.tag_news);
        desTV=findViewById(R.id.description_news);
        dateTV=findViewById(R.id.date_news);
        imgTV=findViewById(R.id.img_news);
    }

    private void getData() {
        Intent intent = getIntent();
        title=intent.getStringExtra("title");
        date=intent.getStringExtra("date");
        tag=intent.getStringExtra("tag");
        des=intent.getStringExtra("des");
        img=intent.getIntExtra("img", 0);
    }
}
