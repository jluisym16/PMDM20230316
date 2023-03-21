package fp.dam.pmdm.examen20230316;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    CustomerAdapter adapter;
    ArrayList<Customer> customerArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("ClassicModelsV2/customers/103/customerName");
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button cons1= findViewById(R.id.cons1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button cons2 = findViewById(R.id.cons2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button gps = findViewById(R.id.gpsbut);
        TextView textView = findViewById(R.id.textView);






        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView.setText(snapshot.getValue().toString());
                if(snapshot.exists()){
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String texto= ds.child("customers").getValue().toString();
                        customerArrayList.add(new Customer(texto));
                    }
                    adapter= new CustomerAdapter(customerArrayList);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}