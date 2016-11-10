package gameshop.serkanbal.com.gameshop.Cart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.R;
import gameshop.serkanbal.com.gameshop.Wishlist.WishlistActivity;

public class CartActivity2 extends AppCompatActivity {
    List<Game> mList;
    RecyclerView mCartRecyclerView;
    CartRecyclerAdapter mCartRecyclerAdapter;
    TextView mCartTotal;
    FloatingActionButton mFab;
    ImageView mBigCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCartTotal = (TextView) findViewById(R.id.textWishlistSize);
        mFab = (FloatingActionButton) findViewById(R.id.cartFab);
        mBigCart = (ImageView) findViewById(R.id.cartBigCart);

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
            mBigCart.setVisibility(View.VISIBLE);
        } else {
            mCartTotal.setText("Cart total is: $" + getCartTotal().toString());
        }

        //Setup the CartRecyclerView
        mCartRecyclerView = (RecyclerView) findViewById(R.id.cartrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mCartRecyclerView.setLayoutManager(linearLayoutManager);
        mCartRecyclerAdapter = new CartRecyclerAdapter(mList, mCartTotal, mBigCart);
        ItemTouchHelper.Callback callback =
                new CartItemTouchHelper(mCartRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mCartRecyclerView);

        mCartRecyclerView.setAdapter(mCartRecyclerAdapter);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList.size() > 0) {
                    LayoutInflater layoutInflater = LayoutInflater.from(CartActivity2.this);
                    final View promptsView = layoutInflater.inflate(R.layout.dialoglist_layout, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            CartActivity2.this);

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);

                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("Thanks",
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
                    Snackbar.make(view, getString(R.string.cart_is_empty), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        } else if (item.getItemId() == R.id.action_goWishlist) {
            Intent intent = new Intent(this, WishlistActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
