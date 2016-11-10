package gameshop.serkanbal.com.gameshop;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import gameshop.serkanbal.com.gameshop.Data.Game;
import gameshop.serkanbal.com.gameshop.MainActivity.MainActivity;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Serkan on 10/11/16.
 */

public class GameShopTest {

    @Test
    public void testIfResultSizeCorrect() {
        MainActivity mainActivity = new MainActivity();
        Game game = new Game(null,null,null,null,null,0,0,0);
        Game game2 = new Game(null,null,null,null,null,0,0,0);
        List<Game> testGame = new ArrayList<>();
        testGame.add(game);
        testGame.add(game2);

        assertEquals("2", mainActivity.resultSize(testGame));
    }

}
