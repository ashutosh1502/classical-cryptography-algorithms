import java.util.Scanner;
public class CaesarCipher{

    public static final String ALPHABET="abcdefghijklmnopqrstuvwxyz";

    public static String encryptData(String data,int shiftKey){
        data=data.toLowerCase();
        String encryptedString="";
        int pos;
        try{
            for(int i=0;i<data.length();i++){
                pos=ALPHABET.indexOf(data.charAt(i));
                char encryptChar=ALPHABET.charAt((pos+shiftKey)%26);
                encryptedString+=encryptChar;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return encryptedString;
    }

    public static String decryptData(String data,int shiftKey){
        data=data.toLowerCase();
        String decryptedString="";
        int pos;
        try{
            for(int i=0;i<data.length();i++){
                pos=ALPHABET.indexOf(data.charAt(i));
                int decryptPos=(pos-shiftKey)%26;
                if (decryptPos < 0){   
                    decryptPos = ALPHABET.length() + decryptPos;   
                } 
                char decryptChar=ALPHABET.charAt(dcpos);
                decryptedString+=decryptChar;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return decryptedString;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the data string: ");
        String data=sc.nextLine();
        if(data.contains(" ")){
            System.out.println("Invalid input: Data string cannot contain spaces.");
            return;
        }
        System.out.println("Enter the shift key:");
        int shiftKey = sc.nextInt();
        System.out.println("\nOriginal data: "+data);
        String encryptedString = encryptData(data, shiftKey);
        System.out.println("Encrypted data: "+encryptedString);
        System.out.println("Decrypted data: "+decryptData(encryptedString,shiftKey));
        sc.close();
    }
}
