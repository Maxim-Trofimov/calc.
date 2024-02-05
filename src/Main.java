
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine(); // 5 + 5 или V + V

            String[] str = input.split(" ");

            if (str.length != 3) {
                throw new IllegalArgumentException("Неверный формат ввода");
            }

            String num1Str = str[0];
            String operation = str[1];
            String num2Str = str[2];

            int num1, num2;

            if (isRomanNumeral(num1Str) != isRomanNumeral(num2Str)) {
                throw new IllegalArgumentException("Числа должны быть либо арабскими, либо римскими");
            }
            else if (isRomanNumeral(num1Str) && isRomanNumeral(num2Str)) {
                // если оба числа римские, преобразуем их в арабские
                num1 = romanToArabic(num1Str);
                num2 = romanToArabic(num2Str);
            } else {
                // иначе, считаем, что оба числа арабские
                num1 = Integer.parseInt(num1Str);
                num2 = Integer.parseInt(num2Str);

                //
                if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                    throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
                }
            }

            int result;
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Неверная операция");
            }

            // eсли введенные числа были римскими, вывести результат в виде римского числа
            if (isRomanNumeral(num1Str) && isRomanNumeral(num2Str)) {
                if (result <= 0) {
                    throw new IllegalArgumentException("Результат в римской системе не может быть отрицательным или равным нулю");
                }
                System.out.println(arabicToRoman(result));
            } else {
                System.out.println(result);
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат числа");
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static boolean isRomanNumeral(String input) {
        return input.matches("^[IVXLCDM]+$");
    }

    private static int romanToArabic(String input) {
        int result;
        switch(input){
            case "I":
                result = 1;
                break;
            case "II":
                result = 2;
                break;
            case "III":
                result = 3;
                break;
            case "IV":
                result = 4;
                break;
            case "V":
                result = 5;
                break;
            case "VI":
                result = 6;
                break;
            case "VII":
                result = 7;
                break;
            case "VIII":
                result = 8;
                break;
            case "IX":
                result = 9;
                break;
            case "X":
                result = 10;
                break;
            default:
                throw new IllegalArgumentException("Неверный символ в римском числе: " + input);
        }
        return result;
    }

    private static String arabicToRoman(int number) {
        char[] romanChars = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] arabicValues = {1, 5, 10, 50, 100, 500, 1000};

        StringBuilder result = new StringBuilder();


// проходим по массиву символов и добавляем их к результату
        for (int i = romanChars.length - 1; i >= 0; i--) {
            while (number >= arabicValues[i]) {
                result.append(romanChars[i]);
                number -= arabicValues[i];
            }
        }

        return result.toString();
    }
}

