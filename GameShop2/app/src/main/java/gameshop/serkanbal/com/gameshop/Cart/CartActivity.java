package gameshop.serkanbal.com.gameshop.Cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.R;

public class CartActivity extends AppCompatActivity {
    List<Game> mList;
    RecyclerView mCartRecyclerView;
    CartRecyclerAdapter mCartRecyclerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedPreferences sharedPreferences = this.getSharedPreferences("key_detailId",
                Context.MODE_PRIVATE);
        List<Integer> cartItems = new ArrayList<>();
        //Change hardcoded 17 to read from the size of the list!
        for (Integer i = 1; i < 17; i++) {
            if (sharedPreferences.getInt(i.toString(),-1) != -1) {
                cartItems.add((sharedPreferences.getInt(i.toString(),-1)));
            }
        }

        mList = new ArrayList<>();
        for (int i = 0; i < cartItems.size(); i++) {
            Game game = Helper.getInstance(this).getCartGameById(cartItems.get(i));
            mList.add(game);
        }



        //Setup the CartRecyclerView
        mCartRecyclerView  = (RecyclerView) findViewById(R.id.cartrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        mCartRecyclerView.setLayoutManager(linearLayoutManager);
        mCartRecyclerAdapter = new CartRecyclerAdapter(mList);
        ItemTouchHelper.Callback callback =
                new CartItemTouchHelper(mCartRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mCartRecyclerView);

        mCartRecyclerView.setAdapter(mCartRecyclerAdapter);


    }
}
