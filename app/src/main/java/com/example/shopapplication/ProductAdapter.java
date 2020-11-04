package com.example.shopapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.InvalidationTracker;

import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductsViewHolder> {


    public ProductAdapter(MainActivity mainActivity, MainActivity mainActivity1) {
    }

    public interface OnDeleteClickListener{
        void OnDeleteClickListener(Product product);
    }

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;

    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductsViewHolder holder, int position) {
        Product product = productList.get(position);

        String amount = String.valueOf(product.getPrice() * product.getQuantity());

        holder.textProduct.setText(product.getProduct());
        holder.textAmount.setText("₹" + amount);
        holder.textDateTime.setText(product.getDateTime());

//        holder.btDelet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Product d = productList.get(holder.getAdapterPosition());
//                appDatabase
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {

        TextView textProduct, textAmount, textDateTime;
        ImageView btDelet;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            textProduct = itemView.findViewById(R.id.text_product);
            textAmount = itemView.findViewById(R.id.text_amount);
            textDateTime = itemView.findViewById(R.id.text_date);
            btDelet = itemView.findViewById(R.id.bt_delet);
            btDelet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Product product = new Product();
                    int ID=productList.get(getAdapterPosition()).getId();
                    product.setId(ID);
                    MainActivity.appDatabase.productDao().deleteProduct(product);
                    new ProductListActivity();
                }
            });

        }


    }


}
