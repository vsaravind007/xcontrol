X-Control Automation System v1.0
=============
Developed By Aravind.V.S
www.aravindvs.com

**NOTE: This project is no longer maintained/obsolete since this can be implemented using ESP8266 or others using simpler technologies now** 

Introduction
-----
I know there are a million homebrew automation systems out there & still I created one. Why? Simply because I wanted to make one on my own with the things I had laying around. My implementation is the most simple one out there I believe. X-Control is based on NodeJS & Arduino. My implementation runs on a home server with an Arduino UNO connected to USB & it controls just 3 appliances; A fan, a light & a plug point. My implementation can be controlled via an Android App or a Blackberry Playbook App. I know its more versatile to use a web based app, but since this is also a learning exercise, I wanted to try out making apps for the purpose. I've used a simple authentication system which disconnects the client of the access key is wrong. I know, its stupid! Still its pretty nifty! It also outputs current temperature via SocketIO & the Playbook App can display it. I haven't implemented it on the Android App yet.

Hardware
-----

- Arduino UNO
- 12v/1A DC Powersupply
- TIP122 Darlington Transistors
- 230V/7A, 12V Relays
- 2.2K Resistors
- 1N4007 Diodes
- Wires
- PCB
- LM35 Linear Temperature Sensor

Software - Server
-----

- Microsoft Windows 7 64Bit
- NodeJS
- Socket.IO
- SerialPort2

Control - Client/Remote
-----

- Android App
- Blackberry Playbook App

Logic/Control Flow
-----

Simple! The app connects to the server running on a PC via Wi-Fi & sends a number corresponding to the action to be performed - On or Off via SocketIO. On receiving the event, the server write the corresponding code word to turn on/off the Arduino pin, which is connected to a relay, which in turn is connected to the appliance.


Hardware Connections
-----

- Ardunio Pin A0 - LM35 Temperature Sensor's Output PIN
- Arduino Pin 10 - Relay 1
- Arduino Pin 11 - Relay 2
- Arduino Pin 12 - Relay 3

Dependancies
-----

This project has the following dependancies:

- [NodeJS Serial Port](https://github.com/voodootikigod/node-serialport) - Used in X-Control NodeJS Server Script
- [Java Socket.IO Client](https://github.com/clwillingham/java-socket.io.client) - Used in X-Control Android App
- [FlashSocket.IO](https://github.com/simb/FlashSocket.IO) - Used in X-Control Blackberry Playbook App

Thanks to the authors for making them public!

Additional Information
-----

The IP Address of the server running the NodeJS server file is hardcoded into the apps. You may want to change it inorder for them to work with your setup.

Issues/Bugs
-----

The system is working 24x7 for almost a week as of now(13/08/2013) & I haven't ran into any issues. The Android App crashes sometimes & the playbook app gets disconnected from the socketio connection after sometime for reasons unknown - I havent tried to resolve it yet as it isn't a big issue for me, maybe it has to do something with the timeout period.

Licence
-----

You can do whatever you want to do with the provided source provided you won't come crying back to me saying that you burned your house down or you electrocuted someone! I assure you, absolutely no warranty! Everything is provided "AS-IS" & you may modify/use it at your own risk. I'm responsible for nothing!

