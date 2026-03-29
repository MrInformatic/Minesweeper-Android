package wepper.mines.mineswepper.world;

import wepper.mines.mineswepper.world.exceptions.TooManyFlagsException;
import wepper.mines.mineswepper.world.exceptions.WinOrLoseException;
import wepper.mines.mineswepper.world.field.Field;

/**
 * Created by philhaus16 on 08.08.2016.
 */
public interface WorldInterface {
    public Field getField(int x,int y);
    public int getWidth();
    public int getHeight();
    public int getFlagCount();
}
