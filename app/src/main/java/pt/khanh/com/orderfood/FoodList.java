package pt.khanh.com.orderfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import pt.khanh.com.orderfood.Interface.ItemClickListener;
import pt.khanh.com.orderfood.Model.Food;
import pt.khanh.com.orderfood.ViewHolder.FoodViewHolder;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView_food;
    FirebaseDatabase database;
    DatabaseReference food;
    RecyclerView.LayoutManager layoutManager;
    String categoryId;
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        database = FirebaseDatabase.getInstance();
        food = database.getReference("Foods");

        recyclerView_food = findViewById(R.id.recycleview_food);
        recyclerView_food.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView_food.setLayoutManager(layoutManager);

        //Get Intent
        if(getIntent() != null){
            categoryId = getIntent().getStringExtra("CategoryId");
        }
        if(!categoryId.isEmpty() && categoryId != null){
            loadFoodList(categoryId);
        }
    }

    private void loadFoodList(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                food.orderByChild("MenuId").equalTo(categoryId) ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.food_image);
                final  Food foodSelected= model;

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View v, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this, ""+foodSelected.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        recyclerView_food.setAdapter(adapter);
    }
}
