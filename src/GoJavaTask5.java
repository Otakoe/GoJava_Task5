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
        while (game){
        System.out.println("Приветствие");
        System.out.println("Давайте создадим машину и покатаемся на ней с товарищами");

        while(true){
            crutch(scan);
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
            crutch(scan);
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

        Car.engineType engine;
        System.out.println("Какой тип двигателя у машины.");
        stopWhile: //метка для выхода из следующего вайла с свичкейс
        while(true) {
            crutch(scan);
            System.out.println("1 - электрический");
            System.out.println("2 - бензиновый");
            System.out.println("3 - гибридный");
            System.out.println("4 - водородный");
            input=scan.nextLine();
            switch (input){
                case "1":
                    engine= Car.engineType.electro;
                    break stopWhile;
                case "2":
                    engine= Car.engineType.gasoline;
                    break stopWhile;
                case "3":
                    engine= Car.engineType.hybrid;
                    break stopWhile;
                case "4":
                    engine= Car.engineType.hydrogen;
                    break stopWhile;
                default:
                    wrong();
            }
        }
        //конец вайла из которого выйдем по метке

        Car car; //пустой но видимый объект
        //ввод открытых и закрытых дверей и окон
        while(true){
            crutch(scan);
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
                    crutch(scan);
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
        //ввод пассажировместительности через сеттер а не конструктор просто для разнообразия
        while (true){
            crutch(scan);
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
            crutch(scan);
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
            crutch(scan);
            int time;
            try{
                System.out.println("А до 100 за сколько разгон?");
                time=scan.nextInt();
                if(time<=2||time>50){
                    System.out.println("Такого быть не может, наверное около 10 сек");
                    time=10;
                }
                car.setTimeTo100(time);
                break;
            }catch (Exception e){
                wrong();
            }
            break;
        }

        //Далее код взаимодействия с объектом машины
        if(game){
            System.out.println("Вы успешно создали и заполнили данные о машине"+
                    "Для получения информации об управлении с машиной введите \"control\"");
        }
        while(game) {
            crutch(scan);
            String inpsub[];
            System.out.println("Введите ваше действие");
            try {
                input = scan.nextLine();
                if (input.equals("exit")) {
                    game = false;
                    break;
                }
                inpsub = input.split(" ", 3);
                switch (inpsub[0]) {
                    case "control":
                        controlInfoCar();
                        break;
                    case "stop":
                        car.stop();
                        break;
                    case "door":
                        switch (inpsub[1]) {
                            case "info":
                                break;
                            case "control":
                                break;
                            default:
                                int n = Integer.parseInt(inpsub[1]);
                                stopWhile2:
                                //метка выхода из вайла ниже
                                while (true) {
                                    crutch(scan);
                                    System.out.println("Вы выбрали дверь № " + n);
                                    if (input.equals("back")) {
                                        break;
                                    }
                                }
                                break;
                        }
                        break;
                    case "wheel":
                        switch (inpsub[1]) {
                            case "info":
                                break;
                            case "control":
                                break;
                            default:
                                int n = Integer.parseInt(inpsub[1]);
                                if (n > car.getWheelsInCar()) // количество колёс в машине
                                {
                                    System.out.println("Неверный номер колеса. Введите число от 1 до " + car.getWheelsInCar()); // количество колёс в машине
                                    n = scan.nextInt();
                                }
                                while (true) {
                                    System.out.println("Выбрано колесо № " + n + ". Что дальше с ним делать?");
                                    input = scan.nextLine();
                                    if (input.equals("back")) {
                                        System.out.println("Вы перешли из настроек колеса обратно к машине");
                                        break;
                                    }
                                }
                        }
                        break;
                    case "car":
                        break;
                    case "all":
                        break;
                    case "addwheel":
                        break;
                    case "dropwheel":
                        break;
                    case "speed":
                        break;
                    case "pass":
                        break;

                }

            } catch (Exception e) {
                wrong();
            }

        }
        }
    }
    // управление
    public static void controlInfoCar(){
        System.out.println("\'door info\' - информация о состоянии дверей и окон в дверях");
        System.out.println("\'door control\' - информация о доступных действиях с дверью");
        System.out.println("\'wheel info\' - информация о имеющихся колёсах в автомобиле.\n\tПервые четыре - основные установленные");
        System.out.println("\'wheel control\' - информация о доступных действиях с колесом");
        System.out.println("\'car info\' - основная информация о машине на данный момент\n\t(кол-во человек, скорость, запасные шины и колёса, состояние колёс");
        System.out.println("\'all info\' - вся информация\n");

        System.out.println("\'door N\' - выбор двери под номером N");
        System.out.println("\'wheel N\' - выбор колеса под номером N");

        System.out.println("\'addwheel N\' - добавить N количество колёс в машину");
        System.out.println("\'dropwheel N\' - выбросить колесо под номером N");

        System.out.println("\'speed + N\' - ускориться на км/ч");
        System.out.println("\'speed - N\' - замедлиться на км/ч");
        System.out.println("\'stop\' - полностью остановиться");

        System.out.println("\'pass + N\' - добавить N пассажиров (должна быть открыта как минимум одна дверь)");
        System.out.println("\'pass - N\' - высадить N пассажиров (должна быть открыта как минимум одна дверь)");

        System.out.println("\'exit\' - выход из игры");
    }
    public static void wheelInfoControl(){
        System.out.println("\'back\' - вернуться назад");
        System.out.println("\'change tire\' - сменить покрышку");
        System.out.println("\'change on N\' - заменить колесо на запасное");
        System.out.println("\'");
    }
    public static void doorInfoControl(){

    }
        //Вывод для неверного ввода
    public static void wrong(){
        System.out.println("Неверный ввод");
    }
    public static void crutch(Scanner scan) {
        if(scan.hasNextLine())
        {
            scan.nextLine();
        }
    }

}
