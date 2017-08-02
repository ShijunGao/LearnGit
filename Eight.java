import java.io.*;
import java.util.Vector;
public class Eight {
	public static String GetFile(String filePath) throws IOException{
		Vector<Character> f = new Vector<Character>();
		FileReader filereader=new FileReader(filePath);
		String s=new String();
		int ch=0;
		char[]buffer=new char[1024];
		while((ch=filereader.read())!=-1){
			f.add((char)ch);
		}
		filereader.close();
		for(char c: f){
			if(c=='0'||c=='1')
				s+=c;
		}
		return s;
		//ch=filereader.read()
		//ch=filereader.read(buffer);
		
	}
	public  static int GetMost(String filepath)throws IOException{
		String s=GetFile(filepath);
		int n=(int)Math.sqrt(s.length());
		int rowMax=0,lineMax=0,LeftparMax=0,RightparMax=0;
		char tab[][]=new char[n][n];
		int i=0,j=0;
		for(i = 0;i<n;i++){
			for(j = 0;j<n;j++){
				tab[i][j]=s.charAt(i*n+j);
			}
		}
		for(i=0;i<n;i++){
			int row=0;
			for(j=0;j<n;j++){
				if(tab[i][j]=='1')
					row++;
			}
			rowMax=Math.max(rowMax, row);
			
		}
		for ( i = 0; i < n; i++) {
			int line = 0;
			for (j = 0; j < n; j++) {
				if (tab[j][i] == '1'){
					line++;
				}
			}
			lineMax = Math.max(lineMax, line);
		}
		for(int num=0;num<2*n;num++){
			int leftpaw=0;
			for(i=0;i<n;i++){
				for(j=0;j<n;j++){
					if((tab[i][j]=='1')&&(i+j==num))
						leftpaw++;
				}
			}
			LeftparMax=Math.max(leftpaw, LeftparMax);
		}
		for(int num=-n;num<n;num++){
			int rightpaw=0;
			for(i=0;i<n;i++){
				for(j=0;j<n;j++){
					if((tab[i][j]=='1')&&(i-j==num))
						rightpaw++;
				}
			} 
			LeftparMax=Math.max(rightpaw, RightparMax);
		}
		return (Math.max(Math.max(rowMax,lineMax),Math.max(LeftparMax,RightparMax)));
	}
	public static void main(String args[])throws Exception{
		String s=new String("G:\\a.txt");
		int a=GetMost(s);
		System.out.println(a);
}
}
	