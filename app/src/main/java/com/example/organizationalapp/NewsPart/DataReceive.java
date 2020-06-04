package com.example.organizationalapp.NewsPart;

import android.content.Context;

import com.example.organizationalapp.R;

import java.util.ArrayList;
import java.util.List;

public class DataReceive {
    public static List<News> getNews(Context context){
        List<News>nList=new ArrayList<>();
        for(int i=0;i<5;i++) {
            News news=new News();
            news.setDate("1399,02,29");
            news.setIdNews(i);
            news.setTag("خبر");
            news.setTitle("عنوان خبر یا اطلاعیه");
            news.setImg(R.drawable.scrum);
            news.setDescription("اساس پیدایش جمهوری اسلامی ایران بر پایه نظریه ولایت فقیه است. حسینعلی منتظری در کتابی به تشریح (روشن سازی) این نظریه پرداخت. بر پایه این نظریه در زمان غیبت امام دوازدهم، فقیه واجد شرایط به عنوان ولی فقیه انتخاب می\u200Cشود که وظایف امام غایب در زمان غیبت را به عهده دارد. نظریه ولایت مطلقه فقیه در سال\u200Cهای اخیر توسط آیت الله خمینی مطرح شده و نظریات مشابهی نیز در آثار فقیهان پیشین نیز هست");
            nList.add(news);
        }
        return nList;

        }
}
