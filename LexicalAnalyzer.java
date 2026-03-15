import java.io.*;

public class LexicalAnalyzer {

    static boolean isKeyword(String buffer){

        String keywords[] = {
        "auto","break","case","char","const","continue","default",
        "do","double","else","enum","extern","float","for","goto",
        "if","int","long","register","return","short","signed",
        "sizeof","static","struct","switch","typedef","union",
        "unsigned","void","volatile","while","main"
        };

        for(String k : keywords){
            if(k.equals(buffer)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        FileReader fr = new FileReader("program.txt");
        BufferedReader br = new BufferedReader(fr);

        int ch;
        String buffer="";
        String operators="+-*/%=";

        while((ch = br.read()) != -1){

            char c = (char) ch;

            // operator check
            if(operators.indexOf(c) != -1){
                System.out.println(c + " is operator");
            }

            // letter or digit
            if(Character.isLetterOrDigit(c)){
                buffer += c;
            }

            else if((c==' ' || c=='\n' || c=='\t' || c==',' || c==';' ||
                    c=='=' || c=='+' || c=='-' || c=='*' || c=='/' || c=='%')
                    && buffer.length()!=0){

                if(isKeyword(buffer)){
                    System.out.println(buffer + " is keyword");
                }
                else{
                    System.out.println(buffer + " is identifier");
                }

                buffer="";
            }
        }

        br.close();
    }
}