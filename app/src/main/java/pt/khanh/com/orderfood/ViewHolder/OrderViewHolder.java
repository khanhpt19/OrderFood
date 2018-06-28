package pt.khanh.com.orderfood.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import pt.khanh.com.orderfood.Interface.ItemClickListener;
import pt.khanh.com.orderfood.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_order_id, txt_order_phone, txt_order_status, txt_order_address;
    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);
        txt_order_address = itemView.findViewById(R.id.txt_order_address);
        txt_order_id = itemView.findViewById(R.id.txt_order_id);
        txt_order_status = itemView.findViewById(R.id.txt_order_status);
        txt_order_phone = itemView.findViewById(R.id.txt_order_phone);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
//        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
