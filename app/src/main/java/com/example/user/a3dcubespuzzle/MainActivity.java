package com.example.user.a3dcubespuzzle;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    public static Paint p;
    // SurfaceView can;
    Cube cube, cubeFloor, cubeWall, cubeInner;
    DrawView dv;
    Bitmap bk;
   

    Table tab;
    int ci, cj;
    float xd = 0;
    float yd = 0;
    boolean fin;
    Random rnd;
    int numCol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bk = BitmapFactory.decodeResource(getResources(), R.drawable.colouredsquares);

        rnd = new Random();
        dv = new DrawView(this);
        setContentView(dv);
        dv.setOnTouchListener(this);
        numCol = rnd.nextInt(6);
        tab = new Table(numCol);
        tab.zoomTab(2.5);
        tab.moveTab(345, 580, 0);
        tab.clutterTab();
        fin = false;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    private float getDist(Point3D p1, Point3D p2) {

        return (float) Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() -
                p2.getY()) * (p1.getY() - p2.getY()) + (p1.getZ() - p2.getZ()) * (p1.getZ() - p2.getZ()));

    }

    private float get2dDist(Point p1, Point p2) {

        return (float) Math.sqrt((p1.GetX() - p2.GetX()) * (p1.GetX() - p2.GetX()) + (p1.GetY() -
                p2.GetY()) * (p1.GetY() - p2.GetY()));

    }

    boolean isPerfect(int col) {
        boolean ok = true;
        for (int i = 0; i < 3 && ok; i++)
            for (int j = 0; j < 3 && ok; j++)
                ok = ok && tab.cubeTable[i][j].al.get(0).getColor() == col;
        return ok;

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // xd =(float) tab.cubeTable[1][1].get2DCenter().GetX();
        // yd =(float) tab.cubeTable[1][1].get2DCenter().GetY();
        float xu = 0;
        float yu = 0;
        float xm = 0;
        float ym = 0;
        fin = false;
        int act=event.getAction() ;

        // if (event.getPointerCount() == 1) {

        switch (act) {
            case MotionEvent.ACTION_DOWN:
                xd = event.getX();
                yd = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                xu = event.getX();
                yu = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                xm = event.getX();
                ym = event.getY();
                break;
            default:
                break;
        }
        boolean ok = false;
        for (int i = 0; i < 3 && !ok; i++)
            for (int j = 0; j < 3 && !ok; j++) {
                // System.out.println("xd,yd = "+ xd +","+yd);
                //   System.out.println("center point= "+ tab.cubeTable[0][0].get2DCenter().GetX()+","+tab.cubeTable[0][0].get2DCenter().GetY());
                if (get2dDist(tab.cubeTable[i][j].get2DCenter(), new Point(xd / 2.5, yd / 2.5)) < 60) {
                    ok = true;
                    ci = i;
                    cj = j;

                }
            }

        if (ok && (act == MotionEvent.ACTION_UP)) {

            if (xd < xu && (xu - xd) > Math.abs(yu - yd)) tab.cubeTable[ci][cj].turnY(45);
            if (xd > xu && (xd - xu) > Math.abs(yu - yd)) tab.cubeTable[ci][cj].turnY(-45);
            if (yd < yu && (yu - yd) > Math.abs(xu - xd)) tab.cubeTable[ci][cj].turnX(45);
            if (yd > yu && (yd - yu) > Math.abs(xu - xd)) tab.cubeTable[ci][cj].turnX(-45);
        }

        for (int i = 0; i < 3; i++) {
            fin = true;
            //   System.out.println(" ");
            for (int j = 0; j < 3; j++) {
                switch (numCol) {
                    case 0: {
                       //  System.out.print("HowSeen :"+ tab.cubeTable[i][j].al.get(0).HowSeen());
                        fin = fin && (tab.cubeTable[i][j].al.get(0).HowSeen() < -0.85 && tab.cubeTable[i][j].al.get(0).HowSeen() > -1);
                        // System.out.println("FIN="+fin);
                    }
                    break;
                    case 1: {
                        //  System.out.print("HowSeen :"+ tab.cubeTable[i][j].al.get(0).HowSeen());
                        fin = fin && (tab.cubeTable[i][j].al.get(0).HowSeen() < 0.5 && tab.cubeTable[i][j].al.get(0).HowSeen() > 0);
                        // System.out.println("FIN="+fin);
                    }
                    break;
                    case 2: {
                        // System.out.print("HowSeen :"+ tab.cubeTable[i][j].al.get(0).HowSeen());
                        fin = fin && (tab.cubeTable[i][j].al.get(0).HowSeen() < 1 && tab.cubeTable[i][j].al.get(0).HowSeen() > 0.8);
                        // System.out.println("FIN="+fin);
                    }
                    break;
                    case 3: {
                         // System.out.print("HowSeen :"+ tab.cubeTable[i][j].al.get(0).HowSeen());
                        fin = fin && (tab.cubeTable[i][j].al.get(0).HowSeen() < 0.05 && tab.cubeTable[i][j].al.get(0).HowSeen() > -0.4);
                        // System.out.println("FIN="+fin);
                    }
                    break;
                    case 4: {
                        // System.out.print("HowSeen :"+ tab.cubeTable[i][j].al.get(0).HowSeen());
                        fin = fin && (tab.cubeTable[i][j].al.get(0).HowSeen() < 0.5 && tab.cubeTable[i][j].al.get(0).HowSeen() > 0.1);
                        // System.out.println("FIN="+fin);
                    }
                    break;
                    case 5: {
                        //  System.out.print("HowSeen :"+ tab.cubeTable[i][j].al.get(0).HowSeen());
                        fin = fin && (tab.cubeTable[i][j].al.get(0).HowSeen() < 0.1 && tab.cubeTable[i][j].al.get(0).HowSeen() > -0.3);
                        // System.out.println("FIN="+fin);
                    }
                    break;
                }
            }
        }
        //  System.out.println(" \n AFTER TESTING FIN=" + fin + "\n");


        dv.invalidate();
        return true;
    }


    class DrawView extends View {
        public DrawView(Context context) {
            super(context);
            p = new Paint();
            //    p.setStyle(Paint.Style.FILL);

        }

        protected void onDraw(Canvas canvas) {
           // canvas.drawColor(0,PorterDuff.Mode.CLEAR);
              canvas.drawBitmap(bk, 0, 0, p);
            p.setTextSize(80);

            switch (numCol) {
                case 0: {
                    p.setColor(Color.BLUE);
                    canvas.drawText(" Turn the cubes BLUE in front ", 5, 90, p);
                    p.setColor(Color.YELLOW);
                    canvas.drawText("       and color YELLOW up ", 5, 170, p);
                }
                break;
                case 1: {
                    p.setColor(Color.YELLOW);
                    canvas.drawText("  Order color YELLOW in front ", 5, 90, p);
                    p.setColor(Color.RED);
                    canvas.drawText("        and color RED up ", 5, 170, p);
                }
                break;
                case 2: {
                    p.setColor(Color.RED);
                    canvas.drawText("  Turn the cubes RED in front ", 5, 90, p);
                    p.setColor(Color.GREEN);
                    canvas.drawText("       and color GREEN up ", 5, 170, p);
                }
                break;
                case 3: {
                    p.setColor(Color.GREEN);
                    canvas.drawText("  Order color GREEN in front ", 5, 90, p);
                    p.setColor(Color.BLUE);
                    canvas.drawText("       and color BLUE up ", 5, 170, p);
                }
                break;
                case 4: {
                    p.setColor(Color.WHITE);
                    canvas.drawText("       Order WHITE in front ", 5, 90, p);
                    p.setColor(Color.YELLOW);
                    canvas.drawText("       and color YELLOW up ", 5, 170, p);
                }
                break;
                case 5: {
                    p.setColor(Color.CYAN);
                    canvas.drawText("   Order color CYAN in front ", 5, 90, p);
                    p.setColor(Color.YELLOW);
                    canvas.drawText("       and color YELLOW up ", 5, 170, p);
                }
                break;
            }
            // canvas.drawText("  and gray color up ",50,175,p);
            for (int i = 0; i < tab.size; i++)
                for (int j = 0; j < tab.size; j++) {
                    tab.cubeTable[i][j].drawCube(canvas);
                    System.out.print(tab.cubeTable[i][j].al.get(0).HowSeen() + " ");
                }
            if (fin) {
                System.out.println("CONGRATULATION!");
              //  p.setColor(Color.YELLOW);
                p.setARGB(200,150,100,50);

                canvas.drawText("CONGRATULATION!", 200, 850, p);
                Intent intent = new Intent(MainActivity.this,FinalActivity.class);
                startActivity(intent);


            }

        }
    }
}
