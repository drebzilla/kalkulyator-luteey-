import java.util.Scanner;

public class Main {
     public static void main(String[] args) throws Exception {
         Scanner sc = new Scanner(System.in);
         System.out.println("Введите два числа(арабских/римских): ");
         String input = sc.nextLine();
         System.out.println(calc(input));
     }

     public static String calc(String input) throws Exception {
         int num1, num2;
         String op;
         String res;
         boolean isRom;
         String[] operands = input.split(" ");
         if (operands.length != 3) throw new Exception("неправильно записан пример");
         op = detectOperation(input);
         if (op == null) throw new Exception("работают только +,-,*,/");
         if (Roman.isRom(operands[0]) && Roman.isRom(operands[2])) {
             num1 = Roman.convertToArabian(operands[0]);
             num2 = Roman.convertToArabian(operands[2]);
             isRom = true;
         }

         else if (!Roman.isRom(operands[0]) && !Roman.isRom(operands[2])) {
             num1 = Integer.parseInt(operands[0]);
             num2 = Integer.parseInt(operands[2]);
             isRom = false;
         }

         else {
             throw new Exception("числа должны быть в одном формате");
         }

         if (num1 > 10 || num2 > 10) {
             throw new Exception("числа не должны быть больше 10");
         }

         int arab = calculate(num1, num2, op);

         if (isRom) {
             if (arab <= 0) {
                 throw new Exception("Римское число должно быть больше нуля");
             }
             res = Roman.convertToRoman(arab);
         } else {
             res = String.valueOf(arab);
         }
         return res;
     }

     static String detectOperation(String op) {
         if (op.contains("+")) return "+";
         else if (op.contains("-")) return "-";
         else if (op.contains("*")) return "*";
         else if (op.contains("/")) return "/";
         else return null;
     }

     static int calculate(int a, int b, String op) {
         if (op.equals("+")) return a + b;
         else if (op.equals("-")) return a - b;
         else if (op.equals("*")) return a * b;
         else return a / b;
     }

     static class Roman {
         static String[] romArr = new String[]{"O","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                 "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                 "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                 "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                 "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                 "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                 "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                 "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                 "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                 "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};


         static boolean isRom(String val) {
             for (int i = 0; i < romArr.length; i++) {
                 if (val.equals(romArr[i])) {
                     return true;
                 }
             }
             return false;
         }

         static int convertToArabian(String roman) {
             for (int i = 0; i < romArr.length; i++) {
                 if (roman.equals(romArr[i])) {
                     return i;
                 }
             }
             return -1;
         }

         static String convertToRoman(int arab) {
             return romArr[arab];
         }
     }

}