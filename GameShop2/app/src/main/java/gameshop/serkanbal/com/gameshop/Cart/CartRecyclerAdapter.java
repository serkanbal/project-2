package gameshop.serkanbal.com.gameshop.Cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.R;

/**
 * Created by Serkan on 06/11/16.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder>
        implements ItemTouchHelperAdapter{
    private List<Game> mCartGames;
    Context context;

    public CartRecyclerAdapter(List<Game> games) {
        mCartGames = games;
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
        mCartGames.remove(position);
        notifyItemRemoved(position);
    }
}
