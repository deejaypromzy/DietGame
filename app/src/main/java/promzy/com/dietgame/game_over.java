package promzy.com.dietgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class game_over extends Activity implements View.OnClickListener {
    TextView finalScore,GameScore,Bonus,t1,t2,t3;
    Button appCompatClose,appCompatRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        setContentView(R.layout.game_over);

        int score = getIntent().getIntExtra("score",0);
        int highscore = getIntent().getIntExtra("HighScore",0);

        // Font path
        String fontPath = "font/CircleD_Font_by_CrazyForMusic.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);


        finalScore = findViewById(R.id.fs);
        GameScore = findViewById(R.id.gs);
        Bonus = findViewById(R.id.b);
        t1 = findViewById(R.id.bb);
        t2 = findViewById(R.id.ggs);
        t3 = findViewById(R.id.ffs);


        appCompatClose =findViewById(R.id.appCompatClose);
        appCompatRetry = findViewById(R.id.appCompatRetry);

        appCompatClose.setOnClickListener(this);
        appCompatRetry.setOnClickListener(this);


        GameScore.setTypeface(tf);
        finalScore.setTypeface(tf);
        Bonus.setTypeface(tf);
        t1.setTypeface(tf);
        t2.setTypeface(tf);
        t3.setTypeface(tf);


        finalScore.setText(String.valueOf(highscore));
        GameScore.setText(String.valueOf(score));
        Bonus.setText(String.valueOf(score));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.appCompatClose:
                startActivity(new Intent(game_over.this, MainActivity.class));
                this.finish();
                break;
            case R.id.appCompatRetry:
                this.setFinishOnTouchOutside(true);
                startActivity(new Intent(game_over.this, Carbohydrate.class));
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(game_over.this,MainActivity.class));
        super.finish();
    }
}
