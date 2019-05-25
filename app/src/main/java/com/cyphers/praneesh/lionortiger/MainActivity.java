package com.cyphers.praneesh.lionortiger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player
    {
        ONE,
        TWO
    }
    Player currentPlayer = Player.ONE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ImageViewTapped(View ImageView1){

        Player[] playerChoices = new Player[9];
        int [] [] WinnerRowsColumn = {  {0,1,2},{3,4,5},{6,7,8}
                                        ,{0,3,6},{1,4,7},{2,5,8}
                                        ,{0,4,8},{2,4,6}};
        int Index = 0;

        ImageView imgview = (ImageView)ImageView1;
        Index = Integer.parseInt(imgview.getTag().toString());
        Log.i("index",""+Index);
        imgview.setTranslationX(-2000);

        if(Player.ONE == currentPlayer) {
            imgview.setImageResource(R.drawable.tiger);
            currentPlayer = Player.TWO;
        }
        else if(Player.TWO == currentPlayer)
        {
            imgview.setImageResource(R.drawable.lion);
            currentPlayer = Player.ONE;
        }

        imgview.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

//        Toast.makeText(this,imgview.getTag().toString(),Toast.LENGTH_SHORT).show();
//        Log.i("Tag",""+imgview.getTag().toString());

    }
}
