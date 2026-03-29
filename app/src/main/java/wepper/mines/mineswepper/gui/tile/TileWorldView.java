package wepper.mines.mineswepper.gui.tile;

import android.content.Context;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by philipp on 15.08.16.
 */
public abstract class TileWorldView<T extends TileView> extends GridLayout implements Iterable<T>,View.OnClickListener{
    private LinkedList<T> tiles = new LinkedList<>();

    public TileWorldView(Context context) {
        super(context);
    }

    public void init(){
        clearWorld();
        initWorld(getMapWidth(),getMapHeight());
    }

    private void clearWorld(){
        removeAllViews();
        tiles.clear();
    }

    private void initWorld(int width,int height){
        this.setColumnCount(width);
        this.setRowCount(height);

        initAllTiles(width,height);
    }

    private void initAllTiles(int width,int height){
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                initTile(x,y);
            }
        }
    }

    private void initTile(int x,int y){
        tiles.add(getTileView(x,y));
        tiles.getLast().setOnClickListener(this);
        this.addView(tiles.getLast());
    }

    public void updateAllTiles(){
        for(T tile : this){
            updateTile(tile);
        }
    }

    public abstract int getMapWidth();

    public abstract int getMapHeight();

    public abstract T getTileView(int x,int y);

    public abstract void updateTile(T tile);

    @Override
    public Iterator<T> iterator(){
        return tiles.iterator();
    }
}
