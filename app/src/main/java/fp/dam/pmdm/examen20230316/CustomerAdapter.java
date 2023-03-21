package fp.dam.pmdm.examen20230316;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    List<Customer> customers;

    public CustomerAdapter(List<Customer> customers) {
        this.customers = customers;
    }



    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //metodo donde se crea la vista
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fragmento2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        //valores de la base de datos
        holder.bind(customers.get(position));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //crear tantas variables como haya en la clase Customer y declararlas en el constructor
        //ej private final TextView nombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //nombre= itemView.findviewbyid(r.id.nombre)
        }

        public void bind(Customer customer) {
            //usar las variables creadas para asignarle valores
            //nombre.setText(customers.getNombre());
        }
    }
}
