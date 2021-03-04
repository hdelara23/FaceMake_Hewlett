package com.example.hewlettfacemaker;
//@Hewlett De Lara

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class FaceView extends SurfaceView{
    // Initialize the Face object
    Face face;

    public FaceView (Context context, AttributeSet set)
    {
        super(context, set); // Invoke parent class constructor
        setWillNotDraw(false);
    }

    public void setFace(Face f)
    {
        this.face = f;
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas); // Invoke parent class constructor
        canvas.drawColor(Color.DKGRAY); // Make the SurfaceView dark gray
        face.draw(canvas); // Draw Face on canvas
    }
}
