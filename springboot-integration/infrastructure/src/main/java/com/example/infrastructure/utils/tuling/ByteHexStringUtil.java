package com.example.infrastructure.utils.tuling;

public final class ByteHexStringUtil {
	private ByteHexStringUtil() {
	}

	public static String byte2HexString(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		String tmp;
		for (int i = 0; i < bytes.length; i++) {
			tmp = Integer.toHexString(bytes[i] & 0xFF);
			if (tmp.length() == 1) {
                builder.append("0").append(tmp);
            } else {
                builder.append(tmp);
            }
		}
		return builder.toString().toUpperCase();
	}

	public static byte[] hexString2Byte(String str) {
		byte[] src = str.getBytes();
		int len = src.length;
		if ((len % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
		byte[] b = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			String item = new String(src, i, 2);
			b[i / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b;
	}

	/*
	public static void main(String[] args) {
		String originStr = "ab6364ed666820";
		byte[] b = hexString2Byte(originStr);
		String str = byte2HexString(b);
		System.out.println(str);
	}
	*/

}
