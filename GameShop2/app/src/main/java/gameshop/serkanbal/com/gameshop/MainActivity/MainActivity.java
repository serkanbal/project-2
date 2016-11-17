package gameshop.serkanbal.com.gameshop.MainActivity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gameshop.serkanbal.com.gameshop.Cart.CartActivity2;
import gameshop.serkanbal.com.gameshop.Data.AssetHelper;
import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.DetailActivity.DetailActivity;
import gameshop.serkanbal.com.gameshop.DetailActivity.DetailFragment;
import gameshop.serkanbal.com.gameshop.R;
import gameshop.serkanbal.com.gameshop.Wishlist.WishlistActivity;

public class MainActivity extends AppCompatActivity
        implements DetailFragment.OnFragmentInteractionListener, GameRecyclerAdapter.OnGameSelectedListener{
    RecyclerView mRecyclerView;
    GameRecyclerAdapter mGameRecyclerAdapter;
    ImageView mFilterButton;
    String mSearchQuery = "";
    TextView mResultSize;
    SearchView mSearcView;
    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.detail_fragment_container) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }

        mFilterButton = (ImageView) findViewById(R.id.filterbutton);
        mResultSize = (TextView) findViewById(R.id.resultSize);

        //DB Initialize
        AssetHelper dbSetup = new AssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        //Async
        AsyncTask<Void, Void, List<Game>> task = new AsyncTask<Void, Void, List<Game>>() {
            @Override
            protected List<Game> doInBackground(Void... voids) {
                List<Game> allGames = Helper.getInstance(MainActivity.this).getAllGames();
                return allGames;
            }

            @Override
            protected void onPostExecute(List<Game> games) {
                super.onPostExecute(games);
                mGameRecyclerAdapter = new GameRecyclerAdapter(games, MainActivity.this);
                mRecyclerView.setAdapter(mGameRecyclerAdapter);
                mResultSize.setText("Number of Items: " + resultSize(games));
            }
        };
        task.execute();

        //Setup the RecyclerView
        mRecyclerView  = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Context Menu
        registerForContextMenu(mFilterButton);
        mFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContextMenu(mFilterButton);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_goCart:
                Intent intent = new Intent(MainActivity.this, CartActivity2.class);
                startActivity(intent);
                return true;
            case R.id.action_goWishlist:
                Intent intent2 = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_price:
                if (!mSearchQuery.equals("")) {
                    AsyncTask<String, Void, List<Game>> taskitemSearchForNameOrTypeFilterByPrice = new AsyncTask<String, Void, List<Game>>() {
                        @Override
                        protected List<Game> doInBackground(String... strings) {
                            List<Game> filteredByPrice = Helper.getInstance(MainActivity.this).
                                    itemSearchForNameOrTypeFilterByPrice(strings[0]);
                            return filteredByPrice;
                        }

                        @Override
                        protected void onPostExecute(List<Game> games) {
                            super.onPostExecute(games);
                            mGameRecyclerAdapter.replaceData(games);
                            mResultSize.setText("Number of Items: " + resultSize(games));
                        }
                    };
                taskitemSearchForNameOrTypeFilterByPrice.execute(mSearchQuery);

                } else {
                    AsyncTask<Void, Void, List<Game>> taskAllFilteredByPrice = new AsyncTask<Void, Void, List<Game>>() {
                        @Override
                        protected List<Game> doInBackground(Void... voids) {
                            List<Game> allFilteredByPrice = Helper.getInstance(MainActivity.this).getAllGamesFilteredByPrice();
                            return allFilteredByPrice;
                        }

                        @Override
                        protected void onPostExecute(List<Game> games) {
                            super.onPostExecute(games);
                            mGameRecyclerAdapter.replaceData(games);
                            mResultSize.setText("Number of Items: " + resultSize(games));
                        }
                    };
                    taskAllFilteredByPrice.execute();
                }
                return true;
            case R.id.filter_platform:
                if (!mSearchQuery.equals("")) {
                    AsyncTask<String, Void, List<Game>> taskFilteredByPlatform = new AsyncTask<String, Void, List<Game>>() {
                        @Override
                        protected List<Game> doInBackground(String... strings) {
                            List<Game> filteredByPlatform = Helper.getInstance(MainActivity.this).
                                    itemSearchForNameOrTypeFilterByPlatform(strings[0]);
                            return filteredByPlatform;
                        }

                        @Override
                        protected void onPostExecute(List<Game> games) {
                            super.onPostExecute(games);
                            mGameRecyclerAdapter.replaceData(games);
                            mResultSize.setText("Number of Items: " + resultSize(games));
                        }
                    };
                    taskFilteredByPlatform.execute(mSearchQuery);
                } else {
                    AsyncTask<Void, Void, List<Game>> taskAllFilteredByPlatform = new AsyncTask<Void, Void, List<Game>>() {
                        @Override
                        protected List<Game> doInBackground(Void... voids) {
                            List<Game> allFilteredByPlatform = Helper.getInstance(MainActivity.this).getAllGamesFilteredByPlatform();
                            return allFilteredByPlatform;
                        }

                        @Override
                        protected void onPostExecute(List<Game> games) {
                            super.onPostExecute(games);
                            mGameRecyclerAdapter.replaceData(games);
                            mResultSize.setText("Number of Items: " + resultSize(games));
                        }
                    };
                taskAllFilteredByPlatform.execute();
                }
                return true;
            case R.id.filter_rating:
                if (!mSearchQuery.equals("")) {
                    AsyncTask<String, Void, List<Game>> taskFilteredByRating = new AsyncTask<String, Void, List<Game>>() {
                        @Override
                        protected List<Game> doInBackground(String... strings) {
                            List<Game> filteredByRating = Helper.getInstance(MainActivity.this).
                                    itemSearchForNameOrTypeFilterByRating(strings[0]);
                            return filteredByRating;
                        }

                        @Override
                        protected void onPostExecute(List<Game> games) {
                            super.onPostExecute(games);
                            mGameRecyclerAdapter.replaceData(games);
                            mResultSize.setText("Number of Items: " + resultSize(games));
                        }
                    };
                    taskFilteredByRating.execute(mSearchQuery);
                } else {
                    AsyncTask<Void, Void, List<Game>> taskAllFilteredByRating = new AsyncTask<Void, Void, List<Game>>() {
                        @Override
                        protected List<Game> doInBackground(Void... voids) {
                            List<Game> allFilteredByRating = Helper.getInstance(MainActivity.this).getAllGamesFilteredByRating();
                            return allFilteredByRating;
                        }

                        @Override
                        protected void onPostExecute(List<Game> games) {
                            super.onPostExecute(games);
                            mGameRecyclerAdapter.replaceData(games);
                            mResultSize.setText("Number of Items: " + resultSize(games));
                        }
                    };
                    taskAllFilteredByRating.execute();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        //Next we make the app searchable. right click on res > new resource file > XML file named
        //searchable.
        //Also added some lines to the android manifest file.

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView'da support.widget olanı seç
        mSearcView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, MainActivity.class);
        mSearcView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        MenuItem search2 = menu.findItem(R.id.search);

        MenuItemCompat.setOnActionExpandListener(search2, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                AsyncTask<Void, Void, List<Game>> taskAllGames = new AsyncTask<Void, Void, List<Game>>() {
                    @Override
                    protected List<Game> doInBackground(Void... voids) {
                        List<Game> allGames = Helper.getInstance(MainActivity.this).getAllGames();
                        return allGames;
                    }

                    @Override
                    protected void onPostExecute(List<Game> games) {
                        super.onPostExecute(games);
                        mGameRecyclerAdapter.replaceData(games);
                        mResultSize.setText("Number of Items: " + resultSize(games));
                        mSearchQuery="";
                    }
                };
                taskAllGames.execute();
                return true;
            }
        });
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);

    }

    public void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            //perform search
            String query = intent.getStringExtra(SearchManager.QUERY);

            AsyncTask<String, Void, List<Game>> taskSearch = new AsyncTask<String, Void, List<Game>>() {
                @Override
                protected List<Game> doInBackground(String... strings) {
                    List<Game> searchList = Helper.getInstance(MainActivity.this).
                            itemSearchForNameOrType(strings[0]);
                    return searchList;
                }

                @Override
                protected void onPostExecute(List<Game> games) {
                    super.onPostExecute(games);
                    mGameRecyclerAdapter.replaceData(games);
                    mResultSize.setText("Number of Items: " + resultSize(games));
                }
            };
            taskSearch.execute(query);

            mSearchQuery = intent.getStringExtra(SearchManager.QUERY);
            mSearcView.clearFocus();
        }
    }

    public String resultSize(List<Game> list) {
        Integer a = list.size();
        String b = a.toString();
        return b;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }

    @Override
    public void onGameSelected(int gameId) {
        if (mTwoPane) {
            //Setup the detail fragment
            DetailFragment detailFragment = DetailFragment.newInstance(gameId, mTwoPane);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.detail_fragment_container, detailFragment);
            fragmentTransaction.commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.ITEM_ID_KEY, gameId);
            intent.putExtra(DetailActivity.IS_TWO_PANE, mTwoPane);
            startActivity(intent);
        }
    }
}