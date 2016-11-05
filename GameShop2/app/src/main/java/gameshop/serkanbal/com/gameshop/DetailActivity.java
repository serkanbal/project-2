package gameshop.serkanbal.com.gameshop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String ITEM_ID_KEY = "detailId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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

        TextView text = (TextView) findViewById(R.id.textScrolling);
        text.setText(gameDetailById.get(0).getName());
    }
}
