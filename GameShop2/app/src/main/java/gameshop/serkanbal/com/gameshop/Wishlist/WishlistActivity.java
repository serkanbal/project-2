package gameshop.serkanbal.com.gameshop.Wishlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Cart.CartActivity2;
import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.R;

public class WishlistActivity extends AppCompatActivity {
    List<Game> mWishList;
    TextView mWishListSize;
    ImageView mBigHeart;
    RecyclerView mWishListRecyclerView;
    WishlistRecyclerAdapter mWishlistRecyclerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWishListSize = (TextView) findViewById(R.id.textWishlistSize);
        mBigHeart = (ImageView) findViewById(R.id.wishListBigHeart);

        AsyncTask<Void, Void, List<Game>> taskGetAllPossible = new AsyncTask<Void, Void, List<Game>>() {
            @Override
            protected List<Game> doInBackground(Void... voids) {
                List<Game> allPossible = Helper.getInstance(WishlistActivity.this).getAllGames();
                return allPossible;
            }

            @Override
            protected void onPostExecute(List<Game> games) {
                super.onPostExecute(games);
                SharedPreferences sharedPreferences = WishlistActivity.this.getSharedPreferences("wishList",
                        Context.MODE_PRIVATE);
                final List<Integer> wishListItems = new ArrayList<>();
                //Change hardcoded 17 to read from the size of the list!
                for (Integer i = 1; i < games.size() + 1; i++) {
                    if (sharedPreferences.getInt(i.toString(), -1) != -1) {
                        wishListItems.add((sharedPreferences.getInt(i.toString(), -1)));
                    }
                }

                AsyncTask<Void, Void, List<Game>> taskFillWishlistItems = new AsyncTask<Void, Void, List<Game>>() {
                    @Override
                    protected List<Game> doInBackground(Void... voids) {
                        List<Game> list = new ArrayList<>();
                        for (int i = 0; i < wishListItems.size(); i++) {
                            Game game = Helper.getInstance(WishlistActivity.this).getCartGameById(wishListItems.get(i));
                            list.add(game);
                        }
                        return list;
                    }

                    @Override
                    protected void onPostExecute(List<Game> games) {
                        super.onPostExecute(games);
                        mWishList = games;

                        if (mWishList.size() == 0) {
                            mWishListSize.setText("Wish List is empty");
                            mBigHeart.setVisibility(View.VISIBLE);
                        } else {
                            mWishListSize.setText("Number of items: " + mWishList.size());
                        }

                        //Setup the CartRecyclerView
                        mWishListRecyclerView = (RecyclerView) findViewById(R.id.wishlistrecyclerview);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WishlistActivity.this,
                                LinearLayoutManager.VERTICAL, false);
                        mWishListRecyclerView.setLayoutManager(linearLayoutManager);
                        mWishlistRecyclerAdapter = new WishlistRecyclerAdapter(mWishList, mWishListSize, mBigHeart);
                        ItemTouchHelper.Callback callback =
                                new WishlistItemTouchHelper(mWishlistRecyclerAdapter);
                        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                        touchHelper.attachToRecyclerView(mWishListRecyclerView);

                        mWishListRecyclerView.setAdapter(mWishlistRecyclerAdapter);
                    }
                };
            taskFillWishlistItems.execute();
            }
        };
        taskGetAllPossible.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_wishlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        } else if (item.getItemId() == R.id.action_goCart) {
            Intent intent = new Intent(this, CartActivity2.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);


    }
}
