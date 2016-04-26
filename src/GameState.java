/**
 * Created by quantumcoder on 4/25/2016.
 */
import org.json.simple.*;

public class GameState {

    public static JSONObject gamestate;

    public GameState(){
        gamestate = new JSONObject();
        gamestate.put("playerLY",Main.SIDE/2);
        gamestate.put("playerRY",Main.SIDE/2);
        gamestate.put("playerTX",Main.SIDE/2);
        gamestate.put("playerBX",Main.SIDE/2);
        gamestate.put("ballX",Main.SIDE/2);
        gamestate.put("ballY",Main.SIDE/2);
    }

    public static void update(Double playerLY, Double playerRY, Double playerTX, Double playerBX, Double ballX, Double ballY ){
        gamestate.put("playerLY",playerLY);
        gamestate.put("playerRY",playerRY);
        gamestate.put("playerTX",playerTX);
        gamestate.put("playerBX",playerBX);
        gamestate.put("ballX",ballX);
        gamestate.put("ballY",ballY);
    }

    public static Double getplayerLY(){ return (Double)gamestate.get("playerLY"); }
    public static Double getplayerRY(){ return (Double)gamestate.get("playerRY"); }
    public static Double getplayerTX(){ return (Double)gamestate.get("playerTX"); }
    public static Double getplayerBX(){ return (Double)gamestate.get("playerBX"); }
    public static Double getballX(){ return (Double)gamestate.get("ballX"); }
    public static Double getballY(){ return (Double)gamestate.get("ballY"); }


}
