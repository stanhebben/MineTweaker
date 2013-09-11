/*
 * Part of the Athena 3 Project.
 *
 * (C) 2011 D&N
 */

package stanhebben.minetweaker.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * DataOutputStream which writes data to a byte array and doesn't throw
 * IOExceptions.
 * 
 * @author Stan Hebben
 */
public class DataArrayOutputStream {
    private byte[] data;
    private int length;

    public DataArrayOutputStream() {
        data = new byte[16];
    }

    public void write(byte[] value) {
        while (length + value.length > data.length) expand();
        for (byte b : value) data[length++] = b;
    }

    public void writeByte(int value) {
        if (length + 1 > data.length) expand();
        data[length++] = (byte)value;
    }

    public void writeShort(int value) {
        if (length + 2 > data.length) expand();
        data[length++] = (byte)(value >> 8);
        data[length++] = (byte)(value);
    }

    public void writeInt(int value) {
        if (length + 4 > data.length) expand();
        data[length++] = (byte)(value >> 24);
        data[length++] = (byte)(value >> 16);
        data[length++] = (byte)(value >> 8);
        data[length++] = (byte)(value);
    }

    public void writeLong(long value) {
        if (length + 8 > data.length) expand();
        data[length++] = (byte)(value >> 56);
        data[length++] = (byte)(value >> 48);
        data[length++] = (byte)(value >> 40);
        data[length++] = (byte)(value >> 32);
        data[length++] = (byte)(value >> 24);
        data[length++] = (byte)(value >> 16);
        data[length++] = (byte)(value >> 8);
        data[length++] = (byte)(value);
    }

    public void writeFloat(float value) {
        writeInt(Float.floatToIntBits(value));
    }

    public void writeDouble(double value) {
        writeLong(Double.doubleToLongBits(value));
    }

    public void writeByteArray(byte[] data) {
        writeInt(data.length);
        write(data);
    }

    public void writeUTF(String str) {
        try {
            byte[] value = str.getBytes("UTF-8");
            writeByteArray(value);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(data, length);
    }

    private void expand() {
        data = Arrays.copyOf(data, data.length * 2);
    }
}
