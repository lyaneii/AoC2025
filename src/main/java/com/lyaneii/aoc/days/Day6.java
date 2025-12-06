package com.lyaneii.aoc.days;

import com.lyaneii.aoc.common.Day;
import com.lyaneii.aoc.common.Input;
import com.lyaneii.aoc.common.util.GridUtils;
import com.lyaneii.aoc.common.util.StringUtils;

import java.util.ArrayList;

public class Day6 extends Day {

    public Day6() {
        super(6, "Day 6: Trash Compactor");
    }

    private static class CalculationWorksheet {
        private interface MathOperation {
            long apply(long lhs, long rhs);
        }

        private static class Add implements MathOperation {
            @Override
            public long apply(long lhs, long rhs) {
                return lhs + rhs;
            }
        }

        private static class Multiply implements MathOperation {
            @Override
            public long apply(long lhs, long rhs) {
                return lhs * rhs;
            }
        }

        private static final String MATH_OPERATORS = "+*";
        public ArrayList<Long> numbers;
        public MathOperation operator;

        public CalculationWorksheet() {
            numbers = new ArrayList<>();
        }

        public long calculate() {
            long result = numbers.getFirst();
            for (int i = 1; i < numbers.size(); ++i) {
                result = operator.apply(result, numbers.get(i));
            }
            return result;
        }

        public static boolean isMathOperator(String operator) {
            return MATH_OPERATORS.contains(operator);
        }

        public static boolean isMathOperator(char operator) {
            return MATH_OPERATORS.indexOf(operator) != -1;
        }

        public void assignMathOperator(char ch) {
            if (ch == '+') {
                operator = new Add();
            } else {
                operator = new Multiply();
            }
        }
    }


    private static CalculationWorksheet[] parseInput(Input input) {
        String[][] mathProblemArrangement = input.asStringGrid();
        CalculationWorksheet[] workSheet = new CalculationWorksheet[mathProblemArrangement[0].length];

        for (int i = 0; i < workSheet.length; ++i) {
            workSheet[i] = new CalculationWorksheet();
        }

        for (String[] horizontalArrangement : mathProblemArrangement) {
            for (int j = 0; j < horizontalArrangement.length; ++j) {
                if (CalculationWorksheet.isMathOperator(horizontalArrangement[j])) {
                    workSheet[j].assignMathOperator(horizontalArrangement[j].charAt(0));
                } else {
                    workSheet[j].numbers.add(Long.parseLong(horizontalArrangement[j]));
                }
            }
        }

        return workSheet;
    }
    
    private static int getTotalAmountOfWorkSheets(char[][] mathHomework) {
        String horizontalArrangement = new String(mathHomework[0]).trim();
        return horizontalArrangement.split(" +").length;
    }

    private static CalculationWorksheet[] parseAsCephalopodInput(Input input) {
        char[][] mathHomework = input.asCharacterGrid();
        int totalWorkSheets = getTotalAmountOfWorkSheets(mathHomework);
        CalculationWorksheet[] workSheets = new CalculationWorksheet[totalWorkSheets];

        for (int i = 0; i < workSheets.length; ++i) {
            workSheets[i] = new CalculationWorksheet();
        }

        int workSheetIndex = 0;
        for (int i = 0; i < mathHomework[0].length; ++i) {
            char[] column = GridUtils.getColumn(mathHomework, i);
            if (new String(column).trim().isEmpty()) {
                ++workSheetIndex;
                continue;
            }

            char operator = column[column.length - 1];
            if (CalculationWorksheet.isMathOperator(operator)) {
                workSheets[workSheetIndex].assignMathOperator(operator);
            }

            long number = StringUtils.findFirstLong(new String(column));
            workSheets[workSheetIndex].numbers.add(number);
        }


        return workSheets;
    }

    @Override
    public Object partOne() {
        CalculationWorksheet[] mathProblems = parseInput(input);
        long result = 0;
        for (var mathProblem : mathProblems) {
            result += mathProblem.calculate();
        }
        return result;
    }

    @Override
    public Object partTwo() {
        CalculationWorksheet[] mathProblems = parseAsCephalopodInput(input);
        long result = 0;
        for (var mathProblem : mathProblems) {
            result += mathProblem.calculate();
        }
        return result;
    }
}
