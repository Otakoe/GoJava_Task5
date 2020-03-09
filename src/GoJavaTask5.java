import car.Car;

import java.util.Scanner;
/*
Создать пакет с классами:

carwheel тоже приват переменные
переменные - состояние шины от 0 до 1 дробное число
функции - сменить шину на новую (состояние 1)
стереть шину на n %
получить состояние (ретурн)
принт состояния объекта

доп зад.
создать консольный интерфейс для взаимодействия с программой пользователем+
 */
public class GoJavaTask5 {
    public static boolean game=true;
    public static void main(String[] args) {
        String input;
        Scanner scan = new Scanner(System.in);
        System.out.println("Приветствие");
        System.out.println("Давайте создадим машину и покатаемся на ней с товарищами");

        while(true){
        System.out.println("Введите ваше действие. yes - да, exit - выход");
        input = scan.nextLine();
        if(input.equals("exit")) {
            game=false;
            break;
        }
        else if(input.equals("yes")) {
            game = true;
            break;
        }
        else
            wrong();
        }
        System.out.println("Укажите несколько данных о машине");
        //Ввод года выпуска
        int year;
        while(true){
            System.out.println("Введите год выпуска машины");
            try{
                year=scan.nextInt();
                if(year>2020)
                    throw new Exception();
                break;
            }
            catch(Exception e){
                wrong();
            }
        }
        //ввод открытых и закрытых дверей и окон
        Car.engineType engine;
        System.out.println("Какой тип двигателя у машины. electro - электрический");
        stopWhile: //метка для выхода из следующего вайла с свичкейс
        while(true) {
            System.out.println("electro - электрический");
            System.out.println("gasoline - бензиновый");
            System.out.println("hybrid - гибридный");
            System.out.println("hydrogen - водородный");
            input=scan.nextLine();
            switch (input){
                case "electro":
                    engine= Car.engineType.electro;
                    break stopWhile;
                case "gasoline":
                    engine= Car.engineType.gasoline;
                    break stopWhile;
                case "hybrid":
                    engine= Car.engineType.hybrid;
                    break stopWhile;
                case "hydrogen":
                    engine= Car.engineType.hydrogen;
                    break stopWhile;
                default:
                    wrong();
            }
            }
        //конец вайла из которого выйдем по метке
        Car car; //пустой но видимый объект
        while(true){
            System.out.println("Будете указывать какие окна и двери открыты а какие закрыты.\n" +
                    "\"yes\" - да, \"no\" - пропустить, тогда все окна и двери будут закрыты");
                input=scan.nextLine();

                if(input.equals("no")) {
                    car=new Car(year);
                    break;
                }                        // первый констуруктор и выход из цикла
                else if(input.equals("yes")){}              // всё хорошо продолжаем двигаться ко второму конструктору
                else {
                    wrong();
                    continue;
                }                                       // этот цикл сначала
                String sDoor[] = new String[4];
                String sWindow[] = new String[4];
                for (int i = 0; i < 4; i++) {
                    while(true){
                        System.out.println("Дверь " + (i + 1) + ": \"open\" открыта, \"close\" закрыта");
                        input=scan.nextLine();
                        if(input.equals("open")||input.equals("close")) {
                            sDoor[i] = input;
                            break;
                        }
                        else
                            wrong();
                    }
                    while(true){
                        System.out.println("Окно двери " + (i + 1) + ": \"open\" открыто, \"close\" закрыто");
                        input=scan.nextLine();
                        if(input.equals("open")||input.equals("close"))
                            sWindow[i]=input;
                        else
                            wrong();
                    }
                }
                car=new Car(year,engine,sDoor,sWindow);
        }
        //ввод пассажировместительности через сеттер а не конструктор просто для проверки рабочести сеттера
        while (true){
            int pass;
            try{
                System.out.println("Какая пассажировместимость вместе с водителем?");
                pass=scan.nextInt();
                if(pass<=0){
                    System.out.println("Значит будет 4");
                    pass=4;
                }
                car.setPassengerMax(pass);
                break;
            }catch (Exception e){
                wrong();
            }
        }
        //ввод макс скорости
        while (true){
            int speed;
            try{
                System.out.println("Какая максимальная скорость по тех. характеристикам");
                speed=scan.nextInt();
                if(speed<=30){
                    System.out.println("Значит будет 100");
                    speed=100;
                }
                car.setSpeedMax(speed);
                break;
            }catch (Exception e){
                wrong();
            }
            break;
        }
        while (true){
            int time;
            try{
                System.out.println("А до 100 за сколько разгон?");
                time=scan.nextInt();
                if(time<=2){
                    System.out.println("Такого быть не может, наверное около 5");
                    time=5;
                }
                car.setTimeTo100(time);
                break;
            }catch (Exception e){
                wrong();
            }
            break;
        }
        while(game) {
            input = scan.nextLine();
            if(input.equals("exit")) {
                game = false;
                break;
            }

        }
    }
    public static void controlInfoCar(){
        System.out.println();
        System.out.println();
    }

    // потому что надоело повторять код
    public static void wrong(){
        System.out.println("Неверный ввод");
    }
}
