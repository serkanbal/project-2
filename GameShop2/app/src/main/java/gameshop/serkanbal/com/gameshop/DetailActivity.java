package gameshop.serkanbal.com.gameshop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String ITEM_ID_KEY = "detailId";
    TextView mDetailName, mDetailDescription, mDetailCompany, mDetailPlatform, mDetailPrice,
    mDetailAvailability;
    ImageView mDetailRating, mDetailAvailabilityIcon, mScrollingHeader;


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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(ITEM_ID_KEY, -1);

        if (id == -1) {
            finish();
        }

        List<Game> gameDetailById = Helper.getInstance(this).
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

        String availableText = gameDetailById.get(0).getAvailability();
        if (availableText.equals("Available")) {
            mDetailAvailability.setTextColor(getResources().getColor(R.color.grass_green));
            mDetailAvailabilityIcon.setImageResource(R.drawable.ic_check_black_24dp);
            mDetailAvailabilityIcon.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                    R.color.grass_green));
        } else {
            mDetailAvailability.setTextColor(getResources().getColor(R.color.red));
            mDetailAvailabilityIcon.setImageResource(R.drawable.ic_error_black_24dp);
            mDetailAvailabilityIcon.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                    R.color.red));
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
    }
}
