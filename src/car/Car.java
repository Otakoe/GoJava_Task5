package car;

import car.parts.CarWheel;

public class Car {
/*
    car тоже приват переменные
    переменные - данные производства (константа после инициализации объекта),
    тип двигателя, макс скорость (если новая), время разгона до 100, максимум пассажиров, пассажиров сейчас,
    массив колёс, массив дверей.
            конструктор - нет пустого, с датой производства, конструктор со всеми полями кроме массива колёс и дверей

    функции - Изменить текущую скорость, посадить 1 пассажира в машину, высадить 1 пассажира, высадить всех пассажиров,
    получить дверь по индексу, получить колесо по индексу, снять все колёса, установить на машину Х новых колёс в добавок имеющимся
    вычислить текущую воможную макс скорость (макс текущая скорость = макс скорость * самое стёртое колесо и =0 если в машине нет людей,
                                              принт всех данных в т.ч. макс расчётную скорость
*/

private enum engineType{gasoline,hybrid,hydrogen,electro};
private final int date[]= new int[3];
private int speedMax;
private int speedMaxCurrent;
private int timeTo100;
private int passengerMax;
private int passengerNow;
private int speed;
private CarWheel wheels[];
private int wheelCount;

public void Car(int [] date){
    //проверить корректность даты до 12 месяцев. до 31 числа, до 2020 года
    this.date[0]=date[0];
    this.date[1]=date[1];
    this.date[2]=date[2];
}
public void setPassengerMax(int passengerMax){
    this.passengerMax=passengerMax;
}
public void setSpeedMax(int speedMax) {
    this.speedMax = speedMax;
}
public void setTimeTo100(int timeTo100){
    this.timeTo100=timeTo100;
}
public void passengerAddOne(){
    if(passengerNow<passengerMax){
        passengerNow++;
        System.out.println("");
    }
    else
        System.out.println("В машину больше людей не влезет");
}
public void passengerRemoveOne(){
    if(passengerNow>0) {
        passengerNow--;
        System.out.println("");
    }
}
public void passengerRemoveAll(){
    if(passengerNow>0) {
        passengerNow = 0;
        System.out.println("Все вышли из машины");
    }
    else{
        System.out.println("В машине уже пусто");
    }

}
public void changeSpeed(int acceleration){
    this.speed+=acceleration;
    System.out.println("Врум врум");
}
public void calcMaxSpeed(){
    int people=passengerNow==0?0:1;
    double worthWheel=1.0;
    for(CarWheel w:wheels)
        if(w.getTire()<worthWheel)
            worthWheel=w.getTire();
    speedMaxCurrent=(int)(speedMax*people*worthWheel);
}
}