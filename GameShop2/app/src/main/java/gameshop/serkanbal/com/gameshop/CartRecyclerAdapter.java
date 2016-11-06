package gameshop.serkanbal.com.gameshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Serkan on 06/11/16.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder> {
    List<Game> mCartGames;

    public CartRecyclerAdapter(List<Game> games) {
        mCartGames = games;
    }
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_cardview_layout, parent, false);
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
}
