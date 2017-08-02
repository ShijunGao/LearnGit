public class CalcProtocol

implements K{
public void service(Socket s){
	try{
		InputStream in=s.getInputStream();
		OutputStream out =s.getOutputStream();
		DataInputStream dis = new DataInputStream(in);
		DataOutputStream dos= new DataOutputStream(out);
		while(true){
			int command =dis.readInt();
			switch(command){
				case 1:
				dos.writeInt(dis.readInt()+dis.readInt());
				dos.flush();
				break;		
			}
		}
	}
	catch (Exception e){
	e.printStackTrace();
	
	}
	
}

}