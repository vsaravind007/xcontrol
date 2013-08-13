//NodeJS Server Code For X-Control Automation System
//Requires SerialPort2 & Socket.IO To Be Installed
//Do whatever you want with it!
//Coded/Developed By Aravind.V.S
//www.aravindvs.com

var io = require('socket.io').listen(80);
var SerialPort  = require('serialport2').SerialPort;
var portName = 'COM12'; //Change To The Port To Which Your Arduino Is Connected

var sp = new SerialPort();
sp.open(portName, { 
   baudRate: 9600,
   dataBits: 8, 
   parity: 'none',
   stopBits: 1,
   flowControl: false
});

var cleanData = '';
var readData = '';
var temp='';

io.set('transports', [
    'websocket'
  , 'flashsocket'
  , 'htmlfile'
  , 'xhr-polling'
  , 'jsonp-polling'
  ]);


//io.set('log level', 1);
io.sockets.on('connection', function (socket) {
  sp.write("<20>");
  
  
 socket.on('my other event', function (data) {
    console.log(data.msgdata);
  });



socket.on('disco', function(){
    socket.disconnect();
});

socket.on('get', function(){
    sp.write("<20>");
});

socket.on('FanToggle', function (data) {
    if(data.msgdata)
      sp.write("<12>");
    if(!data.msgdata)
      sp.write("<13>");
    console.log(data.msgdata);
    socket.emit('update',data.msgdata);
  });


 socket.on('LightToggle', function (data) {
    if(data.msgdata)
      sp.write("<10>");
    if(!data.msgdata)
      sp.write("<11>");
    console.log(data.msgdata);
  });

socket.on('PlugToggle', function (data) {
    if(data.msgdata)
      sp.write("<14>");
    if(!data.msgdata)
      sp.write("<15>");
    console.log(data.msgdata);
  });

socket.on('validate', function (data) {
    //console.log(data.msgdata);
    if(data.msgdata  == 'aravind')
     {
     socket.emit('success',data.msgdata);
     //io.sockets.emit('temperature', cleanData);
       }
    else
    {
     socket.disconnect();
    }
  });

});

sp.on('data', function (data) {
    readData += data.toString();

    if(readData.indexOf('B') >= 0 && readData.indexOf('A') >= 0) 
      {
        cleanData = readData.substring(readData.indexOf('A') + 1, readData.indexOf('B'));
        readData = '';
        cleanData=Math.floor(cleanData);
        io.sockets.emit('temperature', cleanData);
        console.log('Emitted Value:'+ cleanData);
      }

});