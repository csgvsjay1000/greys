package ivg.cn.greys.agent;

import java.lang.instrument.Instrumentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgentLauncher {

	
	private static Logger LOG = LogManager.getLogger(AgentLauncher.class);
	
	public static void premain(String args,Instrumentation inst) {
		main(args, inst);
	}

	public static void agentmain(String args,Instrumentation inst) {
		main(args, inst);
	}
	
	private static synchronized void main(final String args, final Instrumentation inst) {
		try {
			
			System.out.println(AgentLauncher.class.getProtectionDomain());
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("hello");
						LOG.info("hello");
					}
				}
			},"AgentLauncher_1").start();
			
			
		} catch (Throwable e) {
			
		}
	}
	
}
