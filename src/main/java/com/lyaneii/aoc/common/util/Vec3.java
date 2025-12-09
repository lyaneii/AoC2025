package com.lyaneii.aoc.common.util;

import java.util.Objects;

public class Vec3 {
    public int x;
    public int y;
    public int z;

    public Vec3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vec3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }

    public void subtract(Vec3 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
    }

    public void scale(int scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
    }

    public static Vec3 add(Vec3 lhs, Vec3 rhs) {
        return new Vec3(lhs.x + rhs.x, lhs.y + rhs.y, lhs.z + rhs.z);
    }
    
    public static Vec3 subtract(Vec3 lhs, Vec3 rhs) {
        return new Vec3(lhs.x - rhs.x, lhs.y - rhs.y, lhs.z - rhs.z);
    }
    
    public static Vec3 scale(Vec3 vec, int scalar) {
        return new Vec3(vec.x * scalar, vec.y * scalar, vec.z * scalar);
    }
    
    public static float magnitudeFloat(Vec3 vec) {
        return (float) Math.sqrt(distanceSquared(vec));
    }
    
    public static double magnitude(Vec3 vec) {
        return Math.sqrt(distanceSquared(vec));
    }
    
    public static long distanceSquared(Vec3 vec) {
        long x = (long)vec.x * vec.x;
        long y = (long)vec.y * vec.y;
        long z = (long)vec.z * vec.z;
        return x + y + z;
    }
        
    public static long distanceSquared(Vec3 a, Vec3 b) {
        return distanceSquared(subtract(b, a));
    }
    
    public static float distanceFloat(Vec3 a, Vec3 b) {
        return magnitudeFloat(subtract(b, a));
    }
    
    public static double distance(Vec3 a, Vec3 b) {
        return magnitude(subtract(b, a));
    }
    
    public static Vec3 toVector3(String[] coordinateStringArray) {
        return new Vec3(
            Integer.parseInt(coordinateStringArray[0]),
            Integer.parseInt(coordinateStringArray[1]),
            Integer.parseInt(coordinateStringArray[2])
        );
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Vec3 vec2 = (Vec3) other;
        return this.x == vec2.x && this.y == vec2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
    
    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
}
