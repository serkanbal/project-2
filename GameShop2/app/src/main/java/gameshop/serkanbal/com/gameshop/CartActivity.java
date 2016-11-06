package gameshop.serkanbal.com.gameshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    List<Game> mList = new ArrayList<>();
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

        for (int i = 0; i < cartItems.size(); i++) {
            Game game = Helper.getInstance(this).getCartGameById(cartItems.get(i));
            mList.add(game);
        }

        //Setup the RecyclerView
        mCartRecyclerView  = (RecyclerView) findViewById(R.id.cartrecyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);

        mCartRecyclerView.setLayoutManager(linearLayoutManager);

        mCartRecyclerAdapter = new CartRecyclerAdapter(mList);
        mCartRecyclerView.setAdapter(mCartRecyclerAdapter);
    }

}
