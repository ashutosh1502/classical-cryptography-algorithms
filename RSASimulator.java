import java.math.BigInteger;
import java.security.SecureRandom;

public class RSASimulator {

    private BigInteger modulus;
    private BigInteger publicKey;
    private BigInteger privateKey;

    public RSASimulator(int bitLength) {
        SecureRandom random = new SecureRandom();

        BigInteger p = BigInteger.probablePrime(bitLength / 2, random);
        BigInteger q = BigInteger.probablePrime(bitLength / 2, random);

        modulus = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        publicKey = BigInteger.valueOf(65537); // Common choice for e
        while (phi.gcd(publicKey).compareTo(BigInteger.ONE) > 0 && publicKey.compareTo(phi) < 0) {
            publicKey = publicKey.add(BigInteger.TWO);
        }

        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(String message) {
        BigInteger messageInt = new BigInteger(message.getBytes());
        return messageInt.modPow(publicKey, modulus);
    }

    public String decrypt(BigInteger cipherText) {
        BigInteger messageInt = cipherText.modPow(privateKey, modulus);
        return new String(messageInt.toByteArray());
    }

    public static void main(String[] args) {
        RSASimulator rsa = new RSASimulator(1024);

        String message = "Hello, RSA!";
        System.out.println("Original Message: " + message);

        BigInteger cipherText = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + cipherText);

        String decryptedMessage = rsa.decrypt(cipherText);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
