package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int col1o, col2o, col3o;
    public int row1o, row2o, row3o;
    public int dia1o,dia2o;

    public int col1x, col2x, col3x;
    public int row1x, row2x, row3x;
    public int dia1x,dia2x;

    public  int bo1, bo2,bo3,bo4,bo5,bo6,bo7,bo8,bo9;

    public boolean isO;

    TextView but1,but2,but3,but4,but5,but6,but7,but8,but9,reset;
    TextView winner;

    public int worl; //0 is game keep going, 1 is o win, 2 is x win
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        worl = 0;
        col1o = col2o = col3o = 0;
        row1o = row2o = row3o = 0;
        dia1o = dia2o = 0;
        col1x = col2x = col3x = 0;
        row1x = row2x = row3x = 0;
        dia1x = dia2x = 0;
        bo1 = bo2 = bo3 = bo4 = bo5 = bo6 = bo7 = bo8 = bo9 = 0;
        isO = true;

        but1 = findViewById(R.id.b1);
        but2 = findViewById(R.id.b2);
        but3 = findViewById(R.id.b3);
        but4 = findViewById(R.id.b4);
        but5 = findViewById(R.id.b5);
        but6 = findViewById(R.id.b6);
        but7 = findViewById(R.id.b7);
        but8 = findViewById(R.id.b8);
        but9 = findViewById(R.id.b9);
        reset = findViewById(R.id.buttonReset);
        winner = findViewById(R.id.textView);

    }

    public void getWorL() {
        if(worl==0) {
            if (col1o == 3 || col2o == 3 || col3o == 3 || row1o == 3 || row2o == 3 || row3o == 3 || dia1o == 3 || dia2o == 3) {
                worl = 1;
                winner.setText("O win");
            } else if (col1x == 3 || col2x == 3 || col3x == 3 || row1x == 3 || row2x == 3 || row3x == 3 || dia1x == 3 || dia2x == 3) {
                worl = 2;
                winner.setText("X win");
            }
        }
    }

    public void reset(View view){
        but1.setText("");
        but2.setText("");
        but3.setText("");
        but4.setText("");
        but5.setText("");
        but6.setText("");
        but7.setText("");
        but8.setText("");
        but9.setText("");
        winner.setText("O vs X");
        worl = 0;
        col1o = col2o = col3o = 0;
        row1o = row2o = row3o = 0;
        dia1o = dia2o = 0;
        col1x = col2x = col3x = 0;
        row1x = row2x = row3x = 0;
        dia1x = dia2x = 0;
        bo1 = bo2 = bo3 = bo4 = bo5 = bo6 = bo7 = bo8 = bo9 = 0;
        isO = true;

    }

    public void b1(View view){
        if(bo1 != 1&&worl==0){
            if(isO){
                bo1++;
                col1o++;
                row1o++;
                dia1o++;
                but1.setText("O");
                getWorL();
                isO = false;
            }
            else{
                bo1++;
                col1o--;
                row1o--;
                dia1o--;

                col1x++;
                row1x++;
                dia1x++;
                but1.setText("X");
                getWorL();
                isO = true;
            }
        }

    }
    public void b2(View view){
        if(bo2 != 1&&worl==0){
            if(isO){
                bo2++;
                col2o++;
                row1o++;
                but2.setText("O");
                getWorL();
                isO = false;
            }
            else{
                bo2++;
                col2o--;
                row1o--;

                col2x++;
                row2x++;
                but2.setText("X");
                getWorL();
                isO = true;
            }
        }
    }
    public void b3(View view){
        if(bo3 != 1&&worl==0){
            if(isO){
                bo3++;
                col3o++;
                row1o++;
                dia2o++;
                but3.setText("O");
                getWorL();
                isO = false;
            }
            else{
                bo3++;
                col3o--;
                row1o--;
                dia2o--;

                col3x++;
                row1x++;
                dia2x++;
                but3.setText("X");
                getWorL();
                isO = true;
            }
        }
    }
    public void b4(View view){
        if(bo4 != 1&&worl==0){
            if(isO){
                bo4++;
                col1o++;
                row2o++;
                but4.setText("O");
                getWorL();
                isO = false;
            }
            else{
                bo4++;
                col1o--;
                row2o--;

                col1x++;
                row2x++;
                but4.setText("X");
                getWorL();
                isO = true;
            }
        }
    }
    public void b5(View view){
        if(bo5 != 1&&worl==0){
            if(isO){
                bo5++;
                col2o++;
                row2o++;
                dia1o++;
                dia2o++;
                but5.setText("O");
                getWorL();
                isO = false;
            }
            else {
                bo5++;
                col2o--;
                row2o--;
                dia1o--;
                dia2o--;

                col2x++;
                row2x++;
                dia1x++;
                dia2x++;
                but5.setText("X");
                getWorL();
                isO = true;
            }
        }
    }
    public void b6(View view){
        if(bo6 != 1&&worl==0){
            if(isO){
                bo6++;
                col3o++;
                row2o++;
                but6.setText("O");
                getWorL();
                isO = false;
            }
            else{
                bo6++;
                col3o--;
                row2o--;

                col3x++;
                row2x++;
                but6.setText("X");
                getWorL();
                isO = true;
            }
        }
    }
    public void b7(View view){
        if(bo7 != 1&&worl==0){
            if(isO){
                bo7++;
                col1o++;
                row3o++;
                dia2o++;
                but7.setText("O");
                getWorL();
                isO = false;
            }
            else {
                bo7++;
                col1o--;
                row3o--;
                dia2o--;

                col1x++;
                row3x++;
                dia2x++;
                but7.setText("X");
                getWorL();
                isO = true;
            }
        }
    }
    public void b8(View view) {
        if(bo8 != 1&&worl==0){
            if(isO){
                bo8++;
                col2o++;
                row3o++;
                but8.setText("O");
                getWorL();
                isO = false;
            }
            else {
                bo8++;
                col2o--;
                row3o--;

                col2x++;
                row3x++;
                but8.setText("X");
                getWorL();
                isO = true;
            }
        }

    }
    public void b9(View view){
        if(bo9 != 1&&worl==0){
            if(isO){
                bo9++;
                col3o++;
                row3o++;
                dia1o++;
                but9.setText("O");
                getWorL();
                isO = false;
            }
            else {
                bo9++;
                col3o--;
                row3o--;
                dia1o--;

                col3x++;
                row3x++;
                dia1x++;
                but9.setText("X");
                getWorL();
                isO = true;
            }
        }

    }

}