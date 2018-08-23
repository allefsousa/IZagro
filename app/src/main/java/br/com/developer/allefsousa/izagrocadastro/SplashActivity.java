package br.com.developer.allefsousa.izagrocadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;

import br.com.developer.allefsousa.izagrocadastro.operacaoUsuario.opUsuarioView;

public class SplashActivity extends AppCompatActivity {

    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startAnimation();
    }

    public void startAnimation() {


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;

                    //

                    while (waited < 2000) {

                        sleep(100);
                        waited += 100;


                    }
                    Intent intent = new Intent(SplashActivity.this, opUsuarioView.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);

                } catch (Exception e) {

                }


            }
        };
        splashTread.start();
    }
}
