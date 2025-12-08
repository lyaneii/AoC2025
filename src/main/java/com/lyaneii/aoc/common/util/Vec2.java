package com.lyaneii.aoc.common.util;

import java.util.Objects;

public class Vec2 {
    public int x;
    public int y;
    
    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
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
