package com.example.shopapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView listProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        listProducts = findViewById(R.id.list_products);
        listProducts.setLayoutManager(new LinearLayoutManager(this));

        getProducts();


    }

    private void getProducts() {

        class GetProducts extends AsyncTask<Void, Void, List<Product>> {

            @Override
            protected List<Product> doInBackground(Void... voids) {
                List<Product> productList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .productDao()
                        .getAllProducts();
                return productList;
            }

            @Override
            protected void onPostExecute(List<Product> tasks) {
                super.onPostExecute(tasks);
                ProductAdapter adapter = new ProductAdapter(ProductListActivity.this, tasks);
                listProducts.setAdapter(adapter);
            }
        }

        GetProducts gt = new GetProducts();
        gt.execute();
    }


}