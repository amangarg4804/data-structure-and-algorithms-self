package poc;

import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptEncryptDecrpt {
    public static void main(String ... args){

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray("root".toCharArray());

//        String encryptedKey = textEncryptor.encrypt("-----BEGIN PGP PRIVATE KEY BLOCK-----\n" +
//                "\n" +
//                "lQdGBGTLRv0BEADNtZb8/hOdFlnO0eaLNwA9Ms83gnodM7xiimqydu7Agv0yakLc\n" +
//                "FKu/GzsSS1rDeEhm854098hYDLlF4nsrlIVgDm8/zQb7sAx0xWh5Ob1ZqzR3/jeV\n" +
//                "6n8l+arl50y6nF29pQUDWmFZ4V9ZfRC+4nowLmzKhmD51ZJRWMhdGQx3DEDXK+p3\n" +
//                "nB0vWoY414nI1vxDFW5u+6k3iFbQeTajd28x9rs/gS8pyKMY2nP8CjxegB2ag5M0\n" +
//                "Pghh1Z8tMamyAbDz/MC8VDu/cT0BopTv15EAel3AyKuIAWNlKHvqwP+n1RStCni/\n" +
//                "OjoxnWxXANWMVv/X8fS11yb4EW4w5q9GDhUHT30Qd4X/WGLcCKH3yUmM3EB3THhl\n" +
//                "GxYF63EbDydMyayf\n" +
//                "=JZH9\n" +
//                "-----END PGP PRIVATE KEY BLOCK-----");
        String encryptedKey ="";
        String decryptedKey = textEncryptor.decrypt(encryptedKey);


        System.out.println("********** Encrypted Key *********");

        System.out.println(encryptedKey);

        System.out.println("********** Encrypted Key *********");


        System.out.println("********** Decrypted Key *********");

        System.out.println(decryptedKey);

        System.out.println("********** Decrypted Key *********");


    }

}

