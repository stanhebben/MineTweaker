/*
 * This file is subject to the license.txt file in the main folder
 * of this project.
 */

package stanhebben.minetweaker.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implements extra methods for arrays.
 *
 * @author Stan Hebben
 */
public class Arrays2 {
    private static final Random random = new Random();

    private Arrays2() {}
    
    /**
     * Randomly permutes this boolean array.
     *
     * @param values
     */
    public static void randomPermute(boolean[] values) {
        for (int i = values.length; i > 1; i--) {
            int r = random.nextInt(i);

            boolean tmp = values[i];
            values[i] = values[r];
            values[r] = tmp;
        }
    }

    /**
     * Randomly permutes this int array.
     *
     * @param values
     */
    public static void randomPermute(int[] values) {
        for (int i = values.length; i > 1; i--) {
            int r = random.nextInt(i);

            int tmp = values[i];
            values[i] = values[r];
            values[r] = tmp;
        }
    }

    /**
     * Randomly permutes this long array.
     *
     * @param values
     */
    public static void randomPermute(long[] values) {
        for (int i = values.length; i > 1; i--) {
            int r = random.nextInt(i);

            long tmp = values[i];
            values[i] = values[r];
            values[r] = tmp;
        }
    }

    /**
     * Randomly permutes this float array.
     *
     * @param values
     */
    public static void randomPermute(float[] values) {
        for (int i = values.length; i > 1; i--) {
            int r = random.nextInt(i);

            float tmp = values[i];
            values[i] = values[r];
            values[r] = tmp;
        }
    }

    /**
     * Randomly permutes this double array.
     *
     * @param values
     */
    public static void randomPermute(double[] values) {
        for (int i = values.length; i > 1; i--) {
            int r = random.nextInt(i);

            double tmp = values[i];
            values[i] = values[r];
            values[r] = tmp;
        }
    }

    /**
     * Calculates this minimum of this array.
     *
     * @param values
     * @return
     */
    public static int min(int[] values) {
        int result = Integer.MAX_VALUE;
        for (int i : values) if (i < result) result = i;
        return result;
    }

    /**
     * Calculates the maximum of this array.
     *
     * @param values
     * @return
     */
    public static int max(int[] values) {
        int result = Integer.MIN_VALUE;
        for (int i : values) if (i > result) result = i;
        return result;
    }

    /**
     * Calculates the sum of this array.
     *
     * @param values
     * @return
     */
    public static int sum(int[] values) {
        int result = 0;
        for (int i : values) result += i;
        return result;
    }

    /**
     * Calculates the average of this array.
     *
     * @param values
     * @return
     */
    public static double avg(int[] values) {
        return sum(values) / (double)values.length;
    }

    /**
     * Calculates the minimum of this array.
     *
     * @param values
     * @return
     */
    public static float min(float[] values) {
        float result = Float.MAX_VALUE;
        for (float f : values) if (f < result) result = f;
        return result;
    }

    /**
     * Calculates the maximum of this array.
     *
     * @param values
     * @return
     */
    public static float max(float[] values) {
        float result = Float.MIN_VALUE;
        for (float f : values) if (f < result) result = f;
        return result;
    }

    /**
     * Calculates the sum of this array.
     *
     * @param values
     * @return
     */
    public static float sum(float[] values) {
        float result = 0;
        for (float f : values) result += f;
        return result;
    }

    /**
     * Calculates the average of this array.
     *
     * @param values
     * @return
     */
    public static float avg(float[] values) {
        return sum(values) / values.length;
    }

    /**
     * Calculates the minimum of this array.
     *
     * @param values
     * @return
     */
    public static double min(double[] values) {
        double result = Double.MAX_VALUE;
        for (double d : values) if (d < result) result = d;
        return result;
    }

    /**
     * Calculates the maximum of this array.
     *
     * @param values
     * @return
     */
    public static double max(double[] values) {
        double result = Double.MIN_VALUE;
        for (double d : values) if (d > result) result = d;
        return result;
    }

    /**
     * Calculates the sum of this array.
     *
     * @param values
     * @return
     */
    public static double sum(double[] values) {
        double result = 0;
        for (double d : values) result += d;
        return result;
    }

    /**
     * Calculates the average of this array.
     *
     * @param values
     * @return
     */
    public static double avg(double[] values) {
        return sum(values) / values.length;
    }

    /**
     * Converts an array to a string by joining it with the specified delimiter.
     *
     * @param values values to join
     * @param delimiter string delimiter
     * @return joined string
     */
    public static String join(int[] values, String delimiter) {
        if (values.length == 0) return "";
        StringBuilder result = new StringBuilder();
        result.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            result.append(delimiter);
            result.append(values[i]);
        }
        return result.toString();
    }

    /**
     * Converts an array to a string by joining it with the specified delimiter.
     *
     * @param values values to join
     * @param delimiter string delimiter
     * @return joined string
     */
    public static String join(int[] values, int from, int to, String delimiter) {
        if (values.length == 0 || from == to) return "";
        StringBuilder result = new StringBuilder();
        result.append(values[from]);
        for (int i = from + 1; i < to; i++) {
            result.append(delimiter);
            result.append(values[i]);
        }
        return result.toString();
    }

    /**
     * Converts an array to a string by joining it with the specified delimiter.
     *
     * @param values values to join
     * @param delimiter string delimiter
     * @return joined string
     */
    public static String join(String[] values, String delimiter) {
        if (values.length == 0) return "";
        StringBuilder result = new StringBuilder();
        result.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            result.append(delimiter);
            result.append(values[i]);
        }
        return result.toString();
    }

    /**
     * Converts an array to a string by joining it with the specified delimiter.
     *
     * @param values values to join
     * @param delimiter string delimiter
     * @return joined string
     */
    public static String join(String[] values, int from, int to, String delimiter) {
        if (values.length == 0 || from == to) return "";
        StringBuilder result = new StringBuilder();
        result.append(values[from]);
        for (int i = from + 1; i < to; i++) {
            result.append(delimiter);
            result.append(values[i]);
        }
        return result.toString();
    }
    
    /**
     * Splits a string in parts, given a specified delimiter.
     * 
     * @param value string to be split
     * @param delimiter delimiter
     * @return 
     */
    public static String[] split(String value, String delimiter) {
        ArrayList<String> result = new ArrayList<String>();
        char[] cdelimiter = delimiter.toCharArray();
        int start = 0;
        outer: for (int i = 0; i <= value.length() - delimiter.length(); i++) {
            for (int j = 0; j < delimiter.length(); j++) {
                if (value.charAt(i + j) != cdelimiter[j]) continue outer;
            }
            result.add(value.substring(start, i));
            start = i + delimiter.length();
            i += delimiter.length() - 1;
        }
        result.add(value.substring(start));
        return result.toArray(new String[result.size()]);
    }
    
    /**
     * Splits a string in parts, given a specified delimiter.
     * 
     * @param value string to be split
     * @param delimiter delimiter
     * @return 
     */
    public static String[] split(String value, char delimiter) {
        ArrayList<String> result = new ArrayList<String>();
        int start = 0;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == delimiter) {
                result.add(value.substring(start, i));
                start = i + 1;
            }
        }
        result.add(value.substring(start));
        return result.toArray(new String[result.size()]);
    }
}
