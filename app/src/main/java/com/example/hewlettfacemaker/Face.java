package com.example.hewlettfacemaker;
//@Hewlett De Lara
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

public class Face {
    private int hairStyle;
    private Paint skinPaint = new Paint();
    private Paint eyePaint = new Paint ();
    private Paint hairPaint = new Paint();

    // Face class constructor
    public Face ()
    {
        this.Randomize();
    }


    // Randomize the Face's hair style and colors of its hair, eyes, and skin
    public void Randomize ()
    {
        // Generate a random number
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        // Use the random number to determine the Face's attributes
        switch(randomNumber)
        {
            case 0:
                skinPaint.setColor(Color.YELLOW);
                eyePaint.setColor(Color.BLUE);
                hairPaint.setColor(Color.RED);
                break;
            case 1:
                skinPaint.setColor(Color.MAGENTA);
                eyePaint.setColor(Color.GREEN);
                hairPaint.setColor(Color.GRAY);
                break;
            case 2:
                skinPaint.setColor(Color.WHITE);
                eyePaint.setColor(Color.CYAN);
                hairPaint.setColor(Color.BLACK);
                break;
        }
        hairStyle = random.nextInt(3);
    }


    // Draw the Face with its eyes, hair, and head on the canvas
    public void draw(Canvas canvas)
    {
        canvas.drawCircle(900,650,500, skinPaint);
        drawEye(canvas);
        drawHair(canvas);
    }

    // Draw the Face's eyes
    public void drawEye(Canvas canvas)
    {
        canvas.drawCircle(725+(80/4), 500+(80/4), 80, eyePaint); // iris
        canvas.drawCircle(725+(80/3), 500+(80/3), 80/2, new Paint(Color.BLACK)); // pupil

        canvas.drawCircle(1000+(80/4), 500+(80/4), 80, eyePaint); // iris
        canvas.drawCircle(1000+(80/3), 500+(80/3), 80/2, new Paint(Color.BLACK)); // pupil

    }

    // Draw the Face's hair
    public void drawHair(Canvas canvas)
    {
        switch (hairStyle)
        {
            case 1: // Draw Bowl Cut hairstyle
                canvas.drawArc(325, 100, 1475, 775, 180F, 180F, true, hairPaint);
                break;
            case 2: // Draw Afro hairstyle
                canvas.drawArc(325, 100, 1475, 775, 180F, 180F, true, hairPaint);
                canvas.drawArc(250,175,1000, 1025, 90, 180F, true, hairPaint );
                canvas.drawArc(775,175,1550, 1025, -90, 180F, true, hairPaint );
                break;
            case 3: // Draw Buzz Cut hairstyle
                canvas.drawRect(425, 100, 1375, 400, hairPaint);
                break;
        }
    }

    // Set the hairstyle for the Face
    public void setHairStyle(int hairStyle)
    {
        this.hairStyle = hairStyle;
    }

    /**
     External Citation
     Date: March 1, 2021
     Problem: Couldn't read and find rgb values from int colors
     Resource:
     https://stackoverflow.com/questions/17183587/convert
     -integer-color-value-to-rgb
     Solution: Used the Sample Code in the Thread to correctly read and determine rgb values
     for different features of the Face as shown below.
     */

    // Get rgb values of the skin color
    public int getSkinColor(int color)
    {
        int rgb = skinPaint.getColor();
        switch(color)
        {
            case 0:
                return ((rgb >> 16) & 0xFF); // (get the red rgb value) Red

            case 1:
                return ((rgb >> 8) & 0xFF); // (get the green rgb value) Green

            case 2:
                return ((rgb) & 0xFF); // (get the blue rgb value) Blue
        }
        return 0;
    }

    // Get rgb values of the eye color
    public int getEyeColor(int color)
    {
        int rgb = eyePaint.getColor(); // Change eye paint object into an int color
        switch(color)
        {
            case 0:
                return ((rgb >> 16) & 0xFF); // Red

            case 1:
                return ((rgb >> 8) & 0xFF); // Green

            case 2:
                return ((rgb) & 0xFF); // Blue
        }
        return 0;
    }

    // Get rgb values of the hair color
    public int getHairColor(int color)
    {
        int rgb = hairPaint.getColor(); // Change hair paint object into an int color
        switch(color)
        {
            case 0:
                return ((rgb >> 16) & 0xFF); // Red

            case 1:
                return ((rgb >> 8) & 0xFF); // Green

            case 2:
                return ((rgb) & 0xFF); // Blue
        }
        return 0;
    }


    // Set the rgb values of the skin color
    public void setSkinColor(int progress, int color)
    {
        int rgb = skinPaint.getColor(); // Change skin paint object into an int color

        // Get the current rgb values
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = (rgb) & 0xFF;

        // Use the new rgb values for the int color
        switch (color)
        {
            case 0: // Red
                this.skinPaint.setARGB(255, progress, green, blue);
                break;
            case 1: // Green
                this.skinPaint.setARGB(255, red, progress, blue);
                break;
            case 2: // Blue
                this.skinPaint.setARGB(255, red, green, progress);
                break;
        }
    }

    // Set the rgb values of the hair color
    public void setHairColor(int progress, int color)
    {
        int rgb = hairPaint.getColor(); // Change hair paint object into an int color

        // Get the current rgb values
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = (rgb) & 0xFF;

        // Use the currently read rgb values for the new color
        switch (color)
        {
            case 0: // Red
                this.hairPaint.setARGB(255, progress, green, blue);
                break;
            case 1: // Green
                this.hairPaint.setARGB(255, red, progress, blue);
                break;
            case 2: // Blue
                this.hairPaint.setARGB(255, red, green, progress);
                break;
        }
    }

    // Set the rgb values of the eye color
    public void setEyeColor(int progress, int color)
    {
        int rgb = eyePaint.getColor(); // Change/store eye paint object into an int color

        // Get and read the current rgb values
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = (rgb) & 0xFF;

        // Use the newly read rgb values for the new color
        switch (color)
        {
            case 0: // Red
                this.eyePaint.setARGB(255, progress, green, blue);
                break;
            case 1: // Green
                this.eyePaint.setARGB(255, red, progress, blue);
                break;
            case 2: // Blue
                this.eyePaint.setARGB(255, red, green, progress);
                break;
        }
    }

}
