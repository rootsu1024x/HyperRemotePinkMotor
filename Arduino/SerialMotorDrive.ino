#define LED_PIN 13

void setup(){
  Serial.begin(9600);
  pinMode (LED_PIN, OUTPUT);
  pinMode(1,OUTPUT); //信号用ピン
  pinMode(2,OUTPUT); //信号用ピン
}

int spd = 30;

void loop(){
  char str[20];
  if (Serial.available()) { 
    recvStr(str);
    spd = atoi(str);
    Serial.print(spd);
  }
  runMotor(spd);
}

void runMotor(int power){
  if(power > 100){
    power = 100;
  }
  if(power < 0){
    power = 0;
  }
  if(power == 0){
    digitalWrite(1,HIGH);
    digitalWrite(2,HIGH);
    analogWrite(3,0);
  }else{
    digitalWrite(1,HIGH);
    digitalWrite(2,LOW);
    analogWrite(3,power);
  }
}
 
void recvStr(char *buf)
{
  int i = 0;
  char c;
  while (1) {
    if (Serial.available()) {
      c = Serial.read();
      buf[i] = c;
      if (c == ';') break; // 文字列の終わりは\0で判断
      i++;
    }
  }
  buf[i] = '\0';
}
