package gameshop.serkanbal.com.gameshop;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Serkan on 01/11/16.
 */

public class GameViewHolder extends RecyclerView.ViewHolder {
    public TextView mTextName, mTextPlatform, mTextCompany, mAvailability, mRating, mPrice;
    public ImageView mImageCover;

    public GameViewHolder(View itemView) {
        super(itemView);
        mTextName = (TextView) itemView.findViewById(R.id.textName);
        mTextPlatform = (TextView) itemView.findViewById(R.id.textPlatform);
        mTextCompany = (TextView) itemView.findViewById(R.id.textCompany);
        mAvailability = (TextView) itemView.findViewById(R.id.textAvailability);
        mRating = (TextView) itemView.findViewById(R.id.textRating);
        mPrice = (TextView) itemView.findViewById(R.id.textPrice);
        mImageCover = (ImageView) itemView.findViewById(R.id.imageCover);
    }
}