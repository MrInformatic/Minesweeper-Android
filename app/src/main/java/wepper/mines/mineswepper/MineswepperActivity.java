package wepper.mines.mineswepper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import wepper.mines.mineswepper.gui.WorldView;
import wepper.mines.mineswepper.gui.dialog.DificultyDialog;
import wepper.mines.mineswepper.gui.dialog.GameDialog;
import wepper.mines.mineswepper.world.World;

/**
 * Created by philhaus16 on 08.08.2016.
 */
public class MineswepperActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener,GameDialog.GameDialogListener,DificultyDialog.DificultyDialogListener{
    private WorldView view;

    private GameDialog gameDialog;
    private DificultyDialog dificultyDialog;

    private Switch flagSwitch;
    private TextView flagCount;

    private LinearLayout scroller;
    private Toolbar toolbar;

    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        this.setContentView(R.layout.activity_mineswepper);
        initDialogs();

        preferences = getPreferences(MODE_PRIVATE);

        flagCount = (TextView) findViewById(R.id.flag_count);
        flagSwitch = (Switch) findViewById(R.id.flag_switch);
        flagSwitch.setOnCheckedChangeListener(this);

        scroller = (LinearLayout) findViewById(R.id.scroller);

        view = new WorldView(this,flagCount);
        view.setOnClickListener(this);
        dificultyDialog.setPreferences(getPreferences(MODE_PRIVATE));
        restart();
        scroller.addView(view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        view.setFlag(isChecked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_difficulty) {
            dificultyDialog.showDificultyDialog();
            return true;
        } else if (id == R.id.action_restart) {
            restart();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onClick(View v) {
        updateFlagCount();
        update();
    }

    private void initDialogs() {
        initGameDialog();
        initDificultyDialog();
    }

    private void initGameDialog() {
        GameDialog.GameDialogBuilder builder = new GameDialog.GameDialogBuilder(this);
        builder.setWinMessage(R.string.win_message)
                .setLoseMessage(R.string.lose_message)
                .setPositiveButton(R.string.positive_button)
                .setNegativeButton(R.string.negative_button)
                .setListener(this);
        gameDialog = builder.create();
    }

    private void initDificultyDialog() {
        DificultyDialog.DificultyDialogBuilder builder = new DificultyDialog.DificultyDialogBuilder(this);
        builder.setTitle(R.string.dificulty_title)
                .setItems(R.array.dificulty)
                .setListener(this);
        dificultyDialog = builder.create();
    }

    @Override
    public void restart() {
        view.restart();
        updateFlagCount();
        flagSwitch.setChecked(false);
    }

    public void updateFlagCount() {
        flagCount.setText("Flag count: " + view.getFlagCount());
    }

    public void update(){
        if (view.isFinish()) {
            if (view.isWin()) {
                gameDialog.showWinDialog();
            } else if (view.isLose()) {
                gameDialog.showLoseDialog();
            }
        }
    }

    @Override
    public void setDificulty(int dificulty) {
        view.setDificulty(dificulty);
        restart();
    }
}
