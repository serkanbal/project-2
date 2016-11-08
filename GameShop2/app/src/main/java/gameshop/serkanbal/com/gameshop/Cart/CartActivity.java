package gameshop.serkanbal.com.gameshop.Cart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.R;

public class CartActivity extends AppCompatActivity {
    List<Game> mList;
    RecyclerView mCartRecyclerView;
    CartRecyclerAdapter mCartRecyclerAdapter;
    TextView mCartTotal;
    FloatingActionButton mFab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mCartTotal = (TextView) findViewById(R.id.textCartTotal);
        mFab = (FloatingActionButton) findViewById(R.id.cartFab);

        SharedPreferences sharedPreferences = this.getSharedPreferences("key_detailId",
                Context.MODE_PRIVATE);
        List<Integer> cartItems = new ArrayList<>();
        List<Game> allPossible = Helper.getInstance(this).getAllGames();
        //Change hardcoded 17 to read from the size of the list!
        for (Integer i = 1; i < allPossible.size() + 1; i++) {
            if (sharedPreferences.getInt(i.toString(), -1) != -1) {
                cartItems.add((sharedPreferences.getInt(i.toString(), -1)));
            }
        }

        mList = new ArrayList<>();
        for (int i = 0; i < cartItems.size(); i++) {
            Game game = Helper.getInstance(this).getCartGameById(cartItems.get(i));
            mList.add(game);
        }


        if (mList.size() == 0) {
            mCartTotal.setText("Cart is empty");
        } else {
            mCartTotal.setText("Cart total is: $" + getCartTotal().toString());
        }

        //Setup the CartRecyclerView
        mCartRecyclerView = (RecyclerView) findViewById(R.id.cartrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mCartRecyclerView.setLayoutManager(linearLayoutManager);
        mCartRecyclerAdapter = new CartRecyclerAdapter(mList, mCartTotal);
        ItemTouchHelper.Callback callback =
                new CartItemTouchHelper(mCartRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mCartRecyclerView);

        mCartRecyclerView.setAdapter(mCartRecyclerAdapter);

            mFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mList.size() > 0) {
                        LayoutInflater layoutInflater = LayoutInflater.from(CartActivity.this);
                        final View promptsView = layoutInflater.inflate(R.layout.dialoglist_layout, null);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                CartActivity.this);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);

                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                mCartRecyclerAdapter.checkOut();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                    } else {
                        Toast.makeText(CartActivity.this, "Your cart is empty!\n" +
                                "Add a product to your cart before checking out.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    public Double getCartTotal() {
        Double sum = 0d;
        for (int i = 0; i < mList.size(); i++) {
            sum = mList.get(i).getPrice() + sum;
        }
        Double c = Math.round(sum * 100.0) / 100.0;
        return c;
    }
}
