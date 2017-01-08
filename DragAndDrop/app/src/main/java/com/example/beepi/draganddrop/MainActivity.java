package com.example.beepi.draganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView ima;
    private static final String IMAGEVIEW_TAG = "Android Logo";
    private RelativeLayout.LayoutParams layoutParams;
    String msg = "Drag and drop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ima = (ImageView) findViewById(R.id.iv_logo);
        //Set the tag
        ima.setTag(IMAGEVIEW_TAG);
        ima.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(ima);

                // Starts the drag
//                v.startDragAndDrop(dragData, myShadow, null, 0);
                v.startDrag(dragData, myShadow, null, 0);
                return true;
            }
        });

        // Create and set the drag event listener for the view
        ima.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        Log.d(msg, "STARTED");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "ENTERED");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(msg, "EXIT");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(msg,"ENDED");
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.d(msg,"DROP");
                        break;
                    default:break;

                }
                return true;
            }
        });


        // set to move image
//        ima.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                layoutParams = (RelativeLayout.LayoutParams) ima.getLayoutParams();
//                switch(event.getAction())
//                {
//                    case MotionEvent.ACTION_DOWN:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int x_cord = (int)event.getRawX();
//                        int y_cord = (int)event.getRawY();
//
//                        if(x_cord>getWindowManager().getDefaultDisplay().getWidth())
//                        {x_cord=getWindowManager().getDefaultDisplay().getWidth();}
//                        if(y_cord>getWindowManager().getDefaultDisplay().getHeight())
//                        {y_cord=getWindowManager().getDefaultDisplay().getHeight();}
//
//                        layoutParams.leftMargin = x_cord -25;
//                        layoutParams.topMargin = y_cord - 75;
//
//                        ima.setLayoutParams(layoutParams);
//                        break;
//                    default:
//                        break;
//                }
//                return true;
//            }
//        });
    }



}

