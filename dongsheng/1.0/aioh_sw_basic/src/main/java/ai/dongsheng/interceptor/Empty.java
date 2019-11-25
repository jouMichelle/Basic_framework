package ai.dongsheng.interceptor;


import java.util.Collection;

/**
 * Created by gaojun on 17-2-6.
 */
public class Empty {
    public static final byte[] BYTE = new byte[0];
    public static final char[] CHAR = new char[0];
    public static final short[] SHORT = new short[0];
    public static final int[] INT = new int[0];
    public static final long[] LONG = new long[0];
    public static final float[] FLOAT = new float[0];
    public static final double[] DOUBLE = new double[0];
    public static final boolean[] BOOLEAN = new boolean[0];
    public static final Object[] OBJECT = new Object[0];
    public static final String STRING = "";

    public static final String[] STRINGS = new String[0];

    public static byte[] empty(byte[] src) { return src == null ? BYTE : src; }
    public static char[] empty(char[] src) { return src == null ? CHAR : src; }
    public static short[] empty(short[] src) { return src == null ? SHORT : src; }
    public static int[] empty(int[] src) { return src == null ? INT : src; }
    public static long[] empty(long[] src) { return src == null ? LONG : src; }
    public static float[] empty(float[] src) { return src == null ? FLOAT : src; }
    public static double[] empty(double[] src) { return src == null ? DOUBLE : src; }
    public static boolean[] empty(boolean[] src) { return src == null ? BOOLEAN : src; }
    public static Object[] empty(Object[] src) { return src == null ? OBJECT : src; }
    public static String[] empty(String[] src) { return src == null ? STRINGS : src; }

    public static String nullable(String src) { return src == null || src.isEmpty() ? null : src; }
    public static <T> T[] nullable(T[] src) { return src == null || src.length == 0 ? null : src; }

    public static boolean isBlank(String src) { return src == null || src.isEmpty() || src.trim().isEmpty(); }

    public static boolean isEmpty(String src) { return src == null || src.isEmpty(); }
    public static boolean isEmpty(byte[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(char[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(short[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(int[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(long[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(float[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(double[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(boolean[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(Object[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(String[] src) { return src == null || src.length == 0; }
    public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection src) { return src == null || src.isEmpty(); }
}
