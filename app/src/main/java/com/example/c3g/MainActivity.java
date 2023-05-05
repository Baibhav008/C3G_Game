package com.example.c3g;

import android.graphics.fonts.Font;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int[] gamestate={2,2,2,2,2,2,2,2,2};  //INITIAL GAME STATE

    //WINNING POSITION TAGS
    int[][] winposs={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    //SETTING ACTIVE PLAYER
    int activeplayer =0;
    // 0 FOR blue_ball
    // 1 FOR golden_ball

    boolean gameactive = true; // CHECKING STATE OF GAME IF SOMEONE WON OR NOT


    public void dropin(View view) {
        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString()); // WHICH TAG IS CLICKED
        //TO CHECK IF THE SPACE IS ALREADY TAPPED OR NOT
        if (gamestate[tappedcounter] == 2 && gameactive) {
            gamestate[tappedcounter] = activeplayer; //  SET GAMESTATE ARRAY ELEMENT[TAG] TO  USER CODE
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.blue_ball);
                activeplayer = 1;

            } else {
                counter.setImageResource(R.drawable.golden_ball);
                activeplayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);


            for (int[] winpos : winposs)    //LOOP TO CHECK WHO WON THE GAME
            {

                if (gamestate[winpos[0]] == gamestate[winpos[1]] && gamestate[winpos[1]] == gamestate[winpos[2]] && gamestate[winpos[0]] != 2) {
                    gameactive = false;// SOMEONE WON
                    String won = "";
                    if (activeplayer == 0) {
                        won = "GOLD";
                        ConstraintLayout constraintLayout;
                        constraintLayout=findViewById(R.id.cl);
                        constraintLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.gold_win));
                    } else {
                        won = "BLUE";
                        ConstraintLayout constraintLayout;
                        constraintLayout=findViewById(R.id.cl);
                        constraintLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.blue_win));
                    }
                   // Toast.makeText(this, won + " has won the game", Toast.LENGTH_SHORT).show();
                    Button button = (Button) findViewById(R.id.button);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(won + " has won the game");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    break;

                }
                else if(gamestate[0]!=2&&gamestate[1]!=2&&gamestate[2]!=2&&gamestate[3]!=2&&gamestate[4]!=2&&gamestate[5]!=2&&gamestate[6]!=2&&gamestate[7]!=2&&gamestate[8]!=2)
                {
                   // Toast.makeText(this, "Fools! Nobody Won", Toast.LENGTH_SHORT).show();
                    Button button = (Button) findViewById(R.id.button);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText("Fools! Nobody Won");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);

                }
            }

        }
    }


    // RESET THE GAME
    public void playAgain(View view)
    {
        Toast.makeText(this, "Starting new game....", Toast.LENGTH_SHORT).show();
        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0 ; i<gridLayout.getChildCount();i++)
        {
            ImageView counter2 = (ImageView) gridLayout.getChildAt(i);
            counter2.setImageDrawable(null);
        }
       for(int i=0;i<gamestate.length;i++)
       {
           gamestate[i]=2;
       }

        activeplayer =0;
        gameactive = true;
        ConstraintLayout constraintLayout;
        constraintLayout=findViewById(R.id.cl);
        constraintLayout.setBackground(ContextCompat.getDrawable(this,R.drawable.bg2));


    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}