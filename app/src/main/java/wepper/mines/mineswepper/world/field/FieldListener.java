package wepper.mines.mineswepper.world.field;

import wepper.mines.mineswepper.world.exceptions.TooManyFlagsException;

/**
 * Created by philipp on 15.08.16.
 */
public interface FieldListener {
    public void addFlag() throws TooManyFlagsException;
    public void removeFlag();
    public void flagMine();
    public void deflagMine();
    public void lose();
}
