package soft.divan.clikerpro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// запрет поворота на вертикаль
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // убирает status bar
    }

    public void player1 (View view){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class); // переход на экран для игрока
        startActivity(intent);
    }

    public void player2 (View view){
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);  // переход на экран для двух игроков
        startActivity(intent);
    }
}
