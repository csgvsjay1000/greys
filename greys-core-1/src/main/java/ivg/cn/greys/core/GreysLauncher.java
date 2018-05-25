package ivg.cn.greys.core;

import java.io.IOException;
import java.util.List;

import ivg.cn.greys.core.util.GaStringUtils;

public class GreysLauncher {

	
	public GreysLauncher(String[] args) throws Exception {
		// 1、解析配置文件，获取pid
		// 2、加载agent，加载代理程序
		
		attachAgent();
	}
	
	public void attachAgent() throws Exception {
		// 1、获取应用类加载器
		// 2、获取VirtualMachine 实例，用于将代理程序添加到目标jvm
		
		String targetPID = "27124";
		String agentJar = "E:\\java_work\\githup_rebuild\\mycat\\greys\\greys-agent-1\\target\\greys-core-jar-with-dependencies.jar";
		String coreJar = "xx";
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Class<?> vmdClass = classLoader.loadClass("com.sun.tools.attach.VirtualMachineDescriptor");
		Class<?> vmClass = classLoader.loadClass("com.sun.tools.attach.VirtualMachine");
		
		Object targetVMD = null;
		// 遍历所有java程序
		for(Object obj : (List<?>)(vmClass.getMethod("list", (Class<?>[] )null).invoke(null, (Object[])null))){
			Object jObject = vmdClass.getMethod("id", (Class<?>[] )null).invoke(obj,(Object[]) null);
			System.out.println(jObject);
			if (jObject.equals(targetPID) ) {
				targetVMD = obj;
			}
		}
		Object objVM = null;
		if (targetVMD != null) {
			objVM = vmClass.getMethod("attach",vmdClass).invoke(null, targetVMD);
		}
		try {
			vmClass.getMethod("loadAgent", String.class,String.class).invoke(objVM, agentJar,coreJar);
		} finally {
			vmClass.getMethod("detach", (Class<?>[] )null).invoke(objVM, (Object[])null);
		}
	}
	
	public static void main(String[] args) {
		try {
			new GreysLauncher(args);
		} catch (Exception e) {
			System.out.println("start greys failed, because : "+GaStringUtils.getCauseMessage(e));
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
}
