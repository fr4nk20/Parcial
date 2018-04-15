package cr.ac.unadeca.parcial.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.parcial.database.ParcialDatabase;

/**
 * Created by pc on 28/2/2018.
 */
@Table(database = ParcialDatabase.class)
public class ParcialTable extends BaseModel{
@PrimaryKey(autoincrement = true)
    public long id;
@Column
    public String Nombre_Apellido;
@Column
    public String Departamento;
@Column
    public String Cedula;
@Column
    public String Codigo;
@Column
    public String Telefono;

}
