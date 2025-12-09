package com.lyaneii.aoc.common.util;

import java.util.ArrayList;
import java.util.Objects;

public class Vec2 {
    public int x;
    public int y;
    
    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vec2 other) {
        x += other.x;
        y += other.y;
    }

    public void subtract(Vec2 other) {
        x -= other.x;
        y -= other.y;
    }
    
    public void scale(int scalar) {
        x *= scalar;
        y *= scalar;
    }
    
    public static long area(Vec2 a, Vec2 b) {
        int lengthX = Math.abs(b.x - a.x) + 1;
        int lengthY = Math.abs(b.y - a.y) + 1;
        return (long)lengthX * lengthY;
    }

    public static Vec2 add(Vec2 lhs, Vec2 rhs) {
        return new Vec2(lhs.x + rhs.x, lhs.y + rhs.y);
    }

    public static Vec2 subtract(Vec2 lhs, Vec2 rhs) {
        return new Vec2(lhs.x - rhs.x, lhs.y - rhs.y);
    }

    public static Vec2 scale(Vec2 vec, int scalar) {
        return new Vec2(vec.x * scalar, vec.y * scalar);
    }

    public static float magnitudeFloat(Vec2 vec) {
        return (float) Math.sqrt(distanceSquared(vec));
    }

    public static double magnitude(Vec2 vec) {
        return Math.sqrt(distanceSquared(vec));
    }

    public static long distanceSquared(Vec2 vec) {
        long x = (long)vec.x * vec.x;
        long y = (long)vec.y * vec.y;
        return x + y;
    }

    public static long distanceSquared(Vec2 a, Vec2 b) {
        return distanceSquared(subtract(b, a));
    }

    public static float distanceFloat(Vec2 a, Vec2 b) {
        return magnitudeFloat(subtract(b, a));
    }

    public static double distance(Vec2 a, Vec2 b) {
        return magnitude(subtract(b, a));
    }

    public static Vec2 toVector2(String[] coordinateStringArray) {
        return new Vec2(
                Integer.parseInt(coordinateStringArray[0]),
                Integer.parseInt(coordinateStringArray[1])
        );
    }
    
    public static ArrayList<Vec2> toArrayList(String[] coordinates) {
        ArrayList<Vec2> arrayList = new ArrayList<>(coordinates.length);
        for (String coordinate : coordinates) {
            arrayList.add(toVector2(coordinate.split(",")));
        }
        return arrayList;
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Vec2 vec2 = (Vec2) other;
        return this.x == vec2.x && this.y == vec2.y;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
