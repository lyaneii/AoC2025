package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.util.DSU;
import com.lyaneii.aoc.common.util.Pair;
import com.lyaneii.aoc.common.util.Vec3;

import java.util.*;
import java.util.Map.Entry;

public class Day8 extends Day {

    public Day8() {
        super(8, "Day 8");
    }
    
    private static class ElectricityGrid {
        private final DSU connections;
        private final ArrayList<Vec3> junctionBoxes;
        private final PriorityQueue<Entry<Long, Pair<Integer>>> distances;
        private final int maxConnections;
        private static final int INPUT_MAX_CONNECTIONS = 1000;
        private static final int EXAMPLE_MAX_CONNECTIONS = 10;
        
        public ElectricityGrid(String[] junctionBoxCoordinates) {
            junctionBoxes = new ArrayList<>();
            distances = new PriorityQueue<>(Entry.comparingByKey());
            connections = new DSU(junctionBoxCoordinates.length);
            if (junctionBoxCoordinates.length > 20) {
                maxConnections = INPUT_MAX_CONNECTIONS;
            } else {
                maxConnections = EXAMPLE_MAX_CONNECTIONS;
            }
            for (String junctionBoxCoordinate : junctionBoxCoordinates) {
                String[] coordinate = junctionBoxCoordinate.split(",");
                junctionBoxes.add(Vec3.toVector3(coordinate));
            }
            computeDistances();
        }
        
        private void computeDistances() {
            for (int i = 0; i < junctionBoxes.size() - 1; ++i) {
                var currentBox = junctionBoxes.get(i);
                for (int j = i + 1; j < junctionBoxes.size(); ++j) {
                    var connectingBox = junctionBoxes.get(j);
                    var distance = Vec3.distanceSquared(currentBox, connectingBox);
                    var entry = new AbstractMap.SimpleEntry<>(distance, new Pair<>(i, j));
                    distances.offer(entry);
                }
            }
        }
        
        public Pair<Integer> getLastConnection() {
            while (!distances.isEmpty()) {
                var connection = distances.poll().getValue();
                connections.union(connection.first, connection.second);
                if (connections.count == 1) {
                    return connection;
                }
            }
            return null;
        }
        
        public long calculateThreeLargestCircuits() {
            for (int i = 0; i < maxConnections; ++i) {
                var connection = distances.poll().getValue();
                connections.union(connection.first, connection.second);
            }

            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < connections.size.length; ++i) {
                arrayList.add(connections.size[i]);
            }
            arrayList.sort(Comparator.reverseOrder());
            return (long)arrayList.get(0) * arrayList.get(1) * arrayList.get(2);
        }
    }
    
    @Override
    public Object partOne() {
        String[] junctionBoxCoordinates = input.asStringArray();
        ElectricityGrid grid = new ElectricityGrid(junctionBoxCoordinates);
        return grid.calculateThreeLargestCircuits();
    }
    
    @Override
    public Object partTwo() {
        String[] junctionBoxCoordinates = input.asStringArray();
        ElectricityGrid grid = new ElectricityGrid(junctionBoxCoordinates);
        var connection = grid.getLastConnection();
        return (long)grid.junctionBoxes.get(connection.first).x * grid.junctionBoxes.get(connection.second).x;
    }
}
