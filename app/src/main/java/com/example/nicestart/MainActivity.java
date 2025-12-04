package com.example.nicestart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeLayout;
    private WebView miVisorWeb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        miVisorWeb = (WebView) findViewById(R.id.vistaweb);

        String html = "<html>"+
                "<head><style>"+
                "html, body { margin:0; padding:0; Height:100%; overflow:hidden }"+
                "img{ width:100%; height:100%; object-fit:cover; }"+
                "</style></head>"+
                "<body>"+
                "<img src = 'https://thispersondoesnotexist.com' />"+
                "</body></html>";


        miVisorWeb.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);

        // TEXTVIEW QUE TENDRÁ MENÚ CONTEXTUAL AL PULSARLO CON LONG-CLICK
        //TextView mycontext = findViewById(R.id.mytext);
        //registerForContextMenu(mycontext); // Activa menú contextual

        WebView mycontext = findViewById(R.id.vistaweb);
        registerForContextMenu(mycontext);

        // SwipeRefresh (gesto de arrastrar hacia abajo)
        swipeLayout = findViewById(R.id.swipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);
    }

    // Listener del gesto de recargar
    protected SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            final ConstraintLayout mLayout = findViewById(R.id.main);

            // Muestra mensaje tipo Snackbar
            Snackbar snackbar = Snackbar
                    .make(mLayout, "Page reset", Snackbar.LENGTH_SHORT)
                    .setAction("UNDO", new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Snackbar snackbar1 = Snackbar.make(mLayout, "Action restored", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });

            snackbar.show();

            // Oculta el icono de recarga
            miVisorWeb.reload();
            swipeLayout.setRefreshing(false);
        }
    };

    // Botón que intenta abrir el “perfil de contactos” (no es una activity real)
    public void openProfile(View v) {
        Intent intent = new Intent(MainActivity.this, ContactsContract.Profile.class);
        startActivity(intent);
    }

    // MENÚ CONTEXTUAL (press largo)
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item1) {
            Toast.makeText(this, "item copied", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.item2) {
            Toast.makeText(this, "item downloaded", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    // MENÚ DE LA APPBAR (arriba a la derecha)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.itemCopy) {
            Toast.makeText(this, "item copied", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.itemSettings) {
            Toast.makeText(this, "open settings", Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId() == R.id.itemExit){
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

            builder.setTitle("Quieres salir?");
            builder.setMessage("Muy importante y tal");
            //builder.setIcon(R.drawable."icono")

            builder.setPositiveButton("Sis", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }

            );

            builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }

            );

            builder.show();
        }

        return false;
    }

}
