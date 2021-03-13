package soft.divan.clikerpro;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    TextView game_timer;
    TextView text_start;
    TextView number_of_clicks;
    TextView record;
    ImageButton button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // запрет на поворот на горизенталь
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);   // убирает status bar
        game_timer = (TextView) findViewById(R.id.game_timer);
        text_start = (TextView) findViewById(R.id.text2);
        number_of_clicks = (TextView) findViewById(R.id.text3);
        record = (TextView) findViewById(R.id.record);
        button1 = (ImageButton) findViewById(R.id.button_player1);
        text_start.setText("Начать игру");
        number_of_clicks.setText("Кликов: 0");
        try {
            rec();         // отображение рекорда
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void rec () throws IOException {                      // функция которая отбражет рекорд
        try {
            FileInputStream fos = openFileInput(nem) ;
            byte[] bytes = new byte[fos.available()];
            fos.read(bytes);
            String text = new String (bytes);
            record.setText("Ваш рекорд:"+" "+text);
            fos.close();
            m = text;
        }
        catch (IOException e ) {

            String  tt =  "0";                                                                    /** при запуске в первый раз записывпем в фаил рекорд 0 **/
            FileOutputStream foz = openFileOutput(nem, Context.MODE_PRIVATE);
            foz.write(tt.getBytes());
            foz.close();
            FileInputStream fos = openFileInput(nem) ;
            byte[] bytes = new byte[fos.available()];
            fos.read(bytes);
            String text = new String (bytes);
            record.setText("Ваш рекорд:"+" "+text);
            fos.close();
            m = text;
        }
    }

    int i=0;
    String m,nem="0";
    public void game(View view) throws IOException {
        i++;
        text_start.setText("");
        rec();
        int q = Integer.valueOf(m);
        if (i>q) {                                 // перезапись рекорда
            m = String.valueOf(i);
            FileOutputStream t = openFileOutput(nem, Context.MODE_PRIVATE);
            t.write(m.getBytes());
            t.close();
            record.setText("Ваш рекорд:"+" "+m);
        }
        number_of_clicks.setText("Кликов:"+" "+String.valueOf(i));
        if (i==1){
            new CountDownTimer(11000,995){   // таймер "игры "
                public void onTick(long mil){
                    game_timer.setText(String.valueOf(mil/1000)+" "+"Секунд ");}
                public void onFinish(){
                    game_timer.setText("Время вышло ");
                    button1.setEnabled(false);
                    new CountDownTimer(4000,995){ // таймер "отдыха"
                            public void onTick(long mil){
                                text_start.setText(String.valueOf(mil/1000)+" - "+"Заново");}
                        public void onFinish(){
                            text_start.setText("Начать игру");
                            game_timer.setText("10 Секунд");
                            button1.setEnabled(true);
                            number_of_clicks.setText("Кликов: 0");
                            i=0;
                        }
                    }.start();
                }
            }.start();
        }
    }

    public void back (View view){
        Intent intent = new Intent(MainActivity2.this, MainActivity.class); //"назад"
        startActivity(intent);
    }
}








