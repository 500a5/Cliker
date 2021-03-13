package soft.divan.clikerpro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    TextView game_timer;
    TextView text_start1;
    TextView number_of_clicks1;
    TextView text_start2;
    TextView number_of_clicks2;
    ImageButton button_player1;
    ImageButton button_player2;
    TextView player1;
    TextView player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);  // убирает status bar
        text_start2 = (TextView) findViewById(R.id.text_start2);
        number_of_clicks2 = (TextView) findViewById(R.id.number_of_clicks2);
        game_timer = (TextView) findViewById(R.id.game_timer);
        text_start1 = (TextView) findViewById(R.id.text_start1);
        number_of_clicks1 = (TextView) findViewById(R.id.number_of_clicks1);
        button_player1 = (ImageButton) findViewById(R.id.button_player1);
        text_start1.setText("Начать игру");
        number_of_clicks2.setText("Кликов: 0");
        text_start2.setText("Начать игру");
        number_of_clicks1.setText("Кликов: 0");
        button_player2 = (ImageButton) findViewById(R.id.button_player2);
        player1=(TextView) findViewById(R.id.player1);
        player2 =(TextView) findViewById(R.id.player2);
        player1.setText("Игрок 1");
        player2.setText("Игрок 2");
    }


    public void back(View view) {  // "назад"
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);
    }

    void empty_text(){
        text_start1.setText("");
        text_start2.setText("");
    }

    int i = 0, z = 0,b=0;
    void game(){
        if (b == 1 ) {
            new CountDownTimer(11000, 995) { // таймер "игры"
                public void onTick(long mil) {
                    game_timer.setText(String.valueOf(mil / 1000) + " " + "Секунд ");
                }

                public void onFinish() {
                    if (i>z){           // отображение победителя
                        Toast q = Toast.makeText(getApplicationContext(),"Победил игрок 1", Toast.LENGTH_SHORT);
                        q.setGravity (Gravity.CENTER,0,0);
                        q.show();
                    }
                    else if (i==z){
                        Toast q = Toast.makeText(getApplicationContext(),"Ничья", Toast.LENGTH_SHORT);
                        q.setGravity (Gravity.CENTER,0,0);
                        q.show();
                    }
                    else {
                        Toast q = Toast.makeText(getApplicationContext(),"Победил игрок 2", Toast.LENGTH_SHORT);
                        q.setGravity (Gravity.CENTER,0,0);
                        q.show();
                    }
                    game_timer.setText("Время вышло ");
                    button_player1.setEnabled(false);
                    button_player2.setEnabled(false);
                    new CountDownTimer(4000, 995) { // таймер  "отдыха"
                        public void onTick(long mil) {
                            text_start1.setText(String.valueOf(mil / 1000) + " - " + "Заново");
                            text_start2.setText(String.valueOf(mil / 1000) + " - " + "Заново");
                        }

                        public void onFinish() {
                            text_start1.setText("Начать игру");
                            game_timer.setText("10 Секунд");
                            button_player1.setEnabled(true);
                            text_start2.setText("Начать игру");
                            button_player2.setEnabled(true);
                            number_of_clicks1.setText("Кликов: 0");
                            number_of_clicks2.setText("Кликов: 0");
                            z = 0;
                            i = 0;
                            b=0;
                        }
                    }.start();
                }
            }.start();
        }
    }

    public void player1(View view) { //игрок 1
        b++;
        i++;
        empty_text();
        number_of_clicks1.setText("Кликов:" + " " + String.valueOf(i));
        game();
    }

    public void player2(View view) { // игрок 2
        b++;
        z++;
        empty_text();
        number_of_clicks2.setText("Кликов:" + " " + String.valueOf(z));
        game();
    }
}
