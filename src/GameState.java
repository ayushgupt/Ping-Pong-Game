/**
 * Created by quantumcoder on 4/25/2016.
 */
import org.json.simple.*;

public class GameState {

    public static JSONObject gamestate;

    public GameState(){
        gamestate.put("playerLY",Main.SIDE/2);
        gamestate.put("playerRY",Main.SIDE/2);
        gamestate.put("playerTX",Main.SIDE/2);
        gamestate.put("playerBX",Main.SIDE/2);
        gamestate.put("ballX",Main.SIDE/2);
        gamestate.put("ballY",Main.SIDE/2);
    }

    public static void update(int playerLY, int playerRY, int playerTX, int playerBX, int ballX, int ballY ){
        gamestate.put("playerLY",playerLY);
        gamestate.put("playerRY",playerRY);
        gamestate.put("playerTX",playerTX);
        gamestate.put("playerBX",playerBX);
        gamestate.put("ballX",ballX);
        gamestate.put("ballY",ballY);
    }

    public static int getplayerLY(){ return (int)gamestate.get("playerLY"); }
    public static int getplayerRY(){ return (int)gamestate.get("playerRY"); }
    public static int getplayerTX(){ return (int)gamestate.get("playerTX"); }
    public static int getplayerBX(){ return (int)gamestate.get("playerBX"); }
    public static int getballX(){ return (int)gamestate.get("ballX"); }
    public static int getballY(){ return (int)gamestate.get("ballY"); }


}
