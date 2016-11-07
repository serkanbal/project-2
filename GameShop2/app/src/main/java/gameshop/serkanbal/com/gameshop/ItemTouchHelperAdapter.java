package gameshop.serkanbal.com.gameshop;

/**
 * Created by Serkan on 07/11/16.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
