package application;


import javax.inject.Inject;

import com.kuka.common.ThreadUtil;

import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.deviceModel.LBRE1Redundancy;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.motionModel.MotionBatch;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
import com.kuka.task.ITaskLogger;

/**
 * Implementation of a robot application.
 * <p>
 * The application provides a {@link RoboticsAPITask#initialize()} and a 
 * {@link RoboticsAPITask#run()} method, which will be called successively in 
 * the application life cycle. The application will terminate automatically after 
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
public class JustForFun extends RoboticsAPIApplication {
	@Inject
	private LBR robot;
	@Inject 
	private ITaskLogger logger;
	/* Learned Positions Table
	 * Point 1 = First Wave Point
	 * Point 2 = Second Wave Point
	 * Point 3 = Unused
	 * Point 4 = Home
	 * Point 5 = First Fist Bump Point
	 * Point 6 = Second Fist Bump Point
	 */
	Frame Point1= new Frame(329.3,-92.9,1143.7,-1.8,.8,.2);
	Frame Point2= new Frame(334,127.6,1144.1,-1.7,-.8,.1);
	Frame Point4= new Frame(11.7,7.4,1331.7,-1.8,-0.05,-0.01);
	Frame Point5= new Frame(683.3,33.6,771.8,-1.6,-0.2,-1.5);
	Frame Point6= new Frame(752.6,37.1,752.9,-1.6,-0.2,-1.5);
	
	LBRE1Redundancy Redundancy = new LBRE1Redundancy().setE1(0.003);
	

	
	@Override
	public void initialize() {
		// initialize your application here
		Point1.setRedundancyInformation(robot,Redundancy);
		Point2.setRedundancyInformation(robot,Redundancy);
		Point4.setRedundancyInformation(robot,Redundancy);
		Point5.setRedundancyInformation(robot,Redundancy);
		Point6.setRedundancyInformation(robot,Redundancy);
	}

	@Override
	public void run() {
		/* Learned Positions Table
		 * Point 1 = First Wave Point
		 * Point 2 = Second Wave Point
		 * Point 3 = Unused
		 * Point 4 = Home
		 * Point 5 = First Fist Bump Point
		 * Point 6 = Second Fist Bump Point
		 */
		
		boolean END=false;//used to mark when the user wants to leave
		MotionBatch wave = new MotionBatch(ptp(Point1),//sets up a MotionBatch for the waving demo
				ptp(Point2),//does a point to point motion to position 2
				ptp(Point1),//does a point to point motion to position 1
				ptp(Point2),//does a point to point motion to position 2...
				ptp(Point1),
				ptp(Point4)).setBlendingRel(0.1).setJointVelocityRel(0.5);//BlendingRel and JointVelocityRel are used to control fluidness of motion and speed respectively
		MotionBatch dance = new MotionBatch(//sets up a MotionBatch for the dancing demo
				ptp(Math.PI/2,Math.PI/2,0,Math.PI/2,-Math.PI/2,0,0),//moves the corresponding joints to the given angles ex: joint 1 to pi/2, joint 2 to pi/2...
				ptp(Math.PI/2,Math.PI/4,-Math.PI/4,Math.PI/4,-Math.PI/2,0,0),
				ptp(Math.PI/2,Math.PI/2,0,Math.PI/2,-Math.PI/2,0,0),
				ptp(Math.PI/2,Math.PI/4,-Math.PI/4,Math.PI/4,-Math.PI/2,0,0),
				ptp(Math.PI/2,Math.PI/2,0,Math.PI/2,-Math.PI/2,0,0),
				ptp(-Math.PI/2,-Math.PI/2,0,-Math.PI/2,Math.PI/2,0,0),
				ptp(-Math.PI/2,-Math.PI/4,-Math.PI/4,-Math.PI/4,Math.PI/2,0,0),
				ptp(-Math.PI/2,-Math.PI/2,0,-Math.PI/2,Math.PI/2,0,0),
				ptp(-Math.PI/2,-Math.PI/4,-Math.PI/4,-Math.PI/4,Math.PI/2,0,0),
				ptp(-Math.PI/2,-Math.PI/2,0,-Math.PI/2,Math.PI/2,0,0)
				).setJointVelocityRel(0.7).setBlendingRel(0.2);
		while(!END){//while the user wants to keep going
		int answer=getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION,"What would you like me to do?", "Wave","Fist Bump","Dance","End");
		//the above line is to ask the user what demo to do. the answers do different demos as seen in the following if statement
		
		if (answer==0){
			logger.info("Waving!");//prints message to Smart Pad
			robot.move(ptp(Point4).setJointVelocityRel(0.4));//moves to point 4
			robot.move(ptp(Point1).setJointVelocityRel(0.4));//moves to point 1
			robot.move(wave);//does wave routine
			robot.move(ptp(Point4).setJointVelocityRel(0.75));//moves to point 4
		
		}else if (answer==1){
			logger.info("Fist Bump!");//prints message to Smart Pad
			robot.move(ptp(Point4).setJointVelocityRel(0.5));//moves to point 4
			robot.move(ptp(Point5).setJointVelocityRel(0.5));//moves to point 5
			ThreadUtil.milliSleep(500);//waits 1/2 second
			robot.move(ptp(Point6).setJointVelocityRel(0.65));//moves to point 6
			robot.move(ptp(Point5).setJointVelocityRel(0.5));//moves to point 5
			robot.move(ptp(Point4).setJointVelocityRel(0.5));//moves to point 4
		}else if (answer==2){
			logger.info("Dance!");//prints to smart pad
			robot.move(ptp(Point4).setJointVelocityRel(0.5));//moves to point 4
			robot.move(dance);//"dances"
			robot.move(ptp(Point4).setJointVelocityRel(0.5));//moves to point 4
			
			
			
		}else if(answer==3){
			//ends
			logger.info("Sayonara!");
			END=true;
		}
		}
	}
	}
