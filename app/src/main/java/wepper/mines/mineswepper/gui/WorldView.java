package wepper.mines.mineswepper.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import wepper.mines.mineswepper.R;
import wepper.mines.mineswepper.gui.dialog.DificultyDialog;
import wepper.mines.mineswepper.gui.dialog.GameDialog;
import wepper.mines.mineswepper.gui.tile.TileWorldView;
import wepper.mines.mineswepper.world.GameInterface;
import wepper.mines.mineswepper.world.World;
import wepper.mines.mineswepper.world.WorldInterface;
import wepper.mines.mineswepper.world.exceptions.TooManyFlagsException;

import java.util.LinkedList;

/**
 * Created by philhaus16 on 08.08.2016.
 */
public class WorldView extends TileWorldView<FieldView> implements View.OnClickListener,GameDialog.GameDialogListener,GameInterface {
    public static final int BEGINNER_WORLD_WIDTH = 8;
    public static final int BEGINNER_WORLD_HEIGHT = 8;
    public static final int BEGINNER_WORLD_MINES = 10;
    public static final int INTERMEDIATE_WORLD_WIDTH = 16;
    public static final int INTERMEDIATE_WORLD_HEIGHT = 16;
    public static final int INTERMEDIATE_WORLD_MINES = 40;
    public static final int EXPERT_WORLD_WIDTH = 24;
    public static final int EXPERT_WORLD_HEIGHT = 24;
    public static final int EXPERT_WORLD_MINES = 99;

    private World world;
    private boolean flag = false;
    private int dificulty;
    private OnClickListener listener;

    private TextView flagCountView;

    public WorldView(Context context, TextView flagCountView) {
        super(context);

        this.flagCountView = flagCountView;
    }

    public void init(World world) {
        this.world = world;
        init();
    }


    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        listener = l;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof FieldView) {
            click((FieldView) v);
            update();
            listener.onClick(v);
        }
    }

    private void update() {
        updateAllTiles();
    }

    private void click(FieldView fieldView) {
        if(!world.isFinish()){
            if (flag) {
                try {
                    fieldView.flag();
                } catch (TooManyFlagsException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                fieldView.scan();
            }
        }
    }

    public void setDificulty(int dificulty) {
        this.dificulty = dificulty;
    }

    @Override
    public void finish() {
        Toast.makeText(getContext(), "Finish", Toast.LENGTH_LONG).show();
    }

    @Override
    public void restart() {
        init(getWorld(dificulty));
    }

    @Override
    public int getMapWidth() {
        return world.getWidth();
    }

    @Override
    public int getMapHeight() {
        return world.getHeight();
    }

    @Override
    public FieldView getTileView(int x, int y) {
        return new FieldView(getContext(), world.getField(x, y));
    }

    @Override
    public void updateTile(FieldView tile) {
        tile.update(world);
    }

    public int getFlagCount() {
        return world.getFlagCount();
    }

    @Override
    public boolean isLose() {
        return world.isLose();
    }

    @Override
    public boolean isWin() {
        return world.isWin();
    }

    @Override
    public boolean isFinish() {
        return world.isFinish();
    }

    private World getWorld(int dificultyLevel) {
        switch (dificultyLevel) {
            case 0:
                return getBeginnerWorld();
            case 1:
                return getIntermediateWorld();
            case 2:
                return getExpertWorld();
        }
        return null;
    }

    private World getBeginnerWorld() {
        return new World(BEGINNER_WORLD_WIDTH, BEGINNER_WORLD_HEIGHT, BEGINNER_WORLD_MINES);
    }

    private World getIntermediateWorld() {
        return new World(INTERMEDIATE_WORLD_WIDTH, INTERMEDIATE_WORLD_HEIGHT, INTERMEDIATE_WORLD_MINES);
    }

    private World getExpertWorld() {
        return new World(EXPERT_WORLD_WIDTH, EXPERT_WORLD_HEIGHT, EXPERT_WORLD_MINES);
    }
}
