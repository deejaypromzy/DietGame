package promzy.com.dietgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;



public class game_success  extends Activity implements View.OnClickListener {
    TextView finalScore,GameScore;
    Button appCompatClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        setContentView(R.layout.game_success);



        finalScore = findViewById(R.id.ffs);
        GameScore = findViewById(R.id.gs);
        appCompatClose =findViewById(R.id.thanks);
        appCompatClose.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
            startActivity(new Intent(game_success.this, MainActivity.class));
            this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(game_success.this,MainActivity.class));
        super.finish();
    }
}
