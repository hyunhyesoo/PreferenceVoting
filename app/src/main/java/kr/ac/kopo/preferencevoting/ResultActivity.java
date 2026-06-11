package kr.ac.kopo.preferencevoting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    TextView textTitle;
    ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int[] imgResArr = {R.drawable.maple1, R.drawable.maple2, R.drawable.maple3, R.drawable.maple4, R.drawable.maple5, R.drawable.maple6, R.drawable.maple7, R.drawable.maple8, R.drawable.maple9};
        int[] voteCount = intent.getIntArrayExtra("voteCount");
        String[] mapleNameArr = intent.getStringArrayExtra("mapleNameArr");

        TextView[] textArr = new TextView[mapleNameArr.length];
        RatingBar[] ratingArr = new RatingBar[mapleNameArr.length];

        textTitle = findViewById(R.id.text_title);
        imgv = findViewById(R.id.imgv);

        //투표수 중에 최댓값 구하기
        int maxIndex = 0;
        int maxValue = voteCount[0];

        for (int i = 1; i < voteCount.length; i++) {
            if (voteCount[i] > maxValue) {
                maxValue = voteCount[i];
                maxIndex = i;
            }
        }

        textTitle.setText(mapleNameArr[maxIndex]);
        imgv.setImageResource(imgResArr[maxIndex]);

        int[] textIdArr = {R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9};
        int[] ratingIdArr = {R.id.rating1, R.id.rating2, R.id.rating3, R.id.rating4, R.id.rating5, R.id.rating6, R.id.rating7, R.id.rating8, R.id.rating9};

        for (int i = 0; i < textArr.length; i++) {
            textArr[i] = findViewById(textIdArr[i]);
            ratingArr[i] = findViewById(ratingIdArr[i]);
        }

        for (int i = 0; i < textArr.length; i++) {
            textArr[i].setText(mapleNameArr[i]);
            ratingArr[i].setRating(voteCount[i]);
        }

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}