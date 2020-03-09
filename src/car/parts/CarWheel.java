package car.parts;
/*
carwheel тоже приват переменные
        переменные - состояние шины от 0 до 1 дробное число
        функции - сменить шину на новую (состояние 1)
        стереть шину на n %
        получить состояние (ретурн)
        принт состояния объекта
*/
public class CarWheel {
    private double tire=1.0;
    public void newTire(){
        tire=1.0;
    }
    public void useTire(int n){
        this.tire-=(n/100.0);
    }
    public double getTire(){
        return tire;
    }
    public void printInfo(){
        System.out.println("Состояние шины: "+tire);
    }
}
