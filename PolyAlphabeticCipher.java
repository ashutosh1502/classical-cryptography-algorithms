import java.util.Scanner;

public class PolyAlphabeticCipher {

    protected static String plainText="";
    protected static String cipherText="";
    protected static String key="";
    public int choice;
    public static Scanner sc=new Scanner(System.in);


    public PolyAlphabeticCipher(){
        try{
            System.out.println("Select the operation.");
            do{
                System.out.print("1.Encrypt  |  2.Decrypt |  (Press 0 to Exit)\n> ");
                choice=sc.nextInt();
                sc.nextLine();
                if(choice==1){
                    encrypt();
                }else if(choice==2){
                    decrypt();
                }else if(!(choice==0)){
                    System.out.println("Invalid input!");
                }
            }while(!(choice==0));
        }catch(Exception e){
            System.out.println("E1: Invalid input:"+e);
        }
    }

    public static void encrypt(){
        try{
            System.out.print("Enter the Plain Text: ");
            plainText=sc.nextLine();
            System.out.print("Enter the Key: ");
            key=sc.nextLine();
            System.out.println("Encrypted Text (cipher text): "+PolyAlphabeticCipherAlgorithm(plainText.toUpperCase(),key.toUpperCase(),true));
        }catch(Exception e){
            System.out.println("E2: Invalid input:"+e.getMessage());
        }
    }

    public static void decrypt(){
        try{
            System.out.print("Enter the Cipher Text: ");
            cipherText=sc.nextLine();
            System.out.print("Enter the Key: ");
            key=sc.nextLine();
            System.out.println("Decrypted Text (plain text): "+PolyAlphabeticCipherAlgorithm(cipherText.toUpperCase(),key.toUpperCase(),false));
        }catch(Exception e){
            System.out.println("E3: Invalid input:"+e.getMessage());
        }
    }

    public static String PolyAlphabeticCipherAlgorithm(String text,String key,boolean encrypt){
        int toAdd=text.length()-key.length();
        StringBuilder newKey=new StringBuilder(key);

        for(int i=0;i<toAdd;i++){
            newKey.append(key.charAt(i%key.length()));
        }

        System.out.println("Regenerated Key: "+newKey);

        StringBuilder processedText=new StringBuilder();

        int textCharValue,newKeyCharValue,temp;
        char cipherChar;
        for(int i=0;i<text.length();i++){
            textCharValue=text.charAt(i);
            newKeyCharValue=newKey.charAt(i);
            if(encrypt)
                temp=(textCharValue+newKeyCharValue)%26;
            else
                temp=(textCharValue-newKeyCharValue+26)%26;
            cipherChar=(char)(temp+'A');
            processedText.append(cipherChar);
        }

        return processedText.toString();
    }


    public static void main(String[] args) {
        new PolyAlphabeticCipher();
    }
}
