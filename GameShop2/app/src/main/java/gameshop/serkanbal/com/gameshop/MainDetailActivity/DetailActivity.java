package gameshop.serkanbal.com.gameshop.MainDetailActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import gameshop.serkanbal.com.gameshop.R;

public class DetailActivity extends AppCompatActivity implements DetailFragment.OnFragmentInteractionListener{
    public static final String ITEM_ID_KEY = "detailId";
    public static final String IS_TWO_PANE = "isTwoPane";

    ImageView mAddToWishlist;
    boolean misTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAddToWishlist = (ImageView) findViewById(R.id.buttonAddToWishlist);

        final int theID = getIntent().getIntExtra(ITEM_ID_KEY, -1);
        misTwoPane = getIntent().getBooleanExtra(IS_TWO_PANE, false);

        if (theID == -1) {
            finish();
        }

        //Setup the detail fragment
        if (savedInstanceState == null) {
            DetailFragment detailFragment = DetailFragment.newInstance(theID, misTwoPane);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.detail_fragment_container, detailFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        int theID = getIntent().getIntExtra(ITEM_ID_KEY, -1);
//        List<Game> gameDetailById = Helper.getInstance(this).
//                getGameById(theID);
//        SharedPreferences checkIfInWishList = DetailActivity.this.
//                getSharedPreferences("wishList",
//                        Context.MODE_PRIVATE);
//        Integer checkWishList = gameDetailById.get(0).getIdDetail();
//        if (checkIfInWishList.getInt(checkWishList.toString(), -1) == -1){
//            mAddToWishlist.setImageResource(R.drawable.ic_favorite_border_black_30dp);
//        } else {
//            mAddToWishlist.setImageResource(R.drawable.ic_favorite_black_30dp);
//        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Do nothing
    }
}
