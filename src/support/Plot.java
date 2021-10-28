package algsex.support;

import java.util.List;
import java.util.LinkedList;

public class Plot {
    private final double CIRCLE_RADIUS = 3;
    private final int PADDING = 5;
    private final int[] CANVAS_SIZE = new int[] { 1000, 1000 };

    private StdDraw stdDraw = new StdDraw();
    private double maxX = 10;
    private double maxY = 10;
    private List<Value> values = new LinkedList<>();

    public Plot() {
        initialize();
    }

    public void add(double x, double y) {
        add(x, y, java.awt.Color.BLACK);
    }

    public void add(double x, double y, java.awt.Color color) {
        assert x >= 0;
        assert y >= 0;

        Value v = new Value(x, y, color);
        values.add(v);

        if (x > maxX || y > maxY) upscale(x, y);
        else draw(v);
    }

    private void upscale(double x, double y) {
        while (maxX < x || maxY < y) {
            maxX *= 1.5;
            maxY *= 1.5;
        }

        stdDraw.clear();
        setScale();

        for (Value v : values) draw(v);
    }

    private void draw(Value v) {
        stdDraw.setPenColor(v.color);
        stdDraw.drawFilledCircle(v.x, v.y, CIRCLE_RADIUS);
    }

    private void initialize() {
        setCanvasSize();
        setScale();
    }

    private void setScale() {
        stdDraw.setScale(
            -PADDING, maxX + PADDING,
            -PADDING, maxY + PADDING
        );
    }

    private void setCanvasSize() {
        stdDraw.setCanvasSize(CANVAS_SIZE[0], CANVAS_SIZE[1]);
    }

    private class Value {
        private final double x;
        private final double y;
        private final java.awt.Color color;

        public Value(double x, double y, java.awt.Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Plot plot = new Plot();

        for (int i = 0; i < 100; i++) {
            setPenColor(i);

            if (args.length > 0 && args[0].equals("quadratic"))
                plot.add(i, Math.pow(i, 2));
            else
                plot.add(i, i);

            Thread.sleep(100);
        }
    }

    private static void setPenColor(int i) {
        if (i % 2 == 0)
            edu.princeton.cs.algs4.StdDraw.setPenColor(java.awt.Color.RED);
        else
            edu.princeton.cs.algs4.StdDraw.setPenColor(java.awt.Color.BLUE);
    }
}