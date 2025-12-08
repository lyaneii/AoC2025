package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.Input;
import com.lyaneii.aoc.common.util.GridUtils;
import com.lyaneii.aoc.common.util.StringUtils;
import com.lyaneii.aoc.common.util.Vec2;

import java.util.HashMap;
import java.util.HashSet;

public class Day7 extends Day {
    private static final char STARTING_POINT = 'S';
    private static final char SPLITTER = '^';
    private static final char MARKED_SPLITTER = 'X';
    
    public Day7() {
        super(7, "Day 7");
    }
    
    private static class TachyonManifold {
        public String[] diagram;
        public String[] markedSplitters;
        HashMap<Vec2, Integer> visited;
        public int timesParticleReachedEnd;
        public Vec2 startPoint;
        
        public TachyonManifold(String[] diagram) {
            this.diagram = diagram;
            timesParticleReachedEnd = 0;
            visited = new HashMap<>();
        }
        
        public void markSplitter(int row, int splitterLocation) {
            StringBuilder markedSplitter = new StringBuilder(markedSplitters[row]);
            markedSplitter.setCharAt(splitterLocation, MARKED_SPLITTER);
            markedSplitters[row] = markedSplitter.toString();
        }
    }

    private static int findStartIndex(String startRow) {
        return startRow.indexOf(STARTING_POINT);
    }
    
    private static TachyonManifold parseInput(Input input) {
        char[][] diagram = input.asCharacterGrid();
        Vec2 startPoint = new Vec2(0, findStartIndex(new String(diagram[0])));
        diagram = GridUtils.transposeGrid(diagram);
        TachyonManifold manifold = new TachyonManifold(GridUtils.toStringArray(diagram));
        manifold.startPoint = startPoint;
        manifold.markedSplitters = manifold.diagram.clone();
        return manifold;
    }
    
    private static int followTimeline(TachyonManifold quantumManifold, Vec2 particle) {
        if (quantumManifold.visited.containsKey(particle)) {
            return quantumManifold.visited.get(particle);
        }
        
        String row = quantumManifold.diagram[particle.y];
        int splitLocation = row.indexOf(SPLITTER, particle.x);
        if (splitLocation == -1) {
            return 1;
        }
        
        int result = 0;
        Vec2 left = new Vec2(splitLocation, particle.y - 1);
        result += followTimeline(quantumManifold, left);
        
        Vec2 right = new Vec2(splitLocation, particle.y + 1);
        result += followTimeline(quantumManifold, right);
        
        quantumManifold.visited.put(particle, result);
        return result;
    }
    
    @Override
    public Object partOne() {
        TachyonManifold manifold = parseInput(input);
        HashSet<Vec2> tachyonBeams = new HashSet<>();
        tachyonBeams.add(manifold.startPoint);
        while (!tachyonBeams.isEmpty()) {
            HashSet<Vec2> splitBeams = new HashSet<>();
            for (Vec2 tachyonBeam : tachyonBeams) {
                String row = manifold.diagram[tachyonBeam.y];
                int beamSplitLocation = row.indexOf(SPLITTER, tachyonBeam.x);
                if (beamSplitLocation != -1) {
                    manifold.markSplitter(tachyonBeam.y, beamSplitLocation);
                    
                    Vec2 left = new Vec2(beamSplitLocation, tachyonBeam.y - 1);
                    Vec2 right = new Vec2(beamSplitLocation, tachyonBeam.y + 1);
                    splitBeams.add(left);
                    splitBeams.add(right);
                }
            }
            tachyonBeams = splitBeams;
        }
        return StringUtils.countOccurrences(manifold.markedSplitters, MARKED_SPLITTER);
    }

    @Override
    public Object partTwo() {
        TachyonManifold manifold = parseInput(input);
        manifold.timesParticleReachedEnd = followTimeline(manifold, manifold.startPoint);
        return manifold.timesParticleReachedEnd;
    }
}
