package com.example.shopapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputProduct;
    private EditText inputProduct;
    private TextInputLayout textInputPrice;
    private EditText inputPrice;
    private TextInputLayout textInputQuantity;
    private EditText inputQuantity;
    private TextInputLayout textInputAmount;
    private EditText inputAmount;
    private Button buttonAdd;
    private Button buttonShowList;


   static AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarChartActivity.class));

            }
        });


        appDatabase= Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "mydb").allowMainThreadQueries().build();

        textInputProduct = findViewById(R.id.text_input_product);
        inputProduct = findViewById(R.id.input_product);
        textInputPrice = findViewById(R.id.text_input_price);
        inputPrice = findViewById(R.id.input_price);
        textInputQuantity = findViewById(R.id.text_input_quantity);
        inputQuantity = findViewById(R.id.input_quantity);
        textInputAmount = findViewById(R.id.text_input_amount);
        inputAmount = findViewById(R.id.input_amount);
        buttonAdd = findViewById(R.id.button_add);
        buttonShowList = findViewById(R.id.button_show_list);

        inputPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        inputQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateAmount();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonAdd.setOnClickListener(this);
        buttonShowList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_add) {
            addProduct();
        } else if (view.getId() == R.id.button_show_list) {
            Intent intent = new Intent(MainActivity.this, ProductListActivity.class);
            startActivity(intent);
        }
    }

    private void calculateAmount() {
        if (!inputQuantity.getText().toString().isEmpty() && !inputPrice.getText()
                .toString().isEmpty()) {
            double amount = Integer.parseInt(inputQuantity.getText().toString()) * Double
                    .parseDouble(inputPrice.getText().toString());
            inputAmount.setText(String.valueOf(amount));
        } else {
            inputAmount.getText().clear();
        }
    }

    private void addProduct() {
        final String productName = inputProduct.getText().toString().trim();
        final String price = inputPrice.getText().toString().trim();
        final String quantity = inputQuantity.getText().toString().trim();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a, dd-MMM-yyyy");
        LocalDateTime now = LocalDateTime.now();
        final String dateTime = dtf.format(now);

        if (productName.isEmpty()) {
            Toast.makeText(this, "Please add Product!", Toast.LENGTH_SHORT).show();
            inputProduct.requestFocus();
            return;
        }

        if (price.isEmpty()) {
            Toast.makeText(this, "Please add Price!", Toast.LENGTH_SHORT).show();
            inputPrice.requestFocus();
            return;
        }

        if (quantity.isEmpty()) {
            Toast.makeText(this, "Please add Quantity!", Toast.LENGTH_SHORT).show();
            inputQuantity.requestFocus();
            return;
        }

        class SaveProduct extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Product product = new Product();
                product.setProduct(productName);
                product.setPrice(Double.parseDouble(price));
                product.setQuantity(Integer.parseInt(quantity));
                product.setDateTime(dateTime);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().productDao().insert(product);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                inputProduct.setText("");
                inputAmount.setText("");
                inputPrice.setText("");
                inputQuantity.setText("");
            }
        }

        SaveProduct st = new SaveProduct();
        st.execute();

    }
}