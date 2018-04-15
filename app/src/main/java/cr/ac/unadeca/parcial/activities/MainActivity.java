package cr.ac.unadeca.parcial.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

import cr.ac.unadeca.parcial.R;
import cr.ac.unadeca.parcial.database.models.ParcialTable;
import cr.ac.unadeca.parcial.subclase.ParcialViewHolder;


public class MainActivity extends AppCompatActivity {
    private static Context QuickContext;
    private RecyclerView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuickContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividad = new Intent(getApplicationContext(), FormularioActivity.class);
                getApplicationContext().startActivity(actividad);
            }
        });
        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        List<ParcialTable>info =SQLite.select().from(ParcialTable.class).queryList();
        lista.setAdapter(new ParcialAdapter(info));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            List<ParcialTable> info = SQLite.select().from(ParcialTable.class).queryList();
            lista.setAdapter(new ParcialAdapter(info));
        }
        return super.onOptionsItemSelected(item);
    }
    public static class ParcialAdapter extends RecyclerView.Adapter<ParcialViewHolder> {
        private final List<ParcialTable> listParcialTable;
        private final LayoutInflater inflater;

        public ParcialAdapter(List<ParcialTable> listToDoTables) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listParcialTable = listToDoTables;
        }

        @Override
        public ParcialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.objecto, parent, false);
            return new ParcialViewHolder(view);
        }

        public void animateTo(List<ParcialTable> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<ParcialTable> newModels) {
            for (int i = listParcialTable.size() - 1; i >= 0; i--) {
                final ParcialTable model = listParcialTable.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<ParcialTable> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final ParcialTable model = newModels.get(i);
                if (!listParcialTable.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<ParcialTable> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final ParcialTable model = newModels.get(toPosition);
                final int fromPosition = listParcialTable.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public ParcialTable removeItem(int position) {
            final ParcialTable model = listParcialTable.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, ParcialTable model) {
            listParcialTable.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final ParcialTable model = listParcialTable.remove(fromPosition);
            listParcialTable.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }
        @Override
        public void onBindViewHolder(final ParcialViewHolder holder, final int position) {
            final ParcialTable current = listParcialTable.get(position);
            holder.html.setHtml(ActividadAString(current),
                    new HtmlResImageGetter(holder.html));
            holder.html.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });
            holder.borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current.delete();
                    removeItem(position);
                    notifyDataSetChanged();
                }
            });
        }
        private  String ActividadAString(ParcialTable todo){
            String html= "<a><big><b> <font color =\""+"\">"+"Nombre: "+todo.Nombre_Apellido+"</b></big>";
            html+="<br><big><b>"+"Cédula: "+"</big>"+ todo.Cedula + "</b>";
            html+="<br><big><b>"+"Codigo: "+"</big>"+todo.Codigo+"</b>";
            html+="<br><big><b>"+"Departamento: "+"</big>"+todo.Departamento+"</b>";
            html+="<br><big><b>"+"Telefono: "+"</big>"+todo.Telefono+"</b></a>";
            return html;
        }
        @Override
        public int getItemCount() {
            return listParcialTable.size();
        }

    }
}
