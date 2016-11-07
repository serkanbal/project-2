package gameshop.serkanbal.com.gameshop.MainDetailActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.R;

import static gameshop.serkanbal.com.gameshop.R.id.fab;

public class DetailActivity extends AppCompatActivity {
    public static final String ITEM_ID_KEY = "detailId";
    TextView mDetailName, mDetailDescription, mDetailCompany, mDetailPlatform, mDetailPrice,
    mDetailAvailability;
    ImageView mDetailRating, mDetailAvailabilityIcon, mScrollingHeader;
    FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDetailName = (TextView) findViewById(R.id.detail_name);
        mDetailDescription = (TextView) findViewById(R.id.detail_description);
        mDetailCompany = (TextView) findViewById(R.id.detail_company);
        mDetailPlatform = (TextView) findViewById(R.id.detail_platform);
        mDetailPrice = (TextView) findViewById(R.id.detail_price);
        mDetailRating = (ImageView) findViewById(R.id.detail_ratingImages);
        mDetailAvailability = (TextView) findViewById(R.id.detail_availableText);
        mDetailAvailabilityIcon = (ImageView) findViewById(R.id.detail_availableIcon);
        mScrollingHeader = (ImageView) findViewById(R.id.header);
        mFab = (FloatingActionButton) findViewById(fab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int id = getIntent().getIntExtra(ITEM_ID_KEY, -1);

        if (id == -1) {
            finish();
        }

        final List<Game> gameDetailById = Helper.getInstance(this).
                getGameById(id);

        if (gameDetailById == null) {
            finish();
        }

        mDetailName.setText(gameDetailById.get(0).getName());
        mDetailDescription.setText(gameDetailById.get(0).getDescription());
        mDetailCompany.setText(gameDetailById.get(0).getCompany());
        mDetailPlatform.setText("  |  " + gameDetailById.get(0).getPlatform());
        Double price = gameDetailById.get(0).getPrice();
        mDetailPrice.setText("$" + price.toString());
        mDetailAvailability.setText("  "+gameDetailById.get(0).getAvailability());

        Double rating = (gameDetailById.get(0).getRating())/2;

        if (rating == 5) {
            mDetailRating.setImageResource(R.drawable.rsz_1stars5);
        } else if (rating >= 4) {
            mDetailRating.setImageResource(R.drawable.rsz_stars4);
        } else if (rating >= 3) {
            mDetailRating.setImageResource(R.drawable.rsz_stars3);
        } else if (rating >= 2) {
            mDetailRating.setImageResource(R.drawable.rsz_stars2);
        } else if (rating >= 0) {
            mDetailRating.setImageResource(R.drawable.rsz_star1);
        }

        final String availableText = gameDetailById.get(0).getAvailability();
        if (availableText.equals("Available")) {
            mDetailAvailability.setTextColor(getResources().getColor(R.color.grass_green));
            mDetailAvailabilityIcon.setImageResource(R.drawable.ic_check_circle_black_24dp);
            mDetailAvailabilityIcon.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                    R.color.grass_green));
        } else {
            mDetailAvailability.setTextColor(getResources().getColor(R.color.red));
            mDetailAvailabilityIcon.setImageResource(R.drawable.ic_error_black_24dp);
            mDetailAvailabilityIcon.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                    R.color.red));
            mFab.setImageResource(R.drawable.ic_block_black_24dp);
            mFab.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                    R.color.disabled_gray));
        }

        switch (gameDetailById.get(0).getIdDetail()) {
            case 1:
            case 2:
                mScrollingHeader.setImageResource(R.drawable.rsz_destiny);
                break;
            case 3:
                mScrollingHeader.setImageResource(R.drawable.rsz_detail_lastofus);
                break;
            case 4:
            case 5:
            case 6:
                mScrollingHeader.setImageResource(R.drawable.rsz_detail_witcher3);
                break;
            case 7:
            case 8:
            case 9:
                mScrollingHeader.setImageResource(R.drawable.rsz_doom);
                break;
            case 10:
                mScrollingHeader.setImageResource(R.drawable.rsz_uncharted);
                break;
            case 11:
            case 12:
                mScrollingHeader.setImageResource(R.drawable.rsz_forza);
                break;
            case 13:
                mScrollingHeader.setImageResource(R.drawable.rsz_gears4);
                break;
            case 14:
            case 15:
            case 16:
                mScrollingHeader.setImageResource(R.drawable.rsz_skyrim);
                break;
            default:
                break;
        }

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (availableText.equals("Available")) {
                    Snackbar.make(view, getString(R.string.add_chart_sucess), Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    SharedPreferences sharedPreferences = DetailActivity.this.
                                            getSharedPreferences("key_detailId",
                                                    Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    Integer detailId = gameDetailById.get(0).getIdDetail();
                                    editor.putInt(detailId.toString(), -1);
                                    editor.commit();
                                }
                            }).show();
                    //Send item detail id to cart activity
                    SharedPreferences sharedPreferences = DetailActivity.this.
                            getSharedPreferences("key_detailId",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Integer detailId = gameDetailById.get(0).getIdDetail();
                    editor.putInt(detailId.toString(), detailId);
                    editor.commit();
                } else {
                    Snackbar.make(view, getString(R.string.add_chart_fail), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);
        return true;
    }
}
