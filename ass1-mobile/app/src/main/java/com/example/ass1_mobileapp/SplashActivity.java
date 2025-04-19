package com.example.ass1_mobileapp;//Main Activity
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {
    private Animation up, down;
    private TextView splash_text;
    private ImageView splash_img;
//--------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),(v,insets)-> {//-----ask
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());//----ask
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);//---ask
            return insets;//----
        });
//-----------------------------------------------------
        up = AnimationUtils.loadAnimation(this, R.anim.high_animation);
        down = AnimationUtils.loadAnimation(this, R.anim.below_animation);

        splash_text = findViewById(R.id.txtsplash);
        splash_img = findViewById(R.id.imgsplash);

        splash_text.setAnimation(down);
        splash_img.setAnimation(up);
    }
//---------------------------------------------------------------------
    @Override
    //after the activity come when it placed in background
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, SearchProductActivity.class);
            startActivity(intent);
            finish();
        }, 3000);//3 second
    }
}
//------------------------------------------------------