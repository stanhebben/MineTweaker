package stanhebben.minetweaker.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOUtil {
	private IOUtil() {}
	
	public static String readFileAsString(File file) {
        try {
            int len;
            char[] chr = new char[4096];
            final StringBuffer buffer = new StringBuffer();
            final FileReader reader = new FileReader(file);
            try {
                while ((len = reader.read(chr)) > 0) {
                    buffer.append(chr, 0, len);
                }
            } finally {
                reader.close();
            }
            return buffer.toString();
        } catch (IOException ex) {
            return null;
        }
    }
	
	public static boolean writeFileString(File file, String value) {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(value);
			writer.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}
}
