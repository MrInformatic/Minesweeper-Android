package wepper.mines.mineswepper.world.exceptions;

/**
 * Created by philipp on 15.08.16.
 */
public class TooManyFlagsException extends Exception {
    public TooManyFlagsException() {
        super("You can not place any more flags!");
    }


}
