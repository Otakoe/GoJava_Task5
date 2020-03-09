package car;

import car.parts.CarDoor;
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

public static enum engineType {gasoline,hybrid,hydrogen,electro};
private engineType engine;
private int date;
private int speedMax;
private int speedMaxCurrent;
private int timeTo100;
private int passengerMax;
private int passengerNow=0;
private int speed=0;
private CarWheel wheels[];
private int wheelsInCar=4;
private int tiresInCar=0;
private CarDoor doors[]=new CarDoor[4];

private void CarHidden (int date) {
    if (date <= 2020)
        this.date = date;
    for (int i = 0; i < wheelsInCar; i++)
        wheels[i] = new CarWheel();
}
public Car(int date){
    CarHidden(date);
    for(CarDoor d:doors){
        d=new CarDoor();
    }
}
public Car(int date,engineType engine,String sDoor[],String sWindow[]){
    CarHidden(date);
    this.engine=engine;
    for (int i=0;i<4;i++)
        doors[i]=new CarDoor(sDoor[i],sWindow[i]);
}

//базовые сеттеры и геттеры
public void setPassengerMax(int passengerMax){
    this.passengerMax=passengerMax;
}
public void setSpeedMax(int speedMax) {
    this.speedMax = speedMax;
}
public void setTimeTo100(int timeTo100){
    this.timeTo100=timeTo100;
}
public void setEngine(engineType type) {
    engine = type;
}
public int getSpeed(){
    return speed;
}
//всё что можно делать с машиной
public void addTire(int tires){
    if(speed >0)
        System.out.println("Нельзя делать на ходу, надо остановиться. Текущая скорость "+speed);
    else
        tiresInCar+=tires;
}
public void addWheel(int wheel){
    if(speed>0)
        System.out.println("Сперва нужно остановиться");
    else {
        if (wheel > 0) {
            for (int i = 0; i < wheel; i++) {
                wheels[wheelsInCar] = new CarWheel();
                wheelsInCar++;
            }
            System.out.println("Добавлено " + wheel + " колёс");
        } else
            System.out.println("Нельзя добавить " + wheel + " колёс");
    }
}
public void changeWheel(int wheelIndex, int wheelNewIndex) {
    if (speed > 0)
        System.out.println("На ходу колесо сменить не получится");
    else {
        if (wheelIndex <= 4 && wheelIndex >= 1 && wheelNewIndex >= 5 && wheelNewIndex <= wheelsInCar) {
            CarWheel tmp = wheels[wheelIndex - 1];
            wheels[wheelIndex - 1] = wheels[wheelNewIndex - 1];
            wheels[wheelNewIndex - 1] = tmp;
            System.out.println("Колесо № " + wheelIndex + " заменено на №" + wheelNewIndex);
        } else
            System.out.println("Что-то вы пытаетесь намудрить");
    }
}
public void changeTireOnWheel(int wheelIndex){
    if(wheelIndex>4&&speed>0)
        System.out.println("На колёсах которые крутятся менять шину на ходу не получится");
    else
    if(wheelIndex>=1&&wheelIndex<=4) {
        if (tiresInCar > 0) {
            wheels[wheelIndex-1].newTire();
            tiresInCar--;
            System.out.println("Заменена покрышка на колесе " + (wheelIndex-1));
        } else
            System.out.println("Нет запасных покрышек в машине.");
    }
    else
        System.out.println("В машине всего 4 колеса");
}


public void passengerAddOne(){
    if(speed>2)
        System.out.println("Слишком большая скорость для этого О_о");
    else if(checkDoors()){
        if (passengerNow < passengerMax) {
            passengerNow++;
            System.out.println("");
        } else
            System.out.println("В машину больше людей не влезет");
    }
    else
        System.out.println("Двери закрыты :(");
}
public void passengerRemoveOne(){
    if(passengerNow>0) {
        passengerNow--;
        System.out.println("Пассажир вышел");
    }
    if (speed>2)
        System.out.println("Но ему больно :(");
}
public void passengerRemoveAll(){
    if(passengerNow>0) {
        if(speed>0)
            System.out.println("Нельзя всем выпрыгнуть на ходу, машина станет неуправляема");
        else {
            passengerNow = 0;
            System.out.println("Все вышли из машины");
        }
    }
    else{
        System.out.println("В машине уже пусто");
    }

}
public void dropWheel(int n){
        if(n>=4)
        {
            if(n<=wheelsInCar) {
                for (int i = n - 1; i < wheelsInCar - 1; i++)
                    wheels[i] = wheels[i + 1];
                wheels[wheelsInCar-1]=null;
                if(speed>10)
                    System.out.println("И пусть кого-то пришибёт");
            }
            else
                System.out.println("Такого колеса в машине не существует");
        }
        else
            System.out.println("Нельзя выкинуть колесо которое используется");
    }

public void changeSpeed(int acceleration){
    setSpeedMaxCurrent();
    if(speed<speedMaxCurrent+acceleration) {
        this.speed += acceleration;
        System.out.println("Врум врум");
    }
    else if(speed<speedMaxCurrent) {
        this.speed = speedMaxCurrent;
        System.out.println("Достигнута максимальная скорость");
    }
}
public void stop(){
        usingTires();
        speed =0;
    }
public int calcMaxSpeed(){
    int people=passengerNow==0?0:1;
    double worthWheel=1.0;
    for(CarWheel w:wheels)
        if(w.getTire()<worthWheel)
            worthWheel=w.getTire();
    return speedMaxCurrent=(int)(speedMax*people*worthWheel);
}
public void setSpeedMaxCurrent(){
    speedMaxCurrent=calcMaxSpeed();
}
public void usingTires(){
    for (int i=0;i<4;i++)
        wheels[i].useTire((int)speed/100*6); // формула в аргументе - рейт стираемости покрышек
}

public void openDoor(int n){
    if(n<=4&&n>=1) {
        if(!doors[n-1].getDoorBool()) {
            doors[n - 1].doorOpen();
            System.out.println("Дверь № " + n + " открыта");
        }
        else
            System.out.println("Дверь уже открыта");
    }
    else
        System.out.println("Нельзя сотворить здесь");
}
public void closeDoor(int n){
    if(n<=4&&n>=1) {
        if(doors[n-1].getDoorBool()) {
            doors[n - 1].doorClose();
            System.out.println("Дверь № " + n + " закрыта");
        }
        else
            System.out.println("Дверь уже закрыта");
    }
    else
        System.out.println("Нельзя сотворить здесь");
}
public void switchDoor(int n){
    if(n<=4&&n>=1) {
        doors[n-1].doorSwitch();
    }

}

public void openWindow(int n){
    if(n<=4&&n>=1) {
        if(!doors[n-1].getWindowBool()) {
            doors[n - 1].windowOpen();
            System.out.println("Окно № " + n + " открыто");
        }
        else
            System.out.println("Окно уже открыто");
    }
    else
        System.out.println("Нельзя сотворить здесь");
}
public void closeWindow(int n){
    if(n<=4&&n>=1) {
        if(doors[n-1].getWindowBool()) {
            doors[n - 1].windowClose();
            System.out.println("Окно № " + n + " закрыто");
        }
        else
            System.out.println("Окно уже закрыто");
    }
    else
        System.out.println("Нельзя сотворить здесь");
}
public void switchWindow(int n){
    if(n<=4&&n>=1){
        doors[n-1].windowSwitch();
    }
}

public void printWheelsInfo(){
    int i=0;
    for(CarWheel w:wheels){
        System.out.print("Колесо "+i+" ");
        w.printInfo();
        i++;
    }
}
public void printDoorsInfo(){
    int i=0;
    for(CarDoor d:doors) {
        System.out.println("Дверь "+i+" ");
        d.printInfo();
        i++;
    }
}
public void printImportantInfo(){
    System.out.println("Максимальная расчётная скорость "+calcMaxSpeed());
    System.out.println("Сейчас людей в машине "+passengerNow);
    System.out.println("Текущая скорость "+speed);
    System.out.println("Кол-во запасных колёс "+(wheelsInCar-4));
    System.out.println("Кол-во запасных покрышек "+tiresInCar);
    printWheelsInfo();
    printDoorsInfo();
}
public void printInfo(){
    System.out.println("Машина "+date+" года выпуска");
    System.out.println("Теоретическая максимальная скорость "+speedMax);
    System.out.println("Разгон до 100 "+timeTo100);
    System.out.println("Пассажировместительность вместе с водителем "+passengerMax);
    printImportantInfo();
}

public boolean checkDoors(){
    for (CarDoor d:doors) {
        if(d.getDoorBool()) {
            return true;
        }
    }
    return false;
}
}