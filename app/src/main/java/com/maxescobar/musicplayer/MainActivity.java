package com.maxescobar.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_play_pause, btn_repeat;
    MediaPlayer mp;
    ImageView iv;
    int repetir = 2, posicion = 0;

    MediaPlayer vectorMP [] = new MediaPlayer[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bueno asignamos lo de siempre
        btn_play_pause = (Button) findViewById(R.id.btnPlay);
        btn_repeat = (Button) findViewById(R.id.btnRepeat);
        iv = (ImageView) findViewById(R.id.imgV);

        //Ahora cargamos la lista de reproducion con el vector MediaPlayer
        vectorMP[0] = MediaPlayer.create(this,R.raw.tema);
        vectorMP[1] = MediaPlayer.create(this,R.raw.temazo);
        vectorMP[2] = MediaPlayer.create(this,R.raw.temon);
        vectorMP[3] = MediaPlayer.create(this,R.raw.tranqui);
    }

    //Boton Play_pause
    public void PlayPause(View vista){
        //Si la pista de audio se encuetra reproducion
        if (vectorMP[posicion].isPlaying()){
            //Pausamos la cancion
            vectorMP[posicion].pause();
            //Cambiamos la imagen del boton
            btn_play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this,"Pausa",Toast.LENGTH_SHORT).show();
        }else{
            //Pausamos la cancion
            vectorMP[posicion].start();
            //Cambiamos la imagen del boton
            btn_play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this,"Reproduciendo",Toast.LENGTH_SHORT).show();
        }
    }
}