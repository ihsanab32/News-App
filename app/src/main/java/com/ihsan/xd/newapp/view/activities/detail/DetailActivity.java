package com.ihsan.xd.newapp.view.activities.detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.ihsan.xd.newapp.R;
import com.ihsan.xd.newapp.view.activities.detail.detailwebview.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.img_news)
    AppCompatImageView ivImgNews;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.date_news)
    TextView txtDateNews;
    @BindView(R.id.title_news)
    TextView txtTitleNews;
    @BindView(R.id.author_news)
    TextView txtAuthorNews;
    @BindView(R.id.content_news)
    TextView txtContentNews;
    @BindView(R.id.source_news)
    TextView txtSourceNews;
    String imgNews, titleNews, contentNews, dateNews, authorNews, sourceNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle(Html.fromHtml("<font color='#ffffff'>Detail News</font>"));
        }
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
            case R.id.share: {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, titleNews + "\n\n" + contentNews + "\n\n" + sourceNews);
                startActivity(Intent.createChooser(i, "Share this Article ?"));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        imgNews = getIntent().getExtras().getString("imgNews");
        titleNews = getIntent().getExtras().getString("titleNews");
        contentNews = getIntent().getExtras().getString("contentNews");
        dateNews = getIntent().getExtras().getString("dateNews");
        authorNews = getIntent().getExtras().getString("authorNews");
        sourceNews = getIntent().getExtras().getString("sourceNews");
        Glide.with(getApplicationContext())
                .load(imgNews)
                .placeholder(R.drawable.ic_null)
                .into(ivImgNews);
        txtTitleNews.setText(titleNews);
        txtContentNews.setText(contentNews);
        txtDateNews.setText(dateNews);
        txtSourceNews.setText(getResources().getString(R.string.view_more));
        txtAuthorNews.setText(authorNews);
    }

    @OnClick(R.id.source_news)
    public void onViewClicked() {
        if (sourceNews == null) {
            Toast.makeText(this, "Url Not Found", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
            i.putExtra("url", sourceNews);
            startActivity(i);
        }

    }
}