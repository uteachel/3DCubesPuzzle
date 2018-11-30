package com.example.user.a3dcubespuzzle;

import java.util.Random;

public class Table {
    private Cube cube, cubeFloor, cubeWall, cubeInner;
    public Cube[][] cubeTable;
    public int size;

    public Table(int col) {
//createTab(col);
        this.size = 3;

        cubeTable = new Cube[size][size];
        if (col < 4) {
            for (int i = 0; i < size; i++) {
                System.out.println("\n");
                for (int j = 0; j < size; j++) {
                    cubeTable[i][j] = new Cube(50 + i * 150, 150 + 150 * j, 200, 150 + 150 * i, 250 + 150 * j, 300);
                    cubeTable[i][j].turnX(col * 90);
                   System.out.print(cubeTable[i][j].al.get(0).HowSeen() + " ");
                }
            }

        }
        if (col== 4) {
            for (int i = 0; i < size; i++) {
                System.out.println("\n");
                for (int j = 0; j < size; j++) {
                    cubeTable[i][j] = new Cube(50 + i * 150, 150 + 150 * j, 200, 150 + 150 * i, 250 + 150 * j, 300);
                    cubeTable[i][j].turnY( 90);
                    System.out.print(cubeTable[i][j].al.get(0).HowSeen() + " ");
                }
            }
        }
        if (col== 5) {
            for (int i = 0; i < size; i++) {
                System.out.println("\n");
                for (int j = 0; j < size; j++) {
                    cubeTable[i][j] = new Cube(50 + i * 150, 150 + 150 * j, 200, 150 + 150 * i, 250 + 150 * j, 300);
                    cubeTable[i][j].turnY( -90);
                    System.out.print(cubeTable[i][j].al.get(0).HowSeen() + " ");
                }
            }
        }
    }

    public void createTab(int col) {

        this.size = 3;
        cubeTable = new Cube[size][size];
        if (col < 4) {
            for (int i = 0; i < size; i++) {
                System.out.println("\n");
                for (int j = 0; j < size; j++) {
                    cubeTable[i][j] = new Cube(50 + i * 150, 150 + 150 * j, 200, 150 + 150 * i, 250 + 150 * j, 300);
                    cubeTable[i][j].rotateX(col * 90);
                  System.out.print(cubeTable[i][j].al.get(0).HowSeen() + " ");
                }
            }
        }
        if (col== 4) {
            for (int i = 0; i < size; i++) {
                System.out.println("\n");
                for (int j = 0; j < size; j++) {
                    cubeTable[i][j] = new Cube(50 + i * 150, 150 + 150 * j, 200, 150 + 150 * i, 250 + 150 * j, 300);
                    cubeTable[i][j].turnY( 90);
                    System.out.print(cubeTable[i][j].al.get(0).HowSeen() + " ");
                }
            }
        }
        if (col== 5) {
            for (int i = 0; i < size; i++) {
                System.out.println("\n");
                for (int j = 0; j < size; j++) {
                    cubeTable[i][j] = new Cube(50 + i * 150, 150 + 150 * j, 200, 150 + 150 * i, 250 + 150 * j, 300);
                    cubeTable[i][j].turnY( -90);
                    System.out.print(cubeTable[i][j].al.get(0).HowSeen() + " ");
                }
            }
        }
    }


    public void clutterTab() {
        Random rnd = new Random();
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                int count = rnd.nextInt(4);
                for (int k = 0; k < count; k++) cubeTable[i][j].turnZ(90);
                count = rnd.nextInt(4);
                for (int k = 0; k < count; k++) cubeTable[i][j].turnX(90);
                count = rnd.nextInt(4);
                for (int k = 0; k < count; k++) cubeTable[i][j].turnY(90);
            }
    }

    public Point3D getCenter() {
        return new Point3D((cubeTable[1][1].p2.getX() + cubeTable[1][1].p8.getX()) / 2, (cubeTable[1][1].p2.getY() + cubeTable[1][1].p8.getY()) / 2,
                (cubeTable[1][1].p2.getZ() + cubeTable[1][1].p8.getZ()) / 2);
    }

    public void moveTab(double dx, double dy, double dz) {

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                cubeTable[i][j].move(dx, dy, dz);
            }
    }

    public void scaleTab(double sx, double sy, double sz) {

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                cubeTable[i][j].scale(sx, sy, sz);
            }
    }

    public void turnTabX(double ang) {

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                cubeTable[i][j].rotateX(ang);
            }
    }

    public void turnTabY(double ang) {

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                cubeTable[i][j].rotateY(ang);
            }
    }

    public void turnTabZ(double ang) {

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                cubeTable[i][j].rotateZ(ang);
            }
    }

    public void zoomTab(double k) {
        Point3D p = getCenter();
        moveTab(-p.getX(), -p.getY(), -p.getZ());
        scaleTab(k, k, k);
        moveTab(p.getX(), p.getY(), p.getZ());
    }

    public void rotateCenterTabX(double ang) {
        Point3D p = getCenter();
        moveTab(-p.getX(), -p.getY(), -p.getZ());
        turnTabX(ang);
        moveTab(p.getX(), p.getY(), p.getZ());
    }

    public void rotateCenterTabY(double ang) {
        Point3D p = getCenter();
        moveTab(-p.getX(), -p.getY(), -p.getZ());
        turnTabY(ang);
        moveTab(p.getX(), p.getY(), p.getZ());
    }

    public void rotateCenterTabZ(double ang) {
        Point3D p = getCenter();
        moveTab(-p.getX(), -p.getY(), -p.getZ());
        turnTabZ(ang);
        moveTab(p.getX(), p.getY(), p.getZ());
    }

}
