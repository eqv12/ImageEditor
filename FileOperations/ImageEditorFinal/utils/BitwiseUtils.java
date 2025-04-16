package ImageEditorFinal.utils;

public class BitwiseUtils {
    public static int readLittleEndian(byte data[], int offset){
        int first = data[offset] & 0xFF;
        int second = (data[offset+1] & 0xFF) << 8;
        int third = (data[offset+2] & 0xFF) << 16;
        int fourth = (data[offset+3] & 0xFF) << 24;

        int ans = first | second | third | fourth;
        return ans;
    }

    public static void writeLittleEndian(int x, byte data[], int offset){
        byte fourth = (byte) ((x & 0b11111111_00000000_00000000_00000000) >>> 24);
        byte third = (byte) ((x & 0b00000000_11111111_00000000_00000000) >>> 16);
        byte second = (byte) ((x & 0b00000000_00000000_11111111_00000000) >>> 8);
        byte first = (byte) ((x & 0b00000000_00000000_00000000_11111111));

        data[offset] = first;
        data[offset+1] = second;
        data[offset+2] = third;
        data[offset+3] = fourth;
    }
}
