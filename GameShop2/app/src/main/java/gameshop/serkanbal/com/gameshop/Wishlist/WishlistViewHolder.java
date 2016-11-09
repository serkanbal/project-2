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
    public TextView mWishlistName, mWishlisttPlatform, mWishlisttPrice;
    public CardView mWishlistCardView;
    public ImageView mWishlistImage;

    public WishlistViewHolder(View itemView) {
        super(itemView);
        mWishlistCardView = (CardView) itemView.findViewById(R.id.wishlistCardView);
        mWishlistName = (TextView) itemView.findViewById(R.id.wishlistName);
        mWishlisttPlatform = (TextView) itemView.findViewById(R.id.wishlistPlatform);
        mWishlisttPrice = (TextView) itemView.findViewById(R.id.wishlistPrice);
        mWishlistImage = (ImageView) itemView.findViewById(R.id.wishlistImage);
    }

}
