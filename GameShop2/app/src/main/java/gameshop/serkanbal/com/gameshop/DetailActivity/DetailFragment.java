package gameshop.serkanbal.com.gameshop.DetailActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gameshop.serkanbal.com.gameshop.Cart.CartActivity2;
import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.R;
import gameshop.serkanbal.com.gameshop.Wishlist.WishlistActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView mAddToWishlist;

    // TODO: Rename and change types of parameters
    private int mSelectedGame;
    private boolean mIsTwoPane;

    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }
    
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(int selectedGame, boolean isTwoPane) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, selectedGame);
        args.putBoolean(ARG_PARAM2, isTwoPane);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectedGame = getArguments().getInt(ARG_PARAM1);
            mIsTwoPane = getArguments().getBoolean(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_detail, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!mIsTwoPane) {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final TextView mDetailName = (TextView) view.findViewById(R.id.detail_name);
        final TextView mDetailDescription = (TextView) view.findViewById(R.id.detail_description);
        final TextView mDetailCompany = (TextView) view.findViewById(R.id.detail_company);
        final TextView mDetailPlatform = (TextView) view.findViewById(R.id.detail_platform);
        final TextView mDetailPrice = (TextView) view.findViewById(R.id.detail_price);
        final ImageView mDetailRating = (ImageView) view.findViewById(R.id.detail_ratingImages);
        final TextView mDetailAvailability = (TextView) view.findViewById(R.id.detail_availableText);
        final ImageView mDetailAvailabilityIcon = (ImageView) view.findViewById(R.id.detail_availableIcon);
        final ImageView mScrollingHeader = (ImageView) view.findViewById(R.id.header);
        final FloatingActionButton mFab = (FloatingActionButton) view.findViewById(R.id.fabDetail);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                view.findViewById(R.id.toolbar_layout);
        mAddToWishlist = (ImageView) view.findViewById(R.id.buttonAddToWishlist);

        if (mIsTwoPane) {
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP); // list other flags here by |
            collapsingToolbarLayout.setLayoutParams(params);
            mDetailName.setVisibility(View.VISIBLE);
        }

        //Get selected item from db

        AsyncTask<Integer, Void, List<Game>> taskGetGameDetailById = new AsyncTask<Integer, Void, List<Game>>() {
            @Override
            protected List<Game> doInBackground(Integer... integers) {
                List<Game> gameDetailById = Helper.getInstance(getContext()).
                        getGameById(integers[0]);
                return gameDetailById;
            }

            @Override
            protected void onPostExecute(final List<Game> games) {
                super.onPostExecute(games);

                mDetailName.setText(games.get(0).getName());
                mDetailDescription.setText(games.get(0).getDescription());
                mDetailCompany.setText(games.get(0).getCompany());
                mDetailPlatform.setText("  |  " + games.get(0).getPlatform());
                Double price = games.get(0).getPrice();
                mDetailPrice.setText("$" + price.toString());
                mDetailAvailability.setText("  "+games.get(0).getAvailability());

                Double rating = (games.get(0).getRating())/2;

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

                final String availableText = games.get(0).getAvailability();
                if (availableText.equals(getString(R.string.available_text))) {
                    mDetailAvailability.setTextColor(getResources().getColor(R.color.grass_green));
                    mDetailAvailabilityIcon.setImageResource(R.drawable.ic_check_circle_black_24dp);
                    mDetailAvailabilityIcon.setColorFilter(ContextCompat.getColor(getContext(),
                            R.color.grass_green));
                } else {
                    mDetailAvailability.setTextColor(getResources().getColor(R.color.red));
                    mDetailAvailabilityIcon.setImageResource(R.drawable.ic_error_black_24dp);
                    mDetailAvailabilityIcon.setColorFilter(ContextCompat.getColor(getContext(),
                            R.color.red));
                    mFab.setImageResource(R.drawable.ic_block_black_24dp);
                    mFab.setColorFilter(ContextCompat.getColor(getContext(),
                            R.color.black));
                }

                switch (games.get(0).getIdDetail()) {
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
                        SharedPreferences checkIfInChart = getContext().
                                getSharedPreferences("key_detailId",
                                        Context.MODE_PRIVATE);
                        Integer checkId = games.get(0).getIdDetail();

                        if (checkIfInChart.getInt(checkId.toString(),-1) == -1) {
                            if (availableText.equals("Available")) {
                                Snackbar.make(view, getString(R.string.add_cart_sucess), Snackbar.LENGTH_LONG)
                                        .setAction("Undo", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                SharedPreferences sharedPreferences = getContext().
                                                        getSharedPreferences("key_detailId",
                                                                Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                Integer detailId = games.get(0).getIdDetail();
                                                editor.putInt(detailId.toString(), -1);
                                                editor.commit();
                                            }
                                        }).show();
                                //Send item detail id to cart activity
                                SharedPreferences sharedPreferences = getContext().
                                        getSharedPreferences("key_detailId",
                                                Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                Integer detailId = games.get(0).getIdDetail();
                                editor.putInt(detailId.toString(), detailId);
                                editor.commit();
                            } else {
                                Snackbar.make(view, getString(R.string.add_cart_fail), Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        } else {
                            Snackbar.make(view, getString(R.string.add_cart_item_already_at_cart),
                                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }
                    }
                });

                SharedPreferences checkIfInWishList = getContext().
                        getSharedPreferences("wishList",
                                Context.MODE_PRIVATE);
                Integer checkWishList = games.get(0).getIdDetail();

                if (checkIfInWishList.getInt(checkWishList.toString(), -1) == -1){
                    mAddToWishlist.setImageResource(R.drawable.ic_favorite_border_black_30dp);
                } else {
                    mAddToWishlist.setImageResource(R.drawable.ic_favorite_black_30dp);
                }

                mAddToWishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences checkIfInWishList = getContext().
                                getSharedPreferences("wishList",
                                        Context.MODE_PRIVATE);
                        Integer checkWishList = games.get(0).getIdDetail();

                        if (checkIfInWishList.getInt(checkWishList.toString(), -1) == -1) {
                            Snackbar.make(view, getString(R.string.add_wishlist_success), Snackbar.LENGTH_LONG)
                                    .setAction("Undo", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            SharedPreferences sharedPreferences = getContext().
                                                    getSharedPreferences("wishList",
                                                            Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            Integer detailId = games.get(0).getIdDetail();
                                            editor.putInt(detailId.toString(), -1);
                                            editor.commit();
                                            mAddToWishlist.setImageResource(R.drawable.ic_favorite_border_black_30dp);
                                        }
                                    }).show();
                            //Send item detail id to cart activity
                            SharedPreferences sharedPreferences = getContext().
                                    getSharedPreferences("wishList",
                                            Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Integer detailId = games.get(0).getIdDetail();
                            editor.putInt(detailId.toString(), detailId);
                            editor.commit();
                            mAddToWishlist.setImageResource(R.drawable.ic_favorite_black_30dp);
                        } else if (checkIfInWishList.getInt(checkWishList.toString(), -1) != -1){
                            Snackbar.make(view, getString(R.string.remove_wishlist_success), Snackbar.LENGTH_LONG)
                                    .setAction("Undo", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            SharedPreferences sharedPreferences = getContext().
                                                    getSharedPreferences("wishList",
                                                            Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            Integer detailId = games.get(0).getIdDetail();
                                            editor.putInt(detailId.toString(), detailId);
                                            editor.commit();
                                            mAddToWishlist.setImageResource(R.drawable.ic_favorite_black_30dp);
                                        }
                                    }).show();
                            //Send item detail id to cart activity
                            SharedPreferences sharedPreferences = getContext().
                                    getSharedPreferences("wishList",
                                            Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Integer detailId = games.get(0).getIdDetail();
                            editor.putInt(detailId.toString(), -1);
                            editor.commit();
                            mAddToWishlist.setImageResource(R.drawable.ic_favorite_border_black_30dp);
                        }
                    }
                });

            }
        };
        taskGetGameDetailById.execute(mSelectedGame);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!mIsTwoPane) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.menu_scrolling, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_goCart:
                Intent intent = new Intent(getActivity(),CartActivity2.class);
                startActivity(intent);
                break;
            case R.id.action_goWishlist:
                Intent intent2 = new Intent(getActivity(), WishlistActivity.class);
                startActivity(intent2);
                break;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        AsyncTask<Integer, Void, List<Game>> taskGetGameDetailById = new AsyncTask<Integer, Void, List<Game>>() {
            @Override
            protected List<Game> doInBackground(Integer... integers) {
                List<Game> gameDetailById = Helper.getInstance(getActivity()).
                        getGameById(integers[0]);
                return gameDetailById;
            }

            @Override
            protected void onPostExecute(List<Game> games) {
                super.onPostExecute(games);
                SharedPreferences checkIfInWishList = getActivity().
                        getSharedPreferences("wishList",
                                Context.MODE_PRIVATE);
                Integer checkWishList = games.get(0).getIdDetail();
                if (checkIfInWishList.getInt(checkWishList.toString(), -1) == -1){
                    mAddToWishlist.setImageResource(R.drawable.ic_favorite_border_black_30dp);
                } else {
                    mAddToWishlist.setImageResource(R.drawable.ic_favorite_black_30dp);
                }
            }
        };
        taskGetGameDetailById.execute(mSelectedGame);
    }
}
