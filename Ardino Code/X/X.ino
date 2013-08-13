//X-Control v1.0
//Arduino Code
//Developed by Aravind.V.S
//www.aravindvs.com
//Do whatever you want to!

float tempC,t1,t2;
int tempPin = 0;
int threshold=0,inbyte,x=0;
const char startOfNumberDelimiter = '<';
const char endOfNumberDelimiter   = '>';
void setup()
{
Serial.begin(9600); //opens serial port, sets data rate to 9600 bps
pinMode(10,OUTPUT);
pinMode(11,OUTPUT);
pinMode(12,OUTPUT);
}

void loop()
{  
if (Serial.available ())
{
processInput ();
}
delay(1);
}


void processNumber (const long n)
  {
threshold=n;

if(n==10)
 {
 digitalWrite(13,HIGH);
 digitalWrite(10,HIGH);
 }
if(n==11)
{
 digitalWrite(10,LOW);
 digitalWrite(13,LOW);
}

if(n==12)
 {
 digitalWrite(11,HIGH);
 }
if(n==13)
{
 digitalWrite(11,LOW);
}
if(n==14)
 {
 digitalWrite(12,HIGH);
 }
if(n==15)
{
 digitalWrite(12,LOW);
}


if(n==20)
 getTemp();
}

void getTemp()
{
t1 = analogRead(tempPin); //read the value from the sensor
t2 = analogRead(tempPin);
tempC=t1;
tempC = (5.0 * tempC * 100.0)/1023.0; //convert the analog data to temperature
tempC=floor(tempC);
if(x!=tempC)
{
Serial.print("A");
Serial.print(tempC); //send the data to the computer
Serial.print("B");
x=tempC;
}
}
  
void processInput ()
  {
  static long receivedNumber = 0;
  static boolean negative = false;
  
  byte c = Serial.read ();
  
  switch (c)
    {
      
    case endOfNumberDelimiter:  
      if (negative) 
        processNumber (- receivedNumber); 
      else
        processNumber (receivedNumber); 

    // fall through to start a new number
    case startOfNumberDelimiter: 
      receivedNumber = 0; 
      negative = false;
      break;
      
    case '0' ... '9': 
      receivedNumber *= 10;
      receivedNumber += c - '0';
      break;
      
    case '-':
      negative = true;
      break;
      
    } // end of switch  
  }  // end of processInput
