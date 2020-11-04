package increment4;
import java.util.Random;
public class Core_Func {
        public  String getProblems(int n){
            String problems = "";
            Random r = new Random();
            int answer;
            int choice;
            int a;
            int b;
            while(n > 0){
                do {
                    choice = r.nextInt(2);
                    choice =  choice == 1?1:-1;
                    a=r.nextInt(100);
                    b=r.nextInt(100);
                    answer = a + choice * b;
                }while (answer  < 0 || answer > 100);
                String symbol = choice == 1?"+":"-";
                problems +=  new Integer(a).toString() +" " + symbol + " " +  new Integer(b).toString() + "\n";
                n--;
            }
            return problems;
        }
        public static int getAnswer(String p){
            int answer  = Integer.parseInt(p.split(" ")[0]) +
                    (44 - p.split(" ")[1].charAt(0))*
                            Integer.parseInt(p.split(" ")[2]);
            return answer;
        }
}
