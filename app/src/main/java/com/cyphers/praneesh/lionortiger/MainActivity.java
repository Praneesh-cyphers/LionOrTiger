package com.cyphers.praneesh.lionortiger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player
    {
        ONE,
        TWO,
        NONE
    }
    Player[] playerChoices = new Player[9];
    int [] [] WinnerRowsColumn = {  {0,1,2},{3,4,5},{6,7,8}
            ,{0,3,6},{1,4,7},{2,5,8}
            ,{0,4,8},{2,4,6}};
    int Index = 0;
    Player currentPlayer = Player.ONE;
    Boolean bIsGameOver = false;
    private Button btnRest;
    private GridLayout gridLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i < 9;i++)
        {
            playerChoices[i] = Player.NONE;
        }

        btnRest = findViewById(R.id.button);

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTheGame();
            }
        });

        gridLayout = findViewById(R.id.gridLayout1);
    }

    public void ImageViewTapped(View ImageView1){


        ImageView imgview = (ImageView)ImageView1;
        Index = Integer.parseInt(imgview.getTag().toString());

        if((playerChoices[Index] == Player.NONE)&&(false == bIsGameOver)) {
            playerChoices[Index] = currentPlayer;

            imgview.setTranslationX(-2000);

            if (Player.ONE == currentPlayer) {
                imgview.setImageResource(R.drawable.tiger);
                currentPlayer = Player.TWO;
            } else if (Player.TWO == currentPlayer) {
                imgview.setImageResource(R.drawable.lion);
                currentPlayer = Player.ONE;
            }

            imgview.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

//        Toast.makeText(this,imgview.getTag().toString(),Toast.LENGTH_SHORT).show();
//        Log.i("Tag",""+imgview.getTag().toString());

            for (int[] winnerColumns : WinnerRowsColumn) {
                if ((playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]) &&
                        (playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]) &&
                        (playerChoices[winnerColumns[0]] != Player.NONE)) {
                    String stPlayer = "";
                    bIsGameOver = true;
                    btnRest.setVisibility(View.VISIBLE);
                    if (playerChoices[Index] == Player.ONE) {
                        stPlayer = "Player One";
                    } else if (playerChoices[Index] == Player.TWO) {
                        stPlayer = "Player Two";
                    }

                    Toast.makeText(this, stPlayer + " Won the Match", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //to reset the game and reset all the local variable
    public void ResetTheGame()
    {
        for(int index = 0; index < gridLayout.getChildCount();index++)
        {
            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
        }
        for(int i=0;i < 9;i++)
        {
            playerChoices[i] = Player.NONE;
        }
        currentPlayer = Player.ONE;
        bIsGameOver = false;
        btnRest.setVisibility(View.GONE);

    }
}
