/*
 * Part of the Athena 3 Project.
 *
 * (C) 2011 D&N
 */

package stanhebben.minetweaker.util;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * DataInputStream implementation which reads from a byte array and doesn't
 * throw IO exceptions. (only an ArrayIndexOutOfBoundsException if you read
 * past the end of the stream)
 *
 * @author Stan Hebben
 */
public class DataArrayInputStream {
    private byte[] data;
    private int index;

    public DataArrayInputStream(byte[] data) {
        this.data = data;
        index = 0;
    }

    public boolean readBoolean() {
        return readByte() != 0;
    }

    public byte readByte() {
        return data[index++];
    }

    public short readShort() {
        int b0 = data[index++] & 0xFF;
        int b1 = data[index++] & 0xFF;
        return (short)((b0 << 8) | b1);
    }

    public int readInt() {
        int b0 = data[index++] & 0xFF;
        int b1 = data[index++] & 0xFF;
        int b2 = data[index++] & 0xFF;
        int b3 = data[index++] & 0xFF;
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    public long readLong() {
        long i0 = readInt() & 0xFFFFFFFFL;
        long i1 = readInt() & 0xFFFFFFFFL;
        return (i0 << 32) | i1;
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public String readUTF() {
        int size = readInt();
        String result = new String(data, index, size, Charset.forName("UTF-8"));
        index += size;
        return result;
    }

    public byte[] readBytes(int size) {
        byte[] result = Arrays.copyOfRange(data, index, index + size);
        index += size;
        return result;
    }

    public byte[] readByteArray() {
        int size = readInt();
        return readBytes(size);
    }
}
