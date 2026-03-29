package wepper.mines.mineswepper.world.field;

import wepper.mines.mineswepper.utils.Node;
import wepper.mines.mineswepper.world.exceptions.TooManyFlagsException;

/**
 * Created by philhaus16 on 12.08.2016.
 */
public class Field extends Node<Field> implements FieldInterface {
    private boolean mine = false;
    private boolean flag = false;
    private boolean show = false;
    private int neighborMinesCount = 0;
    private FieldListener listener;

    public Field(int x,int y) {
        super(x,y);
    }

    public void setListener(FieldListener listener) {
        this.listener = listener;
    }

    @Override
    public void setMine(){
        mine = true;
        for (Field neighbor : getNeighbors()) {
            neighbor.neighborMinesCount++;
        }
    }

    @Override
    public void show(){
        if(mine){
            if(!flag) {
                listener.lose();
            }
        }else {
            if (!show && !flag) {
                show = true;
                if (!mine && neighborMinesCount == 0) {
                    for (Field neighbor : getNeighbors()) {
                        neighbor.show();
                    }
                }
            }
        }
    }

    @Override
    public void toggleFlag() throws TooManyFlagsException {
        setFlag(!flag);
    }

    @Override
    public void setFlag(boolean flag) throws TooManyFlagsException {
        if(!show) {
            if (this.flag != flag) {
                updateFlag(flag);
            }
            this.flag = flag;
        }
    }

    private void updateFlag(boolean flag) throws TooManyFlagsException {
        if(flag){
            listener.addFlag();
            if(mine){
                listener.flagMine();
            }
        }else{
            listener.removeFlag();
            if(mine){
                listener.deflagMine();
            }
        }
    }

    @Override
    public boolean isMine(){
        return mine;
    }

    @Override
    public boolean isFlag(){
        return flag;
    }

    @Override
    public boolean isShow(){
        return show;
    }

    @Override
    public int getNeighborMinesCount() {
        return neighborMinesCount;
    }
}
