package com.example.parcialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcialapp.model.Grupos;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Socket socket;
    private Writer writer;
    private int x,y,r,g,b;
    private int[] color = new int[3];
    private EditText nombreGrupo,cantidadText,posXText,posYText;
    private Button azul, verde, rojo, crear, borrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r = 184; g = 15; b = 10;
        nombreGrupo =  findViewById(R.id.nombreGrupo);
        cantidadText = findViewById(R.id.cantidadText);
        posXText = findViewById(R.id.posXText);
        posYText = findViewById(R.id.posYText);
        azul = findViewById(R.id.buttonBlue);
        verde = findViewById(R.id.buttonGreen);
        rojo = findViewById(R.id.buttonRed);
        crear = findViewById(R.id.buttonCrear);
        borrar = findViewById(R.id.buttonBorrar);
        crear.setBackgroundColor(Color.DKGRAY);
        borrar.setBackgroundColor(Color.DKGRAY);

        //cambio de color
      rojo.setOnClickListener((v)->{
          r = 184; g = 15; b = 10;
      });

        azul.setOnClickListener((v)->{
            r = 13; g = 62; b = 105;
        });


        verde.setOnClickListener((v)->{
            r = 11; g = 102; b = 35;
        });

        borrar.setOnClickListener((v)->{
            sendMsg("Borrar");
        });

        crear.setOnClickListener((v)->{
        if(nombreGrupo.getText().toString().equals("")||cantidadText.getText().toString().equals("")
        ||posXText.getText().toString().equals("")||posYText.getText().toString().equals("")){
            Toast.makeText(this, "Llene todos los campos para continuar", Toast.LENGTH_SHORT).show();
        } else{
            Gson gson = new Gson();

            int cant =  Integer.parseInt(cantidadText.getText().toString());
            x =  Integer.parseInt(posXText.getText().toString());
            y =  Integer.parseInt(posYText.getText().toString());
            color[0]= r;
            color[1]= g;
            color[2]= b;

            Grupos grupos = new Grupos(nombreGrupo.getText().toString(), cant, x,y,color);

            String json = gson.toJson(grupos);
            sendMsg(json);
        }
        });

        serveriniciar();
    }

    public void serveriniciar() {
        new Thread(
                ()->{
                    try {
                        //cambiar aqui la direccion ip
                        socket = new Socket("10.0.2.2",5000);
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        writer = new BufferedWriter(osw);


                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }).start();
    }

    public void sendMsg(String msg){
        new Thread(()->{
            try {
                writer.write(msg+"\n");
                writer.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }
}