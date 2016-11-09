package gameshop.serkanbal.com.gameshop.Cart;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.Data.Helper;
import gameshop.serkanbal.com.gameshop.R;

/**
 * Created by Serkan on 06/11/16.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder>
        implements ItemTouchHelperAdapter{
    List<Game> mCartGames;
    Context context;
    TextView mCartTotal;
    ImageView mBigCart;

    public CartRecyclerAdapter(List<Game> games, TextView cartTotal, ImageView bigCart) {
        mCartGames = games;
        mCartTotal = cartTotal;
        mBigCart = bigCart;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_cardview_layout, parent, false);
        context = view.getContext();
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        String name = mCartGames.get(position).getName();
        String platform = mCartGames.get(position).getPlatform();
        Double price = mCartGames.get(position).getPrice();

        holder.mCartName.setText(name);
        holder.mCartPlatform.setText(platform);
        holder.mCartPrice.setText("$" + price.toString());

        if (platform.equals("PS4")) {
            holder.mCartPlatform.setBackgroundColor(Color.parseColor("#003791"));
            holder.mCartPlatform.setTextColor(Color.WHITE);
        } else if (platform.equals("Xbox One")) {
            holder.mCartPlatform.setBackgroundColor(Color.parseColor("#5dc21e"));
            holder.mCartPlatform.setTextColor(Color.WHITE);
        } else {
            holder.mCartPlatform.setBackgroundColor(Color.GRAY);
            holder.mCartPlatform.setTextColor(Color.WHITE);
        }

        switch (mCartGames.get(position).getIdDetail()) {
            case 1:
                holder.mCartImage.setImageResource(R.drawable.destiny_ps4);
                break;
            case 2:
                holder.mCartImage.setImageResource(R.drawable.destiny_xb);
                break;
            case 3:
                holder.mCartImage.setImageResource(R.drawable.lastofus_ps4);
                break;
            case 4:
                holder.mCartImage.setImageResource(R.drawable.witcher_ps4);
                break;
            case 5:
                holder.mCartImage.setImageResource(R.drawable.witcher_xb);
                break;
            case 6:
                holder.mCartImage.setImageResource(R.drawable.witcher_pc);
                break;
            case 7:
                holder.mCartImage.setImageResource(R.drawable.doom_ps4);
                break;
            case 8:
                holder.mCartImage.setImageResource(R.drawable.doom_xb);
                break;
            case 9:
                holder.mCartImage.setImageResource(R.drawable.doom_pc);
                break;
            case 10:
                holder.mCartImage.setImageResource(R.drawable.uncharted4);
                break;
            case 11:
                holder.mCartImage.setImageResource(R.drawable.forza_xb);
                break;
            case 12:
                holder.mCartImage.setImageResource(R.drawable.forza_pc);
                break;
            case 13:
                holder.mCartImage.setImageResource(R.drawable.gearsofwar4_xb);
                break;
            case 14:
                holder.mCartImage.setImageResource(R.drawable.skyrim_ps4);
                break;
            case 15:
                holder.mCartImage.setImageResource(R.drawable.skyrim_xb);
                break;
            case 16:
                holder.mCartImage.setImageResource(R.drawable.skyrim_pc);
                break;
            default:
                //
                break;
        }
    
    
    }

    @Override
    public int getItemCount() {
        return mCartGames.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mCartGames, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mCartGames, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("key_detailId",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Integer a = mCartGames.get(position).getIdDetail();
        String detailId = a.toString();
        editor.putInt(detailId, -1);
        editor.commit();
        mCartGames.remove(position);
        notifyItemRemoved(position);

        if (mCartGames.size() == 0) {
            mCartTotal.setText("Cart is empty");
            mBigCart.setVisibility(View.VISIBLE);
        } else {
            mCartTotal.setText("Cart total is: $" + getCartTotal().toString());
        }
    }

    public Double getCartTotal() {
        Double sum = 0d;
        for (int i = 0; i < mCartGames.size(); i++) {
            sum = mCartGames.get(i).getPrice() + sum;
        }
        Double c = Math.round(sum * 100.0) / 100.0;
        return c;
    }

    public void checkOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("key_detailId",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<Game> allPossible = Helper.getInstance(context).getAllGames();
        for (Integer i = 1; i < allPossible.size()+1; i++) {
            editor.putInt(i.toString(), -1);
        }
        editor.commit();
        mCartTotal.setText("Cart is empty");
        mBigCart.setVisibility(View.VISIBLE);
        mCartGames.clear();
        notifyDataSetChanged();
    }

}
