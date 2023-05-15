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

        //Carga de los archivos para la lista de reproduccion
        Carga_Reproduccion();
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

    //Metodo Pausar
    public void Stop(View vista){
        //Estructura condicional
        if (vectorMP[posicion] != null){
            //Dado la situacion al preguntar por esto el vector se vacia la lista de reproduccion
            //Por lo cual una vez detenido habra que cargar la lista de reproduccion de vuelta
            vectorMP[posicion].stop();
            //Cargamos la lista de reproduccion
            Carga_Reproduccion();
            //Regresamos el indice a la posicion 0
            posicion = 0;
            //Cambiamos la imagen del boton
            btn_play_pause.setBackgroundResource(R.drawable.reproducir);
            //Cambiamos la portada de la primaera cancion
            iv.setImageResource(R.drawable.portada1);
            //Mensaje para e usuario
            Toast.makeText(this,"La Reproduccion se ha detenido", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para la repeticion de una pista
    public void Repetir(View vista){
        //Si activamos el boton repetir
       if (repetir == 1){
           //cambiamos la imagen del boton
           btn_repeat.setBackgroundResource(R.drawable.repetir);
           //Mandamos un mensaje al usuario
           Toast.makeText(this,"No repetir",Toast.LENGTH_SHORT).show();
           //utilizamos el metodo repetir de la clase MediaPlayer
           vectorMP[posicion].setLooping(false);
           //Se cambia el valor de la variable
           repetir = 2;
       }else{
           //cambiamos la imagen del boton
           btn_repeat.setBackgroundResource(R.drawable.no_repetir);
           //Mandamos un mensaje al usuario
           Toast.makeText(this,"Repetir",Toast.LENGTH_SHORT).show();
           //utilizamos el metodo repetir de la clase MediaPlayer
           vectorMP[posicion].setLooping(true);
           //Se cambia el valor de la variable para que cuando se presione el boton entre en el if
           repetir = 1;
       }
    }

    //Metodo para el boton Siguiente
    public void Siguiente(View vista){
        //en este caso nos preguntamos si el indice es menor a la pista actual
        if (posicion < vectorMP.length -1){
            //nos fijamos si hay un archivo reproduciendo
            if(vectorMP[posicion].isPlaying()){
                //De ser asi lo detenemos
                vectorMP[posicion].stop();
                //Incrementamos la posicion osea el indice selector del vector
                posicion++;
                //Comenzamos la reproduccion del archivo
                vectorMP[posicion].start();

                //Cambiamos las imagenes
                ImagenPortada(vista, posicion);

            }else {
                //Incrementamos la posicion osea el indice selector del vector
                posicion++;
                //Comenzamos la reproduccion del archivo
                vectorMP[posicion].start();

                //Cambiamos las imagenes
                ImagenPortada(vista, posicion);
            }
        }else{
            //Mensaje para el usuario
            Toast.makeText(this,"Se acabo la list de reproducion", Toast.LENGTH_SHORT).show();
        }
    }

    public void Retroceder(View vista){
        //En este caso hacemos lo opuesto a lo anterior
        if (posicion > 0 ){
            //nos fijamos si hay un archivo reproduciendo
            if(vectorMP[posicion].isPlaying()){
                //De ser asi lo detenemos
                vectorMP[posicion].stop();
                //Carga de la listade reproduccion
                Carga_Reproduccion();

                //Decrementamos la posicion osea el indice selector del vector
                posicion--;
                //Comenzamos la reproduccion del archivo
                vectorMP[posicion].start();

                //Cambiamos las imagenes
                ImagenPortada(vista, posicion);

            }else {
                //Decrementamos la posicion osea el indice selector del vector
                posicion--;
                //Comenzamos la reproduccion del archivo
                vectorMP[posicion].start();

                //Cambiamos las imagenes
                ImagenPortada(vista, posicion);
            }
        }else{
            //Mensaje para el usuario
            Toast.makeText(this,"No hay mas archivos de reproduccion", Toast.LENGTH_SHORT).show();
        }
    }

    private void ImagenPortada(View vista, int a){
        if (a == 0) iv.setImageResource(R.drawable.portada1);
        if (a == 1) iv.setImageResource(R.drawable.portada2);
        if (a == 2) iv.setImageResource(R.drawable.portada3);
        if (a == 3) iv.setImageResource(R.drawable.portada4);
    }

    //Carga de temas en la lista de reproduccion
    private void Carga_Reproduccion(){
        //Ahora cargamos la lista de reproducion con el vector MediaPlayer
        vectorMP[0] = MediaPlayer.create(this,R.raw.tema);
        vectorMP[1] = MediaPlayer.create(this,R.raw.temazo);
        vectorMP[2] = MediaPlayer.create(this,R.raw.temon);
        vectorMP[3] = MediaPlayer.create(this,R.raw.tranqui);
    }
}