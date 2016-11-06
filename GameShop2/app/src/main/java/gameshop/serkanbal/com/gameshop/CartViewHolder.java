package gameshop.serkanbal.com.gameshop;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Serkan on 06/11/16.
 */

public class CartViewHolder extends RecyclerView.ViewHolder {
    public TextView mCartName, mCartPlatform, mCartPrice;
    public CardView mCartCardView;

    public CartViewHolder(View itemView) {
        super(itemView);
        mCartCardView = (CardView) itemView.findViewById(R.id.cartCardView);
        mCartName = (TextView) itemView.findViewById(R.id.cartName);
        mCartPlatform = (TextView) itemView.findViewById(R.id.cartPlatform);
        mCartPrice = (TextView) itemView.findViewById(R.id.cartPrice);
    }
}
