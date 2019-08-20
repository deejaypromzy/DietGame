package promzy.com.dietgame;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Carbohydrate extends Activity {
   MediaPlayer bg_Sound,correct_sound,wrong_sound,applause,game_over_sound;
   int SCORE=0;
    CountDownTimer countDownTimer;
    TextView timerTextView;
    SharedPreferences ScorePrefs;
    TextView hi,hi2,hi3,hi4;
    String myTags="";
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carbohydrate);
        game_over_sound=MediaPlayer.create(this,R.raw.game_over);
        bg_Sound=MediaPlayer.create(this,R.raw.game_bg_sound);
        correct_sound=MediaPlayer.create(this,R.raw.correct);
        wrong_sound=MediaPlayer.create(this,R.raw.error);
        applause=MediaPlayer.create(this,R.raw.applause);
        timerTextView = findViewById(R.id.timerTextView);
        bg_Sound.start();
        bg_Sound.setLooping(true);
        // Font path
        String fontPath = "font/CircleD_Font_by_CrazyForMusic.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        // saved score
        ScorePrefs = getSharedPreferences("HighScore", MODE_PRIVATE);

        if(SCORE>ScorePrefs.getInt("currentHighScore",0)) {
            ScorePrefs.edit().putInt("currentHighScore", SCORE).apply();
        }



        LinearLayout sample = findViewById(R.id.sample);
        sample.setTag("not");
        sample.setOnLongClickListener(new MyClickListener());
        LinearLayout sample2 = findViewById(R.id.sample2);
        sample2.setTag("carbo");
        sample2.setOnLongClickListener(new MyClickListener());
        LinearLayout sample3 = findViewById(R.id.sample3);
        sample3.setTag("carbo");
        sample3.setOnLongClickListener(new MyClickListener());
        LinearLayout sample4 = findViewById(R.id.sample4);
        sample4.setTag("not");
        sample4.setOnLongClickListener(new MyClickListener());
        LinearLayout sample5 = findViewById(R.id.sample5);
        sample5.setTag("carbo");
        sample5.setOnLongClickListener(new MyClickListener());
        LinearLayout sample6 = findViewById(R.id.sample6);
        sample6.setTag("carbo");
        sample6.setOnLongClickListener(new MyClickListener());



        findViewById(R.id.v1).setOnDragListener(new MyDragListener());
        findViewById(R.id.v2).setOnDragListener(new MyDragListener());
        findViewById(R.id.v3).setOnDragListener(new MyDragListener());
        findViewById(R.id.v4).setOnDragListener(new MyDragListener());
        findViewById(R.id.sample).setOnDragListener(new MyDragListener());
        findViewById(R.id.sample2).setOnDragListener(new MyDragListener());
        findViewById(R.id.sample3).setOnDragListener(new MyDragListener());
        findViewById(R.id.sample4).setOnDragListener(new MyDragListener());
        findViewById(R.id.sample5).setOnDragListener(new MyDragListener());
        findViewById(R.id.sample6).setOnDragListener(new MyDragListener());
        findViewById(R.id.sv).setOnDragListener(new MyDragListener());
        findViewById(R.id.abc4).setOnDragListener(new MyDragListener());
        findViewById(R.id.abc).setOnDragListener(new MyDragListener());
        findViewById(R.id.abc2).setOnDragListener(new MyDragListener());
        findViewById(R.id.abc3).setOnDragListener(new MyDragListener());
        findViewById(R.id.main).setOnDragListener(new MyDragListener());
        findViewById(R.id.top).setOnDragListener(new MyDragListener());




        hi = findViewById(R.id.highScore);
        hi.setTypeface(tf);
        hi2 = findViewById(R.id.highScoretv);
        hi2.setTypeface(tf);
        hi3 = findViewById(R.id.hctv);
        hi3.setTypeface(tf);
        hi4 = findViewById(R.id.Score);
        hi4.setTypeface(tf);


        //default high score
        hi.setText(String.valueOf(ScorePrefs.getInt("currentHighScore",0)));
        controlTimer();

    }

    private final class MyClickListener implements OnLongClickListener {

        // called when the item is long-clicked
        @Override
        public boolean onLongClick(View view) {
            // TODO Auto-generated method stub

            // create it from the object's tag
            ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());


            myTags= String.valueOf(view.getTag());
            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            DragShadowBuilder shadowBuilder = new DragShadowBuilder(view);

            view.startDrag( data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );


            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {
                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                  break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    //    v.setBackground( getResources().getDrawable(R.color.colorPrimary));	//go back to normal shape
                    break;
                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                     //   v.setBackground( getResources().getDrawable(R.color.colorAccent));	//go back to normal shape
                    break;
                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    // if the view is the bottomlinear, we accept the drag item
                    if(myTags.equals("carbo")){
                    if    ( v == findViewById(R.id.v1 ) ||
                            v == findViewById(R.id.v2 ) ||
                            v == findViewById(R.id.v3 ) ||
                            v == findViewById(R.id.v4 ) ) {

                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);
                        LinearLayout containView = (LinearLayout) v;
                        containView.addView(view);
                        view.setVisibility(View.VISIBLE);
                        //SCORE BOARD
                        SCORE = SCORE + 10;
                        hi4.setText(String.valueOf(SCORE));
                        correct_sound.start();
                        //recored high score
                        if (SCORE >= ScorePrefs.getInt("currentHighScore", 0)) {
                            ScorePrefs.edit().putInt("currentHighScore", SCORE).apply();
                            hi.setText(String.valueOf(ScorePrefs.getInt("currentHighScore", 0)));
                        }
                    }
                        //check game success
                        gameSuccess();
                    }else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Context context = getApplicationContext();
                        Toast.makeText(context, "Sorry!! Try again", Toast.LENGTH_LONG).show();
                        wrong_sound.start();
                        break;
                    }
                        break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                       //  v.setBackground( getResources().getDrawable(android.R.color.black));	//go back to normal shape
                    default:
                    break;
            }
            return true;
        }
    }


    @Override
    protected void onPause() {
        bg_Sound.stop();
        super.onPause();
    }

    public void updateTimer(int secondsLeft)
    {
        int minutes = (int) secondsLeft / 60; //convert seconds to minutes
        int seconds = secondsLeft - minutes * 60; //remaining seconds

        String secondStr = Integer.toString(seconds);
        String minuteStr = Integer.toString(minutes);

        // if minutes or seconds have just one digit 0 is added in front of the value

        if(secondStr.length() == 1)
        {
            secondStr = "0" + secondStr;

        }
        if(minuteStr.length() == 1)
        {
            minuteStr = "0" + minuteStr;
        }
        timerTextView.setText(minuteStr + ":" + secondStr);
    }

    public void controlTimer()
    {
            countDownTimer = new CountDownTimer( 1000 * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {

                       openDialog();

                }
            }.start();
        }

    public void openDialog(){
        if(SCORE<40) {
            timerTextView.setText("Time Up !!");
            Intent myInt = new Intent(Carbohydrate.this,game_over.class);
            myInt.putExtra("score",SCORE);
            myInt.putExtra("HighScore",ScorePrefs.getInt("currentHighScore",0));
            startActivity(myInt);
            bg_Sound.stop();
            game_over_sound.start();

        }


    }
    private void gameSuccess() {
        if(SCORE>=40 && !Objects.equals(timerTextView.getText().toString(), "Time Up !!")){
            timerTextView.setText("Congratulation !!");
            Intent myInt = new Intent(Carbohydrate.this,game_success.class);
            startActivity(myInt);
            bg_Sound.stop();
            applause.start();
            finish();
        }
    }

    }


