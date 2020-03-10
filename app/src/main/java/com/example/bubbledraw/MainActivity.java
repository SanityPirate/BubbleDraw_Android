package com.example.bubbledraw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Xml;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Scroller;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isChecked = false;

    BubbleView bubbleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bubbleView = (BubbleView) findViewById(R.id.bubbleView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem checkable = menu.findItem(R.id.action_drawMode);
        checkable.setChecked(isChecked);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_clear:
                bubbleView.clearCanvas();
                return true;
            case R.id.action_about:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("About Bubble Draw")
                        .setMessage("VERSION 1.0 \n" +
                                "Created by Brayden Waugh" +
                                " \nusing \"Learn Java the Easy Way: A Hands-On Introduction to Programming\" " +
                                "\nby Bryson Payne")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
                TextView textView = dialog.findViewById(android.R.id.message);
                textView.setMaxLines(5);
                textView.setScroller(new Scroller(this));
                textView.setVerticalScrollBarEnabled(true);
                textView.setMovementMethod(new ScrollingMovementMethod());
                return true;
            case R.id.action_drawMode:
                isChecked = !item.isChecked();
                item.setChecked(isChecked);
                if (isChecked) {
                    bubbleView.clearCanvas();
                    bubbleView.setDrawMode(true);
                } else {
                    bubbleView.setDrawMode(false);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
