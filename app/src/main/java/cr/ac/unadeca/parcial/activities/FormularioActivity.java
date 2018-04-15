package cr.ac.unadeca.parcial.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.unadeca.parcial.R;
import cr.ac.unadeca.parcial.database.models.ParcialTable;

/**
 * Created by pc on 28/2/2018.
 */

public class FormularioActivity extends AppCompatActivity {
    private TextView lblnombre_apellido, lbldepartamento, lblcedula, lblcodigo, lbltelefono;
    private EditText txtnombre_apellido, txtdepartamento, txtcedula, txtcodigo, txttelefono;
    private Button btnguardar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);
        lblnombre_apellido=findViewById(R.id.lblNombre_Apellido);
        lbldepartamento=findViewById(R.id.lblDepartamento);
        lblcedula=findViewById(R.id.lblCedula);
        lblcodigo=findViewById(R.id.lblCodigo);
        lbltelefono=findViewById(R.id.lblTelefono);
        txtnombre_apellido=findViewById(R.id.txtNombre_Apellido);
        txtdepartamento=findViewById(R.id.txtDepartamento);
        txtcedula=findViewById(R.id.txtCedula);
        txtcodigo=findViewById(R.id.txtCodigo);
        txttelefono=findViewById(R.id.txtTelefono);
        btnguardar =findViewById(R.id.btnGuardar);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardar();
            }
        });

    }
    private boolean validacion() {
        boolean send = true;
        if (txtnombre_apellido.getText().toString().isEmpty()) {
            send = false;
        }
        if (txtdepartamento.getText().toString().isEmpty()) {
            send = false;
        }
        if (txtcodigo.getText().toString().isEmpty()) {
            send = false;
        }
        if (txtcedula.getText().toString().isEmpty()) {
            send = false;
        }
        if (txttelefono.getText().toString().isEmpty()) {
            send = false;
        }
        return send;
    }
    private void guardar() {
        if (validacion()) {
            //para guardar informacion
            ParcialTable registro = new ParcialTable();
            registro.Nombre_Apellido = txtnombre_apellido.getText().toString();
            registro.Departamento = txtdepartamento.getText().toString();
            registro.Cedula = txtcedula.getText().toString();
            registro.Codigo = txtcodigo.getText().toString();
            registro.Telefono = txttelefono.getText().toString();
            Toast.makeText(this, getResources().getString(R.string.succes_valid), Toast.LENGTH_LONG).show();
            registro.save();
            finish();
        } else {
            //para mensajes de error segun la validacion
            Toast.makeText(this, getResources().getString(R.string.error_valid), Toast.LENGTH_LONG).show();
        }
    }
}
