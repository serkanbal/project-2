package gameshop.serkanbal.com.gameshop.Wishlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Cart.ItemTouchHelperAdapter;
import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.R;

/**
 * Created by Serkan on 08/11/16.
 */

public class WishlistRecyclerAdapter extends RecyclerView.Adapter<WishlistViewHolder>
        implements ItemTouchHelperAdapter {
    List<Game> mWishlistGames;
    TextView mWishListSize;
    ImageView mBigHeart;
    Context context;

    public WishlistRecyclerAdapter(List<Game> wishlistGames, TextView wishListSize, ImageView bigHeart) {
        mWishlistGames = wishlistGames;
        mWishListSize = wishListSize;
        mBigHeart = bigHeart;
    }

    @Override
    public WishlistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wishlist_recyclerview_layout, parent, false);
        context = view.getContext();
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WishlistViewHolder holder, final int position) {
        String name = mWishlistGames.get(position).getName();
        String platform = mWishlistGames.get(position).getPlatform();
        Double price = mWishlistGames.get(position).getPrice();
        Double rating = mWishlistGames.get(position).getRating();

        holder.mWishListName.setText(name);
        holder.mWishListRating.setText("Rated " + rating.toString());
        holder.mWishListPlatform.setText(platform);
        holder.mWishListPrice.setText("$" + price.toString());

        switch (mWishlistGames.get(position).getIdDetail()) {
            case 1:
                holder.mWishListImage.setImageResource(R.drawable.destiny_ps4);
                break;
            case 2:
                holder.mWishListImage.setImageResource(R.drawable.destiny_xb);
                break;
            case 3:
                holder.mWishListImage.setImageResource(R.drawable.lastofus_ps4);
                break;
            case 4:
                holder.mWishListImage.setImageResource(R.drawable.witcher_ps4);
                break;
            case 5:
                holder.mWishListImage.setImageResource(R.drawable.witcher_xb);
                break;
            case 6:
                holder.mWishListImage.setImageResource(R.drawable.witcher_pc);
                break;
            case 7:
                holder.mWishListImage.setImageResource(R.drawable.doom_ps4);
                break;
            case 8:
                holder.mWishListImage.setImageResource(R.drawable.doom_xb);
                break;
            case 9:
                holder.mWishListImage.setImageResource(R.drawable.doom_pc);
                break;
            case 10:
                holder.mWishListImage.setImageResource(R.drawable.uncharted4);
                break;
            case 11:
                holder.mWishListImage.setImageResource(R.drawable.forza_xb);
                break;
            case 12:
                holder.mWishListImage.setImageResource(R.drawable.forza_pc);
                break;
            case 13:
                holder.mWishListImage.setImageResource(R.drawable.gearsofwar4_xb);
                break;
            case 14:
                holder.mWishListImage.setImageResource(R.drawable.skyrim_ps4);
                break;
            case 15:
                holder.mWishListImage.setImageResource(R.drawable.skyrim_xb);
                break;
            case 16:
                holder.mWishListImage.setImageResource(R.drawable.skyrim_pc);
                break;
            default:
                //
                break;
        }

    }


    @Override
    public int getItemCount() {
        return mWishlistGames.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mWishlistGames, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mWishlistGames, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("wishList",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Integer a = mWishlistGames.get(position).getIdDetail();
        String detailId = a.toString();
        editor.putInt(detailId, -1);
        editor.commit();
        mWishlistGames.remove(position);
        notifyItemRemoved(position);

        if (mWishlistGames.size() == 0) {
            mWishListSize.setText("Wish List is empty");
            mBigHeart.setVisibility(View.VISIBLE);
        } else {
            mWishListSize.setText("Number of items: " + mWishlistGames.size());
        }
    }
}
