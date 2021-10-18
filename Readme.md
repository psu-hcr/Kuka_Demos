### To run this demo on the Kuka LBR IIWA 14 820
#### Step 0: Install, Sunrise Workbench on your machine, clone this repo into your Sunrise Workspace, setup connection to the Kuka
*Note: Use these steps to clone the repo with the Github Desktop App. This is only done once.*  

1.Open SunriseWorkbench and select a workspace. The default on Windows is a folder called ~/SunriseWorkspace.

2. To clone this repository into the workspace, open the Github desktop app and select File-> Clone repository.

3. In the window that pops up, find psu-hcr/Kuka_Demos and select it. Change the local path to the sunrise workspace and click `Clone'.

*You should now have a folder called ~/SunriseWorkspace/Kuka_Demos on your local machine.*

4. Set up the ethernet connection with the X66 ethernet port on the Sunrise Cabinet by going to Control Panel ->Network and Internet > Network and Sharing Center

5. Click on the Ethernet hyperlink under the unidentifies ethernet network and select Properties

6. Under properties, select IPv4.

7. In the menu that pops up, manually enter the IP address as 172.31.1.42 and mask as 255.255.0.0


#### Step 1: Load Project on Kuka
1. Open the Kuka_Demos project in the Sunrise Workbench. Click the Synchronize icon.
2. Select Deploy to Project and execute.
3. You will be asked to select a user and enter a password. Switch to user Maintenance technician and enter `argus` as the password.
4. On the Kuka smartHMI tablet, select the application and press the green arrow to enable. Then choose from the programmed motions.


###Saving frames from the Kuka
####Step 1: Save Points on Smart Pad
####Step 1: Synchronize
####Step 3: Hardcode frame data

###Set up a new project


