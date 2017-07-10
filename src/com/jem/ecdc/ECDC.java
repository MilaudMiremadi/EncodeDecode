package com.jem.ecdc;

public class ECDC {

	public static void main(String[] args) {
		Matrix mat = new Matrix(2, -17, 11, -1, 11, -7, 0, 3, -2);
		System.out.println(encode("Hello, world!", mat));
		System.out.println(decode("-1.00 17.00 -11.00 2.00 8.00 -5.00 5.00 -1.00 1.00 8.00 -10.00 7.00 11.00 -19.00 13.00 14.00 -28.00 19.00 17.00 -37.00 25.00 20.00 -46.00 31.00 23.00 -55.00 37.00 26.00 -64.00 43.00 29.00 -73.00 49.00 32.00 -82.00 55.00 35.00 -91.00 61.00 38.00 -100.00 67.00 41.00 -109.00 73.00 44.00 -118.00 79.00 47.00 -127.00 85.00 50.00 -136.00 91.00 53.00 -145.00 97.00 56.00 -154.00 103.00 59.00 -163.00 109.00 62.00 -172.00 115.00 65.00 -181.00 121.00 68.00 -190.00 127.00 71.00 -199.00 133.00 74.00 -208.00 139.00 77.00 -217.00 145.00 80.00 -226.00 151.00 83.00 -235.00 157.00 86.00 -244.00 163.00 89.00 -529.00 353.00", mat));
	}

	private static final String TABLE = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}\\\"|;:/?.>,<";

	private static String encode(String string, Matrix encodingMatrix) {
		while (!divisibleBy3(string.length())) {
			string += " ";
		}
		char[] chars = new char[string.length()];
		string.getChars(0, string.length(), chars, 0);
		Vector[] v = new Vector[string.length() / 3];
		int counter = 0;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			if (counter == 2) {
				v[index] = new Vector(TABLE.indexOf(chars[i - 2]), TABLE.indexOf(chars[i - 1]), TABLE.indexOf(chars[i]));
				counter = -1;
				index++;
			}
			counter++;
		}
		for (int i = 0; i < v.length; i++) {
			v[i] = encodingMatrix.multiply(v[i]);
		}
		String encodedMessage = "";
		for (Vector vec : v) {
			encodedMessage += " " + vec.toString();
		}
		return encodedMessage;
	}

	private static String decode(String string, Matrix decodingMatrix) {
		String[] split = string.split("\\s+");
		float[] values = new float[string.length()];
		for (int i = 0; i < split.length; i++) {
			if (!split[i].equals("")) {
				values[i] = Float.parseFloat(split[i]);
			}
		}
		int zeroCount = countZeros(values);
		float[] removedZeros = new float[values.length - zeroCount];
		int index = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != 0) {
				removedZeros[index++] = values[i];
			}
		}
		Vector[] v = new Vector[removedZeros.length / 3];
		int counter = 0;
		int index2 = 0;
		for (int i = 0; i < removedZeros.length; i++) {
			if (counter == 2) {
				v[index2] = new Vector(removedZeros[i - 2], removedZeros[i - 1], removedZeros[i]);
				counter = -1;
				index2++;
			}
			counter++;
		}
		Matrix inverse = decodingMatrix.inverse();
		Vector[] decoded = new Vector[v.length];
		for (int i = 0; i < decoded.length; i++) {
			decoded[i] = inverse.multiply(v[i]);
		}

		String result = "";

		for (int i = 0; i < decoded.length; i++) {
			result += TABLE.charAt((int) decoded[i].x);
			result += TABLE.charAt((int) decoded[i].y);
			result += TABLE.charAt((int) decoded[i].z);
		}

		return result;
	}

	private static int countZeros(float[] values) {
		int count = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] == 0.0f) {
				count++;
			}
		}
		return count;
	}

	private static boolean divisibleBy3(int n) {
		if (n < 0) {
			n = -n;
		}

		while (n > 0) {
			n -= 3;
		}

		return n == 0;
	}

}
