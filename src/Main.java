import java.io.IOException;
import java.util.Scanner;
import static java.lang.System.*;
public class Main {
    static String [] romans = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    static Character[] operations ={'+','-','/','*'};
    public static void main(String[] args) throws IOException {
        String input;
        out.print("Введите операцию -> ");
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        out.print("Результат операции =");
        out.println(calc(input));
    }
    public static String calc (String input)throws IOException
    {
        boolean firstIsRoman = false;
        boolean secondIsRoman = false;
        int numb1 = 0;
        int numb2 = 0;
        int result = 0;
        int pos = -1;
        int i=-1;
        while (pos ==-1&& i<4)
        {
            i++;
            pos = input.indexOf(operations[i]);
        }
        if (pos!=-1)
        {
            for (int j =0; j< romans.length;j++)
            {
                if (romans[j].equals(input.substring(0,pos)))
                {
                    firstIsRoman =true;
                    numb1 = j+1;
                }
                if (romans[j].equals(input.substring(pos+1)))
                {
                    secondIsRoman = true;
                    numb2 =j+1;
                }
            }
            if (!firstIsRoman&&!secondIsRoman)
            {
                numb1 = Integer.parseInt(input.substring(0,pos));
                numb2 = Integer.parseInt(input.substring(pos+1));
            }
            if (firstIsRoman&&!secondIsRoman)
            {
                throw  new IOException("Используются одновременно разные системы счисления");
            }
            if (!firstIsRoman&&secondIsRoman)
            {
                throw  new IOException("Используются одновременно разные системы счисления");
            }
            switch (i) {
                case 0 -> result = numb1 + numb2;
                case 1 -> result = numb1 - numb2;
                case 2 -> result = numb1 / numb2;
                case 3 -> result = numb1 * numb2;
            }
        }
        else
        {
            throw  new IOException("Строка не является математической операцией");
        }
        if (result>0&&firstIsRoman)
            return toRoman(result);
        else if (result<=0)
        {
            throw  new IOException("D римской системе нет отрицательных чисел и нуля");
        }
        return Integer.toString(result);
    }

    public static String toRoman (Integer numberInt) {
        String numberString = numberInt.toString();
        String romanNumber = "";
        if (numberInt > 10)
        {
            int firstDigit = Integer.parseInt(numberString.substring(0, 1));
            String secondDigit = numberString.substring(1);
            if (numberInt < 50)
            {
                if (numberInt>=40)
                romanNumber += "XL";
                else
                {
                    for (int i = 0; i < firstDigit; i++)
                    {
                        romanNumber += "X";
                    }
                }
            }
            else if(numberInt<90)
            {
                romanNumber += "L";
                for (int i = 0; i < firstDigit - 6; i++) {
                    romanNumber += "X";
                }
            }
            else
            {
                if(numberInt == 100)
                    romanNumber="C";
                else
                {
                    romanNumber+="XC";
                    romanNumber+=secondDigit;
                }
            }
            romanNumber += toRoman(Integer.parseInt(secondDigit));
        }
        else
        {
            if (numberInt == 10)
                romanNumber = "X";
            else if (numberInt == 9)
                romanNumber = "IX";
            else if (numberInt < 5)
            {
                if(numberInt==4)
                {
                    romanNumber = "IV";
                }
                else
                {
                    for (int i = 0; i < numberInt; i++)
                    {
                        romanNumber += "I";
                    }
                }
            }
            else
            {
                romanNumber += "V";
                for (int i = 0; i < numberInt - 6; i++)
                {
                    romanNumber += "I";
                }
            }
        }
        return romanNumber;
    }
}