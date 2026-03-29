package wepper.mines.mineswepper.gui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import android.widget.ImageView;

import wepper.mines.mineswepper.R;
import wepper.mines.mineswepper.gui.tile.TileView;
import wepper.mines.mineswepper.world.GameInterface;
import wepper.mines.mineswepper.world.exceptions.TooManyFlagsException;
import wepper.mines.mineswepper.world.field.Field;

/**
 * Created by philhaus16 on 08.08.2016.
 */
public class FieldView extends TileView{
    private Field field;

    public FieldView(Context context, Field field) {
        super(context);
        this.field = field;
        update();
    }

    public void update(GameInterface gameInterface){
        if(gameInterface.isFinish()){
            if(gameInterface.isWin()){
                win();
            }else{
                lose();
            }
        }else{
            update();
        }
    }

    public void flag() throws TooManyFlagsException {
        field.toggleFlag();
    }

    public void scan(){
        field.show();
    }

    private void update(){
        setDrawable(getUpdateFieldResource(field.isShow(),field.isFlag(),field.getNeighborMinesCount()));
    }

    private void lose(){
        setDrawable(getMineFieldResource(field.isFlag(),field.isMine()));
    }

    private void win(){
        setDrawable(getWinFieldResource(field.isMine(),field.getNeighborMinesCount()));
    }

    private static @DrawableRes int getNeutralFieldResource(int neighborMinesCount){
        switch (neighborMinesCount){
            case 0:
                return R.drawable.field0;
            case 1:
                return R.drawable.field1;
            case 2:
                return R.drawable.field2;
            case 3:
                return R.drawable.field3;
            case 4:
                return R.drawable.field4;
            case 5:
                return R.drawable.field5;
            case 6:
                return R.drawable.field6;
            case 7:
                return R.drawable.field7;
            case 8:
                return R.drawable.field8;
        }
        return -1;
    }

    private static @DrawableRes int getFlagFieldResource(boolean flag){
        if(flag) {
            return R.drawable.field_flag;
        }else{
            return R.drawable.field;
        }
    }

    private static @DrawableRes int getMineFieldResource(boolean flag,boolean mine){
        if(mine){
            if(!flag) {
                return R.drawable.field_mine;
            }
        }else{
            if(flag){
                return R.drawable.field_no_mine;
            }
        }
        return -1;
    }

    private static @DrawableRes int getWinFieldResource(boolean mine,int neighborMinesCount){
        if(mine){
            return R.drawable.field_flag;
        }else {
            return getNeutralFieldResource(neighborMinesCount);
        }
    }

    private static @DrawableRes int getUpdateFieldResource(boolean show,boolean flag,int neighborMinesCount){
        if(show){
            return getNeutralFieldResource(neighborMinesCount);
        }else{
            return getFlagFieldResource(flag);
        }
    }
}
