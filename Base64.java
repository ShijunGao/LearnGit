import java.util.HashMap;


public class Base64 {
	private static HashMap<Integer, Character> dict = new HashMap<>();
	public static String encode(byte[] binaryData) {
		StringBuffer retstr = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < binaryData.length; i++) {
			sb.append(getstdstr(binaryData[i]));
		}
		int len = sb.length() % 6;
		if (len != 0){
			for (int i = 0; i < 6 - len; i++) {
				sb.append("0");
			}
		}
		len = ((24 - (sb.length() % 24)) / 6) % 4;
		for (int i = 0; i < sb.length(); i += 6) {

			retstr.append(getcode(getsubstring(sb,i)));
		}
		for (int i = 0; i < len; i++) {
			retstr.append("=");
		}
		return retstr.toString();
	}

	private static String getsubstring(StringBuffer sb, int index) {
		StringBuffer sb1 = new StringBuffer();
		for (int i = 0; i <= 5 ; i++) {
			sb1.append(sb.charAt(index + i));
		}
		return sb1.toString();
	}

	private static char getcode(String s) {
		int num = Integer.parseInt(s, 2);
		return dict.get(num);
	}

	private static String getstdstr(byte binaryDatum) {
		String ss = Integer.toBinaryString(binaryDatum & 0xff);
		if (ss.length() == 8){
			return   ss;
		}else {
			StringBuffer sb = new StringBuffer();
			int len = ss.length();
			for (int i = 0; i < 8 - len; i++) {
				sb.append("0");
			}
			return sb.toString() + ss;
		}
	}

	public static byte[] decode(String s) {
		int len = s.indexOf('=');
		String ss = s;
		if (len != -1){
			 ss = s.substring(0,len);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ss.length(); i++) {
			sb.append(getbytenum(ss.charAt(i)));
		}
		len = sb.length() / 8;
		ss = sb.substring(0, len*8);
		byte[] retbyte = new byte[len];
		int k = 0;
		byte mm;
		int index = 0;
		for (int i = 0; i < len; i++) {
			mm = (byte) Integer.parseInt(ss.substring(index, index+8), 2);
			System.out.println(ss.substring(index, index+8));
			System.out.println(mm);
			retbyte[k++] = mm;
			index += 8;
		}
		return retbyte;
	}

	private static String getbytenum(char c) {
		int num = 0;
		for (int i = 0; i < dict.size(); i++) {
			num = i;
			if (dict.get(i) == c){
				break;
			}
		}
		String retstr = Integer.toBinaryString(num);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6 - retstr.length(); i++) {
			sb.append('0');
		}
		return sb.toString() + retstr;
	}

	public static void main(String[] args) {
		initdict();
		byte[] a = { 1, 2, 3, -7, -9, 110};
		String s = encode(a);
		System.out.println(s);
		byte[] b = decode(s);
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();
	}

	private static void initdict() {
		for (int i = 0; i <= 25; i++) {
			dict.put(i, (char) ('A'+i));
		}

		for (int i = 26; i <= 51 ; i++) {
			dict.put(i, (char)('a'+i-26));
		}

		for (int i = 52; i <= 61 ; i++) {
			dict.put(i, (char)('0'+i-52));
		}
		dict.put(62,'+');
		dict.put(63,'/');
	}
}