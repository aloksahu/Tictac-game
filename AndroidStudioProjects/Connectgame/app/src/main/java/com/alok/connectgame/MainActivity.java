package com.alok.connectgame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    int player=0;
    //for cross its 0 for zero  its 1
    int state[]={2,2,2,2,2,2,2,2,2};
    // to maintain the states of a player click.
    int counter=0;
    int win=0;
    int winner=0;
    int active=0;

    public void centre(View v)
    {
        ImageView im=(ImageView)v;
        int tag=Integer.parseInt(im.getTag().toString());
        if(state[tag]==2 && active==0)
        {


            if (player == 1) {
                im.setTranslationX(-1000f);
                im.setImageResource(R.drawable.temp);
                im.animate().translationXBy(1000f).rotation(360f).setDuration(200);
                state[tag]=0;
                player = 0;
            } else {
                im.setTranslationY(-1000f);
                im.setImageResource(R.drawable.cross);
                im.animate().translationYBy(1000f).rotation(360f).setDuration(200);
                player = 1;
                state[tag]=1;
            }


            counter++;
        }
        if(counter>4)
            checkwin();
        if(win==1)
        {
            TextView t=(TextView)findViewById(R.id.message);
            if(winner==1)
            t.setText("Player zero Wins");
           else if(winner==2)
                t.setText("Player cross Wins");
            else
            {
                t.setText("Its a draw");
            }

            LinearLayout l=(LinearLayout)findViewById(R.id.playagain);
            l.animate().rotation(360f).setDuration(1000);
            l.setVisibility(View.VISIBLE);
            active=1;
        }

    }

    public void checkwin()
    {
             if(((state[0]==0&&state[1]==0 && state[2]==0)||
                (state[3]==0&&state[4]==0 && state[5]==0)||
                (state[6]==0&&state[7]==0 && state[8]==0)||
                (state[0]==0&&state[3]==0 && state[6]==0)||
                (state[1]==0&&state[4]==0 && state[7]==0)||
                (state[2]==0&&state[5]==0 && state[8]==0)||
                (state[0]==0&&state[4]==0 && state[8]==0)||
                (state[2]==0&&state[4]==0 && state[6]==0))&&win==0)
                {
            Log.i("hello", "Player zero wins");
                    win=1;
                    winner=1;
                    return;
                }
        else if(((state[0]==1&&state[1]==1 && state[2]==1)||
                     ( state[3]==1&&state[4]==1 && state[5]==1)||
                     ( state[6]==1&&state[7]==1 && state[8]==1)||
                     (state[0]==1&&state[3]==1 && state[6]==1)||
                     (state[1]==1&&state[4]==1 && state[7]==1)||
                     (state[2]==1&&state[5]==1 && state[8]==1)||
                     (state[0]==1&&state[4]==1 && state[8]==1)||
                     (state[2]==1&&state[4]==1 && state[6]==1))&&win==0)
             {
                 Log.i("world", "Player cross wins");
                 win=1;
                 winner=2;
                 return;
             }
        else
             {
                 if(counter==9)
                 {
                     win=1;
                     winner=3;
                     Log.i("check", "Better luck next time.It's a draw");
                     return;
                 }
             }
    }

    public void startagain(View v)
    {
        player=0;
        counter=0;
        winner=0;
        active=0;
        win=0;
        for(int i=0;i<9;i++)
            state[i]=2;
        LinearLayout l=(LinearLayout)findViewById(R.id.playagain);
        l.animate().rotation(360f).setDuration(1000);
        l.setVisibility(View.INVISIBLE);
        GridLayout g=(GridLayout)findViewById(R.id.grid1);
        for(int i=0;i<9;i++)
        {
            ((ImageView)g.getChildAt(i)).setImageResource(0);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
