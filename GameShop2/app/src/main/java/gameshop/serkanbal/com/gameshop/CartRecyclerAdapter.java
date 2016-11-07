package gameshop.serkanbal.com.gameshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by Serkan on 06/11/16.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartViewHolder>
        implements ItemTouchHelperAdapter{
    List<Game> mCartGames;
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
        SharedPreferences sharedPreferences = context.getSharedPreferences("key_detailId",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Integer a = mCartGames.get(position).getIdDetail();
        String detailId = a.toString();
        editor.putInt(detailId, -1);
        editor.commit();
        mCartGames.remove(position);
        notifyItemRemoved(position);
        //
    }
}
