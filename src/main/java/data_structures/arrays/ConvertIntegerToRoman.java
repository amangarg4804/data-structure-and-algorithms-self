package data_structures.arrays;

public class ConvertIntegerToRoman {

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};


    public static void main(String[] args) {
        ConvertIntegerToRoman convertIntegerToRoman = new ConvertIntegerToRoman();
        System.out.println(convertIntegerToRoman.convertIntegerToRoman(9));
    }

    private String convertIntegerToRoman(int i) {
        return thousands[i/1000] + hundreds[(i%1000)/100] +
                tens[(i%100)/10] + units[i%10];
    }


}
