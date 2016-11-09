package gameshop.serkanbal.com.gameshop.Cart;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gameshop.serkanbal.com.gameshop.R;

/**
 * Created by Serkan on 06/11/16.
 */

public class CartViewHolder extends RecyclerView.ViewHolder {
    public TextView mCartName, mCartPlatform, mCartPrice;
    public CardView mCartCardView;
    public ImageView mCartImage;

    public CartViewHolder(View itemView) {
        super(itemView);
        mCartCardView = (CardView) itemView.findViewById(R.id.wishlistCardView);
        mCartName = (TextView) itemView.findViewById(R.id.wishlistName);
        mCartPlatform = (TextView) itemView.findViewById(R.id.wishlistPlatform);
        mCartPrice = (TextView) itemView.findViewById(R.id.wishlistPrice);
        mCartImage = (ImageView) itemView.findViewById(R.id.wishlistImage);
    }
}
