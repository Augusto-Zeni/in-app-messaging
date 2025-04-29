package com.example.firebaseinappdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar o Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Botão para simular evento que aciona mensagem in-app
        Button triggerButton = findViewById(R.id.button_trigger_event);
        triggerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logEventToTriggerInAppMessage();

                Toast.makeText(MainActivity.this, "Evento acionado!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logEventToTriggerInAppMessage() {
        // Gatilho para fazer com que a mensagem apareça
        Bundle params = new Bundle();
        firebaseAnalytics.logEvent("evento_botao_clicado", params);
    }
}