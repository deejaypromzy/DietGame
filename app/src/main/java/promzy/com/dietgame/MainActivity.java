package promzy.com.dietgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CircleImageView carbo,protien,vitamins,water,minerals,fats;
    private MediaPlayer bg_Sound;
    private MediaPlayer btn_Click_sound;
    private Animation myAnim;
    private Animation myAnim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitViews();
        SetImage();
        carbo.setOnClickListener(this);
        protien.setOnClickListener(this);
        vitamins.setOnClickListener(this);
        water.setOnClickListener(this);
        fats.setOnClickListener(this);
        minerals.setOnClickListener(this);

    }

    private void SetImage() {
        Glide.with(this)
                .load(R.drawable.carbohydrate)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(carbo);
        Glide.with(this)
                .load(R.drawable.protein)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(protien);
        Glide.with(this)
                .load(R.drawable.minerals)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(minerals);
        Glide.with(this)
                .load(R.drawable.vitamins)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(vitamins);
        Glide.with(this)
                .load(R.drawable.fats)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(fats);
        Glide.with(this)
                .load(R.drawable.water)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(water);
    }

    private void InitViews() {
        carbo=findViewById(R.id.carbohydrate);
        protien=findViewById(R.id.protein);
        vitamins=findViewById(R.id.vitamins);
        water=findViewById(R.id.water);
        fats=findViewById(R.id.fats);
        minerals=findViewById(R.id.minerals);
        bg_Sound= MediaPlayer.create(this,R.raw.game_bg_sound);
        btn_Click_sound=MediaPlayer.create(this,R.raw.btn_click_sound);
        myAnim= AnimationUtils.loadAnimation(this, R.anim.bounce  );
        myAnim2= AnimationUtils.loadAnimation(this, R.anim.scale_up  );
        bg_Sound.start();

        carbo.startAnimation(myAnim);
        protien.startAnimation(myAnim);
        vitamins.startAnimation(myAnim);
        water.startAnimation(myAnim);
        minerals.startAnimation(myAnim);
        fats.startAnimation(myAnim);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.carbohydrate:
                startActivity(new Intent(MainActivity.this, Carbohydrate.class));
                break;
        case R.id.protein:
            startActivity(new Intent(MainActivity.this, Protein.class));
            break;
        case R.id.fats:
            startActivity(new Intent(MainActivity.this, Fats.class));
            break;
        case R.id.vitamins:
            startActivity(new Intent(MainActivity.this, Vitamins.class));
            break;
        }
    }
}
