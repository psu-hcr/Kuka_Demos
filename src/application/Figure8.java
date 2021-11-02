package application;



import com.kuka.common.ThreadUtil;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.JointPosition;
import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.LBRE1Redundancy;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.motionModel.MotionBatch;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.task.ITaskLogger;
import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;

/**
 * Implementation of a robot application.
 * <p>
 * The application provides a {@link RoboticsAPITask#initialize()} and a 
 * {@link RoboticsAPITask#run()} method, which will be called successively in 
 * the application lifecycle. The application will terminate automatically after 
 * the {@link RoboticsAPITask#run()} method has finished or after stopping the 
 * task. The {@link RoboticsAPITask#dispose()} method will be called, even if an 
 * exception is thrown during initialization or run. 
 * <p>
 * <b>It is imperative to call <code>super.dispose()</code> when overriding the 
 * {@link RoboticsAPITask#dispose()} method.</b> 
 * 
 * @see UseRoboticsAPIContext
 * @see #initialize()
 * @see #run()
 * @see #dispose()
 */
public class Figure8 extends RoboticsAPIApplication {
	
	
	
	@Inject
	private LBR robot;
	Frame TestPoint=new Frame(752.6,37.1,752.9,-1.6,-0.2,-1.5);
	LBRE1Redundancy Redundancy = new LBRE1Redundancy().setE1(0.003);
	JointPosition FUCKYOU;
	@Override
	public void initialize() {
		TestPoint.setRedundancyInformation(robot,Redundancy);
		
	}

	@Override
	public void run() {
		// your application execution starts here
		int time = 5000;
		double x = 0;
		double y = 0;
		double z = 0;
	
		Frame[] Points= new Frame [5000];
		for(double i =0;i<time;i++)
		{
			x=500;
			y=250*java.lang.Math.cos((2*i)/1000);
			z=250*java.lang.Math.sin((4*i)/1000)+650;
			Frame Point= new Frame(x,y,z,0,0,0);
			Points[(int)i]=Point;
		}
		robot.move(ptpHome());
		robot.move(ptp(.1,.1,-.1,.3,.1,.05,.2));
		FUCKYOU= robot.getInverseKinematicFromFrameAndRedundancy(TestPoint);
		robot.move(ptp(FUCKYOU));
	
		for(int i =0;i<time;i++)
		{
			ptp(Points[i]);
		}
		
		ptp(Math.PI/2,Math.PI/2,0,Math.PI/2,-Math.PI/2,0,0);
	}
}