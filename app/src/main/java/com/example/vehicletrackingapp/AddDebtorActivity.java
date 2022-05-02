package com.example.vehicletrackingapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDebtorActivity extends AppCompatActivity {
    EditText name, contact, vrn, litres, cost;
    Button addDebtor, delete, update, viewDebtors;
    DBHelper2 DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debtor);
        name =(EditText)findViewById(R.id.edtName);
        contact =(EditText)findViewById(R.id.edtContact);
        vrn =(EditText)findViewById(R.id.edtVRN);
        litres =(EditText)findViewById(R.id.edtLitres);
        cost =(EditText)findViewById(R.id.edtCost);

        addDebtor =(Button)findViewById(R.id.btnAdddebtor);
        delete =(Button)findViewById(R.id.btnDelete);
        update =(Button)findViewById(R.id.btnUpdate);
        viewDebtors =(Button)findViewById(R.id.btnView);

        DB =new DBHelper2(this);
        addDebtor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String VRNTXT = vrn.getText().toString();
                String litresTXT = litres.getText().toString();
                String costTXT = cost.getText().toString();

                Boolean checkaddeddata= DB.addDebtorToList(nameTXT, contactTXT, VRNTXT, litresTXT, costTXT);
                if(checkaddeddata==true && !name.equals("")){
                    Toast.makeText(AddDebtorActivity.this,"New Debtor Added",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    contact.setText("");
                    vrn.setText("");
                    litres.setText("");
                    cost.setText("");
                }else
                    Toast.makeText(AddDebtorActivity.this,"New Debtor Not Added",Toast.LENGTH_SHORT).show();
                name.setText("");
                contact.setText("");
                vrn.setText("");
                litres.setText("");
                cost.setText("");

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String VRNTXT = vrn.getText().toString();
                Boolean deletedata = DB.deleteFromList(VRNTXT);
                if(deletedata==true){
                    Toast.makeText(AddDebtorActivity.this, "Debtor Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddDebtorActivity.this,"Debtor Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String VRNTXT = vrn.getText().toString();
                String litresTXT = litres.getText().toString();
                String costTXT = cost.getText().toString();
                Boolean checkupdatelist= DB.updateList(nameTXT, contactTXT, VRNTXT, litresTXT, costTXT);
                if(checkupdatelist==true)
                    Toast.makeText(AddDebtorActivity.this,"Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddDebtorActivity.this,"Entry Not Updated", Toast.LENGTH_SHORT).show();

            }
        });
        viewDebtors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = DB.getdata();
                if(cur.getCount()==0){
                    Toast.makeText(AddDebtorActivity.this,"No List Exists",Toast.LENGTH_SHORT).show();
                    return;

            }
                StringBuffer buffer = new StringBuffer();
                int i;
                while(cur.moveToNext()){
                    buffer.append("name"+cur.getString(0) + "\n");
                    buffer.append("contact"+cur.getString(1) + "\n");
                    buffer.append("VRN"+cur.getString(2) + "\n");
                    buffer.append("litres"+cur.getString(3) + "\n");
                    buffer.append("cost"+cur.getString(4) + "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDebtorActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Debtors");
                builder.setMessage(buffer.toString());
                builder.show();

                }
        });

        }
}