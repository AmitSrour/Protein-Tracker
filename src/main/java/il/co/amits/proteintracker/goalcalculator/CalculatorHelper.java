package il.co.amits.proteintracker.goalcalculator;

/**
 * Created by Administrator on 18/03/2016.
 *
 * //mostly referenced by: http://www.globalrph.com/protein-calculator.htm
 */
public class CalculatorHelper {
    public static float idealBodyWeight;

    /**
     * age must be 1<,
     * isMan?,
     * weightInkilo if is pound just *2.2,
     * activityLevel must be int 1-5
     * if 0 is returned then you dun goofed show n error.
     */
    public static double calculateProteinGoal(int age,boolean isMan,double weightInKilo, int activityLevel){
        double result = weightInKilo*0.8;
        switch (activityLevel)//activity levels 1 to 5
        {
            case 1:
                result = 0.8;
                break;
            case 2:
                result = 1.2;
                if(!isMan){
                    result-=0.15;
                }
                break;
            case 3:
                result = 1.6;
                if(!isMan){
                    result-=0.2;
                }
                break;
            case 4:
                result = 1.9;
                if(!isMan){
                    result-=0.25;
                }
                break;
            case 5:
                result = 2.2;
                if(!isMan){
                    result-=0.3;
                }
                break;
        }
        //age multiplier and return of calculated result
        if (age<=1){
            result = 1.2;
            return result*weightInKilo;
        }
        if (age>1 && age<=3){
            result = 1.05;
            return result*weightInKilo;
        }
        if (age>3 && age<=8){
            result = 0.95;
            return result*weightInKilo;
        }
        if (age>8 && age<=13){
            result = 1.05;
            return result*weightInKilo;
        }
        if (age>14 && age<=18){
            result = 0.85;
            return result*weightInKilo;
        }
        if (age>=19){
            return result*weightInKilo;
        }
        return result*weightInKilo;
    }

}
