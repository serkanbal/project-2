package gameshop.serkanbal.com.gameshop;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Serkan on 01/11/16.
 */

public class GameRecyclerAdapter extends RecyclerView.Adapter<GameViewHolder> {
    List<Game> mGames;

    public GameRecyclerAdapter(List<Game> games) {
        mGames = games;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_cardview, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GameViewHolder holder, int position) {
        String name = mGames.get(position).getName();
        String platform = mGames.get(position).getPlatform();
        String company = mGames.get(position).getCompany();
        String availability = mGames.get(position).getAvailability();
        Double rating = mGames.get(position).getRating();
        Double price = mGames.get(position).getPrice();
        int idDetail = mGames.get(position).getIdDetail();

        holder.mTextName.setText(name);
        holder.mTextPlatform.setText(platform);
        holder.mTextCompany.setText("by " + company);
        holder.mAvailability.setText(availability);
        holder.mRating.setText(rating.toString());
        holder.mPrice.setText("$"+price.toString());

        if (platform.equals("PS4")) {
            holder.mTextPlatform.setBackgroundColor(Color.parseColor("#003791"));
            holder.mTextPlatform.setTextColor(Color.WHITE);
        } else if (platform.equals("Xbox One")) {
            holder.mTextPlatform.setBackgroundColor(Color.parseColor("#5dc21e"));
            holder.mTextPlatform.setTextColor(Color.WHITE);
        } else {
            holder.mTextPlatform.setBackgroundColor(Color.GRAY);
            holder.mTextPlatform.setTextColor(Color.WHITE);
        }


        switch (idDetail) {
            case 1:
                holder.mImageCover.setImageResource(R.drawable.destiny_ps4);
                break;
            case 2:
                holder.mImageCover.setImageResource(R.drawable.destiny_xb);
                break;
            case 3:
                holder.mImageCover.setImageResource(R.drawable.lastofus_ps4);
                break;
            case 4:
                holder.mImageCover.setImageResource(R.drawable.witcher_ps4);
                break;
            case 5:
                holder.mImageCover.setImageResource(R.drawable.witcher_xb);
                break;
            case 6:
                holder.mImageCover.setImageResource(R.drawable.witcher_pc);
                break;
            case 7:
                holder.mImageCover.setImageResource(R.drawable.doom_ps4);
                break;
            case 8:
                holder.mImageCover.setImageResource(R.drawable.doom_xb);
                break;
            case 9:
                holder.mImageCover.setImageResource(R.drawable.doom_pc);
                break;
            case 10:
                holder.mImageCover.setImageResource(R.drawable.uncharted4);
                break;
            case 11:
                holder.mImageCover.setImageResource(R.drawable.forza_xb);
                break;
            case 12:
                holder.mImageCover.setImageResource(R.drawable.forza_pc);
                break;
            case 13:
                holder.mImageCover.setImageResource(R.drawable.gearsofwar4_xb);
                break;
            case 14:
                holder.mImageCover.setImageResource(R.drawable.skyrim_ps4);
                break;
            case 15:
                holder.mImageCover.setImageResource(R.drawable.skyrim_xb);
                break;
            case 16:
                holder.mImageCover.setImageResource(R.drawable.skyrim_pc);
                break;
            default:
                //
                break;
        }

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_ID_KEY,
                        mGames.get(holder.getAdapterPosition()).getIdDetail());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void replaceData(List<Game> newList){
        mGames = newList;
        notifyDataSetChanged();
    }

    public void restoreData(List<Game> newList){
        mGames = newList;
        notifyDataSetChanged();
    }
}
