#include <Adafruit_CircuitPlayground.h>
#include <Adafruit_Circuit_Playground.h>

#include <Adafruit_CircuitPlayground.h>
#include <Adafruit_Circuit_Playground.h>

/*********************************************************************
  * Laura Arjona. UW EE P 523. SPRING 2020
  * Example of simple interaction beteween Adafruit Circuit Playground
  * and Android App. Communication with BLE - uart
*********************************************************************/
#include <Arduino.h>
#include <SPI.h>
#include "Adafruit_BLE.h"
#include "Adafruit_BluefruitLE_SPI.h"
#include "Adafruit_BluefruitLE_UART.h"
#include <Adafruit_CircuitPlayground.h>

#include "BluefruitConfig.h"

#if SOFTWARE_SERIAL_AVAILABLE
  #include <SoftwareSerial.h>
#endif


// Strings to compare incoming BLE messages
String start = "start";
String cancel = "cancel";
String red = "red";
String readtemp = "readtemp";
String stp = "stop";
String no = "no";
String ok = "ok";
String lost = "phone_lost";
int  sensorTemp = 0;

//parameters to detect the door openning
float threshold = 10.1;
unsigned long shakeInterval = 3000;
float startAccelMag = 0;
unsigned long lastShakeTime = 0;
boolean flag = false;
float result;
bool leftButtonPressed;
bool rightButtonPressed;
int left;
int right;
int count = 0;

/*=========================================================================
    APPLICATION SETTINGS
    -----------------------------------------------------------------------*/
    #define FACTORYRESET_ENABLE         0
    #define MINIMUM_FIRMWARE_VERSION    "0.6.6"
    #define MODE_LED_BEHAVIOUR          "MODE"
/*=========================================================================*/

// Create the bluefruit object, either software serial...uncomment these lines

Adafruit_BluefruitLE_UART ble(BLUEFRUIT_HWSERIAL_NAME, BLUEFRUIT_UART_MODE_PIN);

/* ...hardware SPI, using SCK/MOSI/MISO hardware SPI pins and then user selected CS/IRQ/RST */
// Adafruit_BluefruitLE_SPI ble(BLUEFRUIT_SPI_CS, BLUEFRUIT_SPI_IRQ, BLUEFRUIT_SPI_RST);

/* ...software SPI, using SCK/MOSI/MISO user-defined SPI pins and then user selected CS/IRQ/RST */
//Adafruit_BluefruitLE_SPI ble(BLUEFRUIT_SPI_SCK, BLUEFRUIT_SPI_MISO,
//                             BLUEFRUIT_SPI_MOSI, BLUEFRUIT_SPI_CS,
//                             BLUEFRUIT_SPI_IRQ, BLUEFRUIT_SPI_RST);


// A small helper to show errors on the serial monitor
void error(const __FlashStringHelper*err) {
  Serial.println(err);
  while (1);
}

void startDetect(){
  for(int i = 0; i < 11; i++){
      CircuitPlayground.setPixelColor(i,0, 255, 0);
    }
  }
  
void openDetect(){
  //turn all the LED be green
  String received = "";
//  for(int i = 0; i < 11; i++){
//      CircuitPlayground.setPixelColor(i,255, 255, 0);
//    }
//  delay(100);
//  for(int i = 0; i < 11; i++){
//    CircuitPlayground.setPixelColor(i,0, 0, 0);
//  }
  char output[8];
  String data = "1";
  data.toCharArray(output,8);
  ble.print(data);
  delay(200);
  Serial.println("Read temperature sensor"); 

  //get the accelerator value
  left = 0;
  right = 0;
  int times = 0;
  while (true){
//    CircuitPlayground.playTone(500, 100);
//    delay(100);
    leftButtonPressed = CircuitPlayground.leftButton();
    rightButtonPressed = CircuitPlayground.rightButton();
    for(int i = 0; i < 11; i++){
      CircuitPlayground.setPixelColor(i,255, 255, 0);
    }
    delay(50);
    for(int i = 0; i < 11; i++){
      CircuitPlayground.setPixelColor(i,0, 0, 0);
    }
    times ++;
   
    while ( ble.available() )
    {
    int c = ble.read();
    Serial.print((char)c);
    received += (char)c;
        delay(50);
    }

    if(cancel == received){
      Serial.println("RECEIVED cancel!!!!");
      for(int i = 0; i < 11; i++){
          CircuitPlayground.setPixelColor(i,0, 255, 0);
      } 
      break;
    }
    delay(50);

    if (rightButtonPressed) {
      right = 1;
    }
    else if (leftButtonPressed) {
      left = 1;
    }
    if (left == 1 && right != 1){
      left = 0;
    }
    if (right == 1 && left == 1){
      for(int i = 0; i < 11; i++){
          CircuitPlayground.setPixelColor(i,0, 255, 0);
      }
      char output2[8];
      String data2 = "3";
      data.toCharArray(output2,8);
      ble.print(data2);
      delay(200);
      break;
    }
    delay(50);
    Serial.println("left:");
    Serial.println(left);
    Serial.println("right:");
    Serial.println(right);
    
//    if(no == received){
//      Serial.println("RECEIVED no!!!!"); 
//      if (times > 30){
//        for(int i = 0; i < 11; i++){
//          CircuitPlayground.setPixelColor(i,0, 255, 0);
//        }
//      break;
//      }
//    }

    if (times > 50){
        for(int i = 0; i < 11; i++){
          CircuitPlayground.setPixelColor(i,255, 0, 0);
        }
      char output1[8];
      String data1 = "2";
      data.toCharArray(output1,8);
      ble.print(data1);
      while(true){
        CircuitPlayground.playTone(1000, 100);
        delay(50);
      }
    }
    
    
  }
}

//float getAccelMagnitude() {
//  float X = 0;
//  float Y = 0;
//  float Z = 0;
//
//  float dX = 0;
//  float dY = 0;
//  float dZ = 0;
//
//  for (int i = 0; i < 10; ++i) {
//    dX = CircuitPlayground.motionX();
//    dY = CircuitPlayground.motionY();
//    dZ = CircuitPlayground.motionZ();
//
//    X += dX;
//    Y += dY;
//    Z += dZ;
//  }
//
//  X /= 10;
//  Y /= 10;
//  Z /= 10;
//
//  return sqrt(X * X + Y * Y + Z * Z);
//}




//boolean detect() {
//  float currentAccelMag = getAccelMagnitude();
//  if ((currentAccelMag - startAccelMag) < threshold) {
//    return false;
//  }
//
//  unsigned long now = millis();
//  if ((now - lastShakeTime) < shakeInterval) {
//    // don't give shake gestures too frequently
//    return false;
//  }
//
//  Serial.println("Shake detected");
//  lastShakeTime = now;
//  return true;
//}

void setup(void)
{
  CircuitPlayground.begin();
  

  Serial.begin(115200);

  /* Initialise the module */
  Serial.print(F("Initialising the Bluefruit LE module: "));

  //attachInterrupt(digitalPinToInterrupt(pin), ISR, mode);

  if ( !ble.begin(VERBOSE_MODE) )
  {
    error(F("Couldn't find Bluefruit, make sure it's in CoMmanD mode & check wiring?"));
  }
  Serial.println( F("OK!") );

  if ( FACTORYRESET_ENABLE )
  {
    /* Perform a factory reset to make sure everything is in a known state */
    Serial.println(F("Performing a factory reset: "));
    if ( ! ble.factoryReset() ){
      error(F("Couldn't factory reset"));
    }
  }

  /* Disable command echo from Bluefruit */
  ble.echo(false);

  Serial.println("Requesting Bluefruit info:");
  /* Print Bluefruit information */
  ble.info();

  Serial.println(F("Please use Adafruit Bluefruit LE app to connect in UART mode"));
  Serial.println(F("Then Enter characters to send to Bluefruit"));
  Serial.println();

  ble.verbose(false);  // debug info is a little annoying after this point!

  /* Wait for connection */
  while (! ble.isConnected()) {
      delay(500);
  }
  startDetect();
Serial.println("CONECTED:");
  
  Serial.println(F("******************************"));

  // LED Activity command is only supported from 0.6.6
  if ( ble.isVersionAtLeast(MINIMUM_FIRMWARE_VERSION) )
  {
    // Change Mode LED Activity
    Serial.println(F("Change LED activity to " MODE_LED_BEHAVIOUR));
    ble.sendCommandCheckOK("AT+HWModeLED=" MODE_LED_BEHAVIOUR);
  }

  // Set module to DATA mode
  Serial.println( F("Switching to DATA mode!") );
  ble.setMode(BLUEFRUIT_MODE_DATA);

  Serial.println(F("******************************"));
 
  CircuitPlayground.setPixelColor(20,20,20,20);
 
  delay(1000);
  
}
/**************************************************************************/
/*!
   Constantly poll for new command or response data
*/
/**************************************************************************/
void loop(void)
{
  // Save received data to string
  String received = "";

  
  while ( ble.available() )
  {
    int c = ble.read();
    Serial.print((char)c);
    received += (char)c;
        delay(50);
  }

  if(red == received){
    Serial.println("RECEIVED RED!!!!"); 
       for(int i = 0; i < 11; i++){
      CircuitPlayground.setPixelColor(i,255, 255, 255);
    }
    delay(50);
  }
 
  else if(readtemp == received){
       
    sensorTemp = CircuitPlayground.temperature(); // returns a floating point number in Centigrade
    Serial.println("Read temperature sensor"); 
    delay(10);

   //Send data to Android Device
    char output[8];
    String data = "";
    data += sensorTemp;
    Serial.println(data);
    data.toCharArray(output,8);
    ble.print(data);
  }
 
  else if (stp == received){
      CircuitPlayground.clearPixels();

  }

  else if (lost == received){
      while(count < 20){
        CircuitPlayground.playTone(1000, 100);
        delay(50);
        count++;
      }
      count = 0;
  }


  float X = 0;
  float Y = 0;
  float Z = 0;

  float dX = 0;
  float dY = 0;
  float dZ = 0;

  for (int i = 0; i < 10; ++i) {
    dX = CircuitPlayground.motionX();
    dY = CircuitPlayground.motionY();
    dZ = CircuitPlayground.motionZ();

    X += dX;
    Y += dY;
    Z += dZ;
  }

  X /= 10;
  Y /= 10;
  Z /= 10;

  result = sqrt(X * X  + Z * Z);
  Serial.println(result);
  

  unsigned long now = millis();
  if ((now - lastShakeTime) > shakeInterval) {
    if ((result - startAccelMag) >= threshold) {
      lastShakeTime = now;
      openDetect();
      
      Serial.println("Open detected"); 
    }
  }

  
  
}

 
