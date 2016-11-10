package gameshop.serkanbal.com.gameshop.MainActivity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gameshop.serkanbal.com.gameshop.R;

/**
 * Created by Serkan on 01/11/16.
 */

public class GameViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextName, mTextPlatform, mTextCompany, mAvailability, mRating, mPrice;
    public ImageView mImageCover;
    public CardView mCardView;

    public GameViewHolder(View itemView) {
        super(itemView);
        mCardView = (CardView) itemView.findViewById(R.id.card_view);
        mTextName = (TextView) itemView.findViewById(R.id.textName);
        mTextPlatform = (TextView) itemView.findViewById(R.id.textPlatform);
        mTextCompany = (TextView) itemView.findViewById(R.id.textCompany);
        mAvailability = (TextView) itemView.findViewById(R.id.textAvailability);
        mRating = (TextView) itemView.findViewById(R.id.textRating);
        mPrice = (TextView) itemView.findViewById(R.id.textPrice);
        mImageCover = (ImageView) itemView.findViewById(R.id.imageCover);
    }
}
