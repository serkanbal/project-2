package gameshop.serkanbal.com.gameshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    List<Game> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedPreferences sharedPreferences = this.getSharedPreferences("key_detailId",
                Context.MODE_PRIVATE);

        List<Integer> cartItems = new ArrayList<>();
        for (Integer i = 1; i < 17; i++) {
            if (sharedPreferences.getInt(i.toString(),-1) != -1) {
                cartItems.add((sharedPreferences.getInt(i.toString(),-1)));
            }
        }

        for (int i = 0; i < cartItems.size(); i++) {
            Game game = Helper.getInstance(this).getCartGameById(cartItems.get(i));
            mList.add(game);
        }
    }
    
}
