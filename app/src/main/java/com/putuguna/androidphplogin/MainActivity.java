package com.putuguna.androidphplogin;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.putuguna.androidphplogin.apiservices.ApiService;
import com.putuguna.androidphplogin.clients.ApiClient;
import com.putuguna.androidphplogin.models.InsertFoodResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnInsert;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = (Button) findViewById(R.id.button_insert_food);

        progressDialog = new ProgressDialog(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupInsertFood();
            }
        });
    }

    /**
     * this method used to open popup
     */
    private void popupInsertFood(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_insert_food,null);
        builder.setView(view);

        final EditText etFoodName = (EditText) view.findViewById(R.id.edit_text_food_name);
        final EditText etFoodQty = (EditText) view.findViewById(R.id.edit_text_food_quantity);

        progressDialog.setTitle("Inserting");
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();

        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                String foodName = etFoodName.getText().toString();
                String foodQty = etFoodQty.getText().toString();

                if(TextUtils.isEmpty(foodName)){
                    Toast.makeText(MainActivity.this, "Food Name is required", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(foodQty)){
                    Toast.makeText(MainActivity.this, "Food Quantity is required", Toast.LENGTH_SHORT).show();
                }else{
                    insertData(foodName,foodQty);
                }
            }
        });

        builder.show();
    }

    /**
     * this method used to send data to server or our local server
     * @param foodName
     * @param foodQty
     */
    private void insertData(String foodName, String foodQty){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<InsertFoodResponseModel> call = apiService.insertFood(foodName, foodQty);
        call.enqueue(new Callback<InsertFoodResponseModel>() {
            @Override
            public void onResponse(Call<InsertFoodResponseModel> call, Response<InsertFoodResponseModel> response) {

                InsertFoodResponseModel insertFoodResponseModel = response.body();

                //check the status code
                if(insertFoodResponseModel.getStatus()==1){
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InsertFoodResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}
