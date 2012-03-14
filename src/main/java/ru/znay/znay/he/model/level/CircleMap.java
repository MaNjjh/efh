package ru.znay.znay.he.model.level;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 13.03.12
 * Time: 20:24
 * To change this template use File | Settings | File Templates.
 */
public class CircleMap {
    private boolean[] circle;
    private int radius;
    private int width;
    private int height;

    public CircleMap(int radius) {
        this.radius = radius;
        this.width = (this.radius) << 1;
        this.height = (this.radius) << 1;
        this.circle = new boolean[width * height];
        for (int i = 0; i < width * height; i++) {
            this.circle[i] = false;
        }
    }

    private void fillMap(int x, int y) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            this.circle[x + y * width] = true;
        }
    }


    private void circlePoints(int cx, int cy, int x, int y) {
        if (0 == x) {
            fillMap(cx, cy + y);
            fillMap(cx, cy - y);
            fillMap(cx + y, cy);
            fillMap(cx - y, cy);
        } else if (x == y) {
            fillMap(cx + x, cy + y);
            fillMap(cx - x, cy + y);
            fillMap(cx + x, cy - y);
            fillMap(cx - x, cy - y);
        } else if (x < y) {
            fillMap(cx + x, cy + y);
            fillMap(cx - x, cy + y);
            fillMap(cx + x, cy - y);
            fillMap(cx - x, cy - y);
            fillMap(cx + y, cy + x);
            fillMap(cx - y, cy + x);
            fillMap(cx + y, cy - x);
            fillMap(cx - y, cy - x);
        }
    }

    public void drawCircle() {
        int x = 0;
        int y = radius - 1;
        int p = (5 - radius * 4) / 4;

        circlePoints(width >> 1, height >> 1, x, y);

        while (x < y) {
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }

            circlePoints(width >> 1, height >> 1, x, y);
        }
    }

    private int findRightRow(int col) {
        for (int row = width; row > width >> 1; row--) {
            boolean currentCol = circle[row + col * width];
            boolean nextCol = circle[(row - 1) + col * width];
            if (currentCol && !nextCol) {
                return row;
            }
        }
        return -1;
    }

    private int findLeftRow(int col) {
        for (int row = 0; row < width >> 1; row++) {
            boolean currentCol = circle[row + col * width];
            boolean nextCol = circle[(row + 1) + col * width];
            if (currentCol && !nextCol) {
                return row;
            }
        }
        return -1;
    }

    public void fillCircle() {
        for (int col = 0; col < height; col++) {
            int leftRow = findLeftRow(col);
            if (leftRow < 0) continue;
            int rightRow = findRightRow(col);
            if (rightRow < 0) continue;

            if (leftRow != rightRow) {
                for (int row = leftRow; row < rightRow; row++) {
                    fillMap(row, col);
                }
            }

        }
    }

    public boolean[] getCircle() {
        return circle;
    }

    public void setCircle(boolean[] circle) {
        this.circle = circle;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
