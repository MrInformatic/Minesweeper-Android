package wepper.mines.mineswepper.world.exceptions;

/**
 * Created by philipp on 15.08.16.
 */
public class WinOrLoseException extends Exception{
    public WinOrLoseException(boolean win,boolean lose){
        super(getMessage(win, lose));
    }

    private static String getMessage(boolean win,boolean lose){
        if(win){
            return "You allready won!";
        }else if(lose){
            return "You allready lose!";
        }
        return null;
    }
}
