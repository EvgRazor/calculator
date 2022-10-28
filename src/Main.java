import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {

        Input input = new Input();

        try {
            String ravno = input.exit();
            System.out.println(ravno);
        } catch (Exception e) {
            System.out.println("throws Exception // Используются одновременно разные системы счисления или некорректные данные (буквы и тд...)");
        }

    }


}

class Input {

    String [] arab = {"0","1","2","3","4","5","6","7","8","9","+","-","*","/"};
    String [] rome = {"I","II","III","IV","V","VI","VII","VIII","IX","X","+","-","*","/"};
    String [] operator = {"+","-","*","/"};
    String strUserInput = null;
    String[] leftRightChislo = null;
    String userOperator = null;
    Map <String, String> arrRome = new HashMap<>();

    // Ввод данных юзера
     String userInput () {
        System.out.print("Введите данные для работы: ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
        scanner.close();
        this.strUserInput = str;
     return str;
    }

    // Считываем данные с клавиатуры
    int whatNumber () {

        int count = 0;
        String strWhatNumber = this.userInput().replaceAll("\\s+","");

        int longStr = strWhatNumber.length();
        char[] userInputChar = strWhatNumber.toCharArray();

        for (Character userInputCha : userInputChar) {
            for (int i = 0; i < arab.length; i++) {
                if (Character.toString(userInputCha).equals(arab[i])) {
                    count += 1;
                    if (count == longStr){
                        return 1; // если все числа араб.
                    }
                }
            }
        }

        count = 0;

        for (Character userInputCha : userInputChar) {
            for (int i = 0; i < rome.length; i++) {
                if (Character.toString(userInputCha).equals(rome[i])) {
                    count += 1;
                    if (count == longStr){
                        return 0; // если все числа рим.
                    }
                }
            }
        }

        return 2; // если ввод был некорректный
    } // +

    // Controller -  какая функция обрабатывает
    String exit () throws Exception {

        String i = String.valueOf(this.whatNumber());

        if (i.equals("1")) {
            int otv = this.numberArab(strUserInput);
            if (otv != 101)
                return String.valueOf(otv);
        } else if (i.equals("0")) {
            String otv = this.numberRome(strUserInput);
                return otv;
        } else if (i.equals("2")) {
           throw new Exception();
        }

        return i;
    }

    // Проверка на ошибки и работа калькулятора с араб цифрами
    int numberArab (String strUserInput) {

        String numberArab = strUserInput.replaceAll("\\s+","");
        char [] numberArabChar = numberArab.toCharArray();
       //  String userOperator = null; //

        // Проверка № 1  на + - / *
        for (String operato : operator) {
            for (int i = 0; i < numberArabChar.length; i++) {
                if (operato.equals(Character.toString(numberArabChar[i]))) {
                    userOperator = operato;
                    break;
                }
            }
        }

        // Проверка № 1, если нет  + - / * , то выкидываем ошибку и останавливаем программу
        if (userOperator == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                return 101;
            }
        }

        // Проверка № 2  на количество + - / *
        int count = 0;
        for (int i = 0; i < numberArabChar.length; i++) {
            switch (numberArabChar[i]) {
                case '+':
                    count += 1;
                    break;
                case '-':
                    count += 1;
                    break;
                case '*':
                    count += 1;
                    break;
                case '/':
                    count += 1;
                    break;
            }
        }

        // Проверка № 2 если больше   + - / *  1-го , то выкидываем ошибку и останавливаем программу
        if (count > 1)  {
            try {
                throw  new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception // т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                return 101;
            }
        }

        // Проверка 3 Оба числа меньше или равны 10
        leftRightChislo = numberArab.split(Pattern.quote(userOperator));

        for (String leftRightChisl : leftRightChislo) {
            int chislo = Integer.parseInt(leftRightChisl);
                if (chislo >= 11 || chislo <= 0) {
                    try {
                        throw  new Exception();
                    } catch (Exception e) {
                        System.out.println("throws Exception // Одно из чисел введеных вами больше 10 или меньше 1. " + "Входной параметр числа от 1 до 10");
                        return 101;
                    }
                }
        }

        // Решение
        int itog = 0;

        int a = Integer.parseInt(leftRightChislo[0]);
        int b = Integer.parseInt(leftRightChislo[1]);

        switch (userOperator) {
            case "-":
                itog = a - b;
                break;
            case "+":
                itog = a + b;
                break;
            case "*":
                itog = a * b;
                break;
            case "/":
                itog = a / b;
                break;
        }
        return itog;
    }

    // Проверка на ошибки и работа калькулятора с рим цифрами
    String numberRome (String strUserInput) {

            arrRome.put("1", "I");
            arrRome.put("2", "II");
            arrRome.put("3", "III");
            arrRome.put("4", "IV");
            arrRome.put("5", "V");
            arrRome.put("6", "VI");
            arrRome.put("7", "VII");
            arrRome.put("8", "VIII");
            arrRome.put("9", "IX");
            arrRome.put("10", "X");
            arrRome.put("11", "XI");
            arrRome.put("12", "XII");
            arrRome.put("13", "XIII");
            arrRome.put("14", "XIV");
            arrRome.put("15", "XV");
            arrRome.put("16", "XVI");
            arrRome.put("17", "XVII");
            arrRome.put("18", "XVIII");
            arrRome.put("19", "XIX");
            arrRome.put("20", "XX");
            arrRome.put("30", "XXX");
            arrRome.put("40", "XL");
            arrRome.put("50", "L");
            arrRome.put("60", "LX");
            arrRome.put("70", "LXX");
            arrRome.put("80", "LXXX");
            arrRome.put("90", "XC");
            arrRome.put("100", "C");

        String numberRome = strUserInput.replaceAll("\\s+","");
        char[] numberRomeChar = numberRome.toCharArray();

        // Определяем оператор № 1  на + - / *
        for (String operato : operator) {
            for (int i = 0; i < numberRomeChar.length; i++) {
                if (operato.equals(Character.toString(numberRomeChar[i]))) {
                    userOperator = operato;
                    break;
                }
            }
        }

        if (userOperator == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //  Отсуствует опертор, а строка не является математической операцией");
                return "";
            }
        }

        int count = 0;
        for (int i = 0; i < numberRomeChar.length; i++) {
            switch (numberRomeChar[i]) {
                case '+':
                    count += 1;
                    break;
                case '-':
                    count += 1;
                    break;
                case '*':
                    count += 1;
                    break;
                case '/':
                    count += 1;
                    break;
            }
        }

        // Проверка № 2 если больше   + - / *  1-го , то выкидываем ошибку и останавливаем программу
        if (count > 1)  {
            try {
                throw  new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                return "";
            }
        }

        String[] numberRomeArr = numberRome.split(Pattern.quote(userOperator));

        String strA = numberRomeArr[0];
        String strB = numberRomeArr[1];

        // Проверка на введные данные, что они от 1 до 10
        int counter = 0;
        for (String rom : rome) {
            for (String numberRomeAr : numberRomeArr) {
                if (numberRomeAr.equals(rom)) {
                    counter++;
                }
            }
        }


        if ((counter == 0 || counter == 1)) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception // Ввод римских цифр от 1 до 10");
                return "";
            }
        }

        // Перевод с Римских чисел в Арабские (от 1 до 10)
        int a = 0;
        int b = 0;

        for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
            if (strA.equals(arrRom.getValue())) {
                a = Integer.parseInt(arrRom.getKey());
            }
            if (strB.equals(arrRom.getValue())) {
                b = Integer.parseInt(arrRom.getKey());
            }
        }



        int itogRome = 0;

        try{
            switch (userOperator) {
                case "-":
                    itogRome = a - b;
                        if (itogRome <= 0 ) throw new Exception();
                    return this.getRomeNumber(itogRome);
                case "+":
                    itogRome = a + b;
                        if (itogRome <= 0 ) throw new Exception();
                    return this.getRomeNumber(itogRome);
                case "*":
                    itogRome = a * b;
                        if (itogRome <= 0 ) throw new Exception();
                    return this.getRomeNumber(itogRome);
                case "/":
                    itogRome = a / b;
                        if (itogRome <= 0 ) throw new Exception();
                    return this.getRomeNumber(itogRome);
            }
        } catch (Exception e) {
            System.out.println("throws Exception // т.к. в римской системе нет 0 и отрицательных чисел");
            return "";
        }
        return "";
    }

    // Расчет и преображение араб в рим числа
    String getRomeNumber (int romeNumb) {

        int caseRomeNumb = romeNumb;
        char[] pass;

        if (caseRomeNumb >= 1 && caseRomeNumb <= 10) {
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (caseRomeNumb == Integer.parseInt(arrRom.getKey())) {
                   return arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 11 && caseRomeNumb <= 19) {
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (caseRomeNumb == Integer.parseInt(arrRom.getKey())) {
                    return arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 20 && caseRomeNumb <= 29) {
           String x = arrRome.get("20");
           pass = String.valueOf(caseRomeNumb).toCharArray();
           if (String.valueOf(pass[1]).equals("0")) {
               return x;
           }
           for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
               if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                   return x+""+arrRom.getValue();
               }
           }
        } else if (caseRomeNumb >= 30 && caseRomeNumb <= 39) {
            String x = arrRome.get("30");
            pass = String.valueOf(caseRomeNumb).toCharArray();
            if (String.valueOf(pass[1]).equals("0")) {
                return x;
            }
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                    return x+""+arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 40 && caseRomeNumb <= 49) {
            String x = arrRome.get("40");
            pass = String.valueOf(caseRomeNumb).toCharArray();
            if (String.valueOf(pass[1]).equals("0")) {
                return x;
            }
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                    return x+""+arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 50 && caseRomeNumb <= 59) {
            String x = arrRome.get("50");
            pass = String.valueOf(caseRomeNumb).toCharArray();
            if (String.valueOf(pass[1]).equals("0")) {
                return x;
            }
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                    return x+""+arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 60 && caseRomeNumb <= 69) {
            String x = arrRome.get("60");
            pass = String.valueOf(caseRomeNumb).toCharArray();
            if (String.valueOf(pass[1]).equals("0")) {
                return x;
            }
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                    return x+""+arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 70 && caseRomeNumb <= 79) {
            String x = arrRome.get("70");
            pass = String.valueOf(caseRomeNumb).toCharArray();
            if (String.valueOf(pass[1]).equals("0")) {
                return x;
            }
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                    return x+""+arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 80 && caseRomeNumb <= 89) {
            String x = arrRome.get("80");
            pass = String.valueOf(caseRomeNumb).toCharArray();
            if (String.valueOf(pass[1]).equals("0")) {
                return x;
            }
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                    return x+""+arrRom.getValue();
                }
            }
        } else if (caseRomeNumb >= 90 && caseRomeNumb <= 99) {
            String x = arrRome.get("90");
            pass = String.valueOf(caseRomeNumb).toCharArray();
            if (String.valueOf(pass[1]).equals("0")) {
                System.out.println(x);
                return x;
            }
            for (Map.Entry<String, String> arrRom : arrRome.entrySet()) {
                if (Integer.parseInt(String.valueOf(pass[1])) == Integer.parseInt(arrRom.getKey())) {
                    return x+""+arrRom.getValue();
                }
            }
        } else if (caseRomeNumb == 100) {
            return arrRome.get("100");
        }
        return "";
    }

}