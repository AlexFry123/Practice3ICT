import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		doSomething();
		System.out.println("Hello i'm Java-project for ICT!");
		String str = "automata";
		int a = 16256;
		//System.out.println(a |= 0x80 >> (4 & 0x7));
		System.out.println(Integer.toBinaryString(a));
		System.out.println(VBEncode(a));
		String tmp = VBEncode(a);
		byte[] data = new byte[tmp.length() / 8];
		for (int i = 0; i < tmp.length(); i++) {
			char c = tmp.charAt(i);
			if (c == '1') {
				data[i >> 3] |= 0x80 >> (i & 0x7);
			} else if (c != '0') {
				throw new IllegalArgumentException("Invalid char in binary string");
			}
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream("D:\\OKA\\IR\\Compressionindexes.bin"));
		for(int k =0; k<15; k++) {
		int read = br.read();
		System.out.println(Integer.toBinaryString(read));
		}
		String resu = "";
		/*for(int i = 0; i<read.length; i++) {
			System.out.println(read[i]);
			String temp = Integer.toBinaryString(read[i]);
			if(i == read.length-1) {
			System.out.println(temp);
			System.out.println(temp.substring(temp.length()-8));
			resu += temp.substring(temp.length()-8);
			System.out.println();
			break;
			}
			int shift = 0;
			if(temp.length()%8 != 0) {
				shift = 8 - (temp.length()%8);
			}
			String tst = "";
			for(int j = 0; j<shift; j++) {
				tst += '0'; 
			}
			tst += temp;
			resu += tst;
		}*/
		System.out.println(resu + " " + resu.length());
	}

	private static void doSomething(){
		int x = 5;
		String string = "123";
		System.out.println("Hello World");
		System.out.println(x + string);
	}
	
	private static String VBEncode(int num) {
		StringBuilder varcode = new StringBuilder();
		String[] bytes = bytesBlock(Integer.toBinaryString(num));
		for (int i = 0; i < bytes.length - 1; i++)
			varcode.append('0').append(bytes[i]);
		varcode.append('1').append(bytes[bytes.length - 1]);
		return varcode.toString();
	}

	/*
	 * Split binary string on blocks with 7 bits. Example: ---> "11111110000000"
	 * ---> { "1111111", "0000000" }
	 */
	private static String[] bytesBlock(String binary) {
		String[] bytes = new String[binary.length() / 8 + 1];
		int length = binary.length();
		int shift = (length % 7 == 0) ? 7 : length % 7;
		StringBuilder first = new StringBuilder();
		for (int i = 0; i < 7 - shift; i++)
			first.append('0');
		bytes[0] = first.append(binary.substring(0, shift)).toString();
		for (int i = 1; i < bytes.length; i++)
			bytes[i] = binary.substring(shift + (i - 1) * 7, shift + i * 7);
		return bytes;
	}
	
}
