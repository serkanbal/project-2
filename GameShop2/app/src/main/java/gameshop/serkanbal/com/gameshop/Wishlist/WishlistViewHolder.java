package gameshop.serkanbal.com.gameshop.Wishlist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gameshop.serkanbal.com.gameshop.R;

/**
 * Created by Serkan on 08/11/16.
 */

public class WishlistViewHolder extends RecyclerView.ViewHolder {
    public TextView mWishListName, mWishListPlatform, mWishListPrice, mWishListRating;
    public CardView mWishListCardView;
    public ImageView mWishListImage;

    public WishlistViewHolder(View itemView) {
        super(itemView);
        mWishListCardView = (CardView) itemView.findViewById(R.id.cartCardView);
        mWishListName = (TextView) itemView.findViewById(R.id.cartName);
        mWishListPlatform = (TextView) itemView.findViewById(R.id.cartPlatform);
        mWishListPrice = (TextView) itemView.findViewById(R.id.cartPrice);
        mWishListImage = (ImageView) itemView.findViewById(R.id.cartImage);
        mWishListRating = (TextView) itemView.findViewById(R.id.wishlistRating);
    }

}
