package com.example.omar10ibanez.serviciosenfermeria.usuario;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.omar10ibanez.serviciosenfermeria.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class RegistroUser extends AppCompatActivity {
        Button buttonRegistro;
        EditText nombre,apellido,correo,contrasena,telefono;
        ImageView imagePhoto;
        private int request_code = 1;
        private Bitmap bitmap;
        ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);

        nombre = (EditText) findViewById(R.id.editText01);
        apellido = (EditText) findViewById(R.id.editText02);
        correo = (EditText) findViewById(R.id.editText03);
        contrasena = (EditText) findViewById(R.id.editText04);
        telefono = (EditText) findViewById(R.id.editText05);
        imagePhoto = (ImageView) findViewById(R.id.usuario_imagen_registro);
        buttonRegistro = (Button) findViewById(R.id.buttonRegistroUser);

        imagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                //verificacion de la version de plataforma
                if(Build.VERSION.SDK_INT < 19){
                    //android 4.3  y anteriores
                    i = new Intent();
                    i.setAction(Intent.ACTION_GET_CONTENT);
                }else {
                    //android 4.4 y superior
                    i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                }
                i.setType("image/*");
                startActivityForResult(i, request_code);
            }
        });




        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (!nombre.getText().toString().trim().equalsIgnoreCase("") ||
                        !apellido.getText().toString().trim().equalsIgnoreCase("") ||
                        !correo.getText().toString().trim().equalsIgnoreCase("") ||
                        !contrasena.getText().toString().trim().equalsIgnoreCase("") ||
                        !telefono.getText().toString().trim().equalsIgnoreCase(""))

                    if (telefono.length()>9 && telefono.length()==10 && contrasena.length()>4 && android.util.Patterns.EMAIL_ADDRESS.matcher(correo.getText().toString()).matches())


                        AgregarUser();

                    else if (telefono.length()<10)
                        Toast.makeText(RegistroUser.this, "Telefono es demasiado corto",
                                Toast.LENGTH_SHORT).show();
                    else  if (telefono.length()>=11)

                        Toast.makeText(RegistroUser.this, "Numero de telefono es demasiado extenso",
                                Toast.LENGTH_SHORT).show();
                    else if (contrasena.length()<=4)

                        Toast.makeText(RegistroUser.this, "La contraseña es muy corta",
                                Toast.LENGTH_SHORT).show();

                    else if( !android.util.Patterns.EMAIL_ADDRESS.matcher(correo.getText().toString()).matches()){
                        Toast.makeText(RegistroUser.this, "Formato de correo no valido",
                                Toast.LENGTH_SHORT).show();
                    }

                else

                Toast.makeText(RegistroUser.this, "Hay campos de texto vacíos",
                        Toast.LENGTH_SHORT).show();

            }
        });
                }

    private void AgregarUser() {

        progreso = new ProgressDialog(this);
        progreso.setMessage("Iniciando...");
        progreso.show();
            Log.i("Entro","llegue");
        OkHttpClient objeto = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("nombre", nombre.getText().toString())
                .add("apellido", apellido.getText().toString())
                .add("telefono",telefono.getText().toString())
                .add("contrasena",contrasena.getText().toString())
                .add("correo",correo.getText().toString())
                .add("foto",convertirImgString(bitmap))
                .build();

        String ip = getString( R.string.ip);
        Request request = new Request.Builder().url(ip+"/serviciosEnfermer%C3%ADa/php2/regUser.php").post(formBody).build();
        Call call = objeto.newCall(request);
        progreso.dismiss();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("error", "error en " + e);
            }

            @Override
            public void onResponse(Response response) throws IOException {

                Intent llevar = new Intent(RegistroUser.this, LoginUser.class);
                startActivity(llevar);
            }
        });
    }
    private String convertirImgString(Bitmap bitmap) {

        String imagenString;
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        if(bitmap!=null){
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
            byte[] imagenByte=array.toByteArray();
            imagenString= Base64.encodeToString(imagenByte,Base64.DEFAULT);
        }else{
            imagenString = "null"; //se enviara este string en caso de no haber imagen
        }

        return imagenString;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == request_code){
            imagePhoto.setImageURI(data.getData());

            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
                imagePhoto.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}






