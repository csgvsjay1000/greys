package ivg.cn.greys.core;

public class TestProgram {

	
	public static void main(String[] args) {
		while (true) {
			try {
				Thread.sleep(1000);
				System.out.println("hello");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
