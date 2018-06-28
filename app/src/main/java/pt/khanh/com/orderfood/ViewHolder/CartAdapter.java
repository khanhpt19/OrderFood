package pt.khanh.com.orderfood.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pt.khanh.com.orderfood.Interface.ItemClickListener;
import pt.khanh.com.orderfood.Model.Order;
import pt.khanh.com.orderfood.R;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txt_cart_item_name, txt_cart_item_price;
    public ImageView img_cart_item_count;
    public ItemClickListener itemClickListener;

    public void setTxt_cart_item_name(TextView txt_cart_item_name) {
        this.txt_cart_item_name = txt_cart_item_name;
    }

    public CartViewHolder(View itemView) {
        super(itemView);
        txt_cart_item_name = itemView.findViewById(R.id.txt_cart_item_name);
        txt_cart_item_price = itemView.findViewById(R.id.txt_cart_item_price);
        img_cart_item_count = itemView.findViewById(R.id.img_cart_item_count);
    }

    @Override
    public void onClick(View view) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + listData.get(position).getQuantity(), Color.RED);
        holder.img_cart_item_count.setImageDrawable(drawable);

        Locale locale = new Locale("en", "US");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        int price = (Integer.parseInt(listData.get(position).getPrice())) * (Integer.parseInt(listData.get(position).getQuantity()));
        holder.txt_cart_item_price.setText(numberFormat.format(price));
        holder.txt_cart_item_name.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
