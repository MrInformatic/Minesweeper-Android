package wepper.mines.mineswepper.world.field;

import wepper.mines.mineswepper.world.exceptions.TooManyFlagsException;

/**
 * Created by philipp on 15.08.16.
 */
public interface FieldInterface {
    public boolean isMine();
    public void setMine();

    public boolean isFlag();
    public void toggleFlag() throws TooManyFlagsException;
    public void setFlag(boolean flag) throws TooManyFlagsException;

    public boolean isShow();
    public void show();

    public int getNeighborMinesCount();
}
