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
    public static void main(String[] args) {
        boolean game=true;
        String input;
        Scanner scan = new Scanner(System.in);
        while(game) {
            input = scan.nextLine();
            if(input.equals("exit"))
                game=false;
        }
    }
}
