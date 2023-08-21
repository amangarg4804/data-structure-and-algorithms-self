package poc;

import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptEncryptDecrpt {
    public static void main(String ... args){

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray("root".toCharArray());

        String encryptedKey = textEncryptor.encrypt("-----BEGIN PGP PRIVATE KEY BLOCK-----\n" +
                "\n" +
                "lQdGBGTLRv0BEADNtZb8/hOdFlnO0eaLNwA9Ms83gnodM7xiimqydu7Agv0yakLc\n" +
                "FKu/GzsSS1rDeEhm854098hYDLlF4nsrlIVgDm8/zQb7sAx0xWh5Ob1ZqzR3/jeV\n" +
                "6n8l+arl50y6nF29pQUDWmFZ4V9ZfRC+4nowLmzKhmD51ZJRWMhdGQx3DEDXK+p3\n" +
                "nB0vWoY414nI1vxDFW5u+6k3iFbQeTajd28x9rs/gS8pyKMY2nP8CjxegB2ag5M0\n" +
                "Pghh1Z8tMamyAbDz/MC8VDu/cT0BopTv15EAel3AyKuIAWNlKHvqwP+n1RStCni/\n" +
                "IGXE5/9+FPmByqu4r2lkqSZmOZYDWRHGyWd4Ul7388XcNxvP7eH1e8fRi2w6gRP5\n" +
                "D8hQ/TdDn63fw1z9XINbuKLprIf4INVQKlGOwv7dp5gjFj71gP7IkaSLDMVWEwlo\n" +
                "dyAgPHgL8jPGsQ0wmURGu+M1n1cmeZl8HZepR5XHhFYVpqmgUN8tMjiymU41Np7g\n" +
                "406khToUxcVqKk3lJkJHFIfamYWolhWD0LFrVEUgT2TYSH4dK5exZ/+/AgCGdf/j\n" +
                "WFvL1tXVo6dkN0S5nzWDsLmairuzKQKus5J8668qGX3DKEUeIJMSmJa345/21eNP\n" +
                "8I2zSLHmLUV6VkZQeJo6BoZUqmuW/mfr14WXMHiUqbFMztGoZMxXu7fLewARAQAB\n" +
                "/gcDAkelh02Bo1hX96FAP4MEVJ5pY508spnECXa8xO4UgdC3ZzweZfwyRe0clv3r\n" +
                "/mc5drvvhsXhxgesUU0UaeXky4MVWiB+lrUhiUzmN+1ZjPbS282jt5OOZF0Dy6iW\n" +
                "7QxXyS8jB1zyj/JYcWTyfdQeiFFrhBFRIYOWmZMdfEGntF7ZrXECXXjr/z9kqk8m\n" +
                "jQVpxL/Z41TatFzp7292LZX9JU6SBQlXiLZZnjldAPc22AMJ1B11svfkiJWsuQJX\n" +
                "mWjG8w4pTvkS85hWcrByz8NfTnBEcpGYFD6x6D3I6+/Vx36TwS+NHnTDwz5Ja0dd\n" +
                "/wf9xETGe2cacfL7lzyleKhQYd+XhUyoEixZja7NvlZoFuO1GIgs0CodAT77uRsW\n" +
                "R6mSdptTse7eTwOYGpY/obrvn4FBUlg0+oEiQfdPskniSNssR3GaHT/fF2hEkAaU\n" +
                "vjsT8/1QI5m8NRx6gxqX/Ji4kYfHC0W1wrWw1oWuUC/QDIW+6cUk7gGqTYINt2e4\n" +
                "sPD5nzdFftKMdKL5uSdFjln/pG9JDSGpPf2FofNEiqd1z5vmIM6Cn3h66X1HuSnK\n" +
                "yGh3TLwbHnX4oRLi5RQDegO8/AvZP0RkiiZaTrehumgd9HIGRwCNr97a0Gi7NZ2u\n" +
                "qa8vnWqnfIr7nKjlpn2xXHqu/cZ+aqSh2GTUDmgRJPplZWRoxjA5YE/IlzNn8BMH\n" +
                "yuAXwchC1D9OQ0EyuY530Ymax9QZgPVB/HCpD9vArT843395J7cnYnlZ9AZ3ui9E\n" +
                "EM3QxDLLEvUJzGLOmv+L9S8iJn9PyEg2OE7oA1CIGiZrcMOUJtrxg4gCEICnAA8U\n" +
                "Aqoa/ZWtamugza/1Mg2qajJwL/n611Ez9hOYCmxBcVqAMxlBnVMFEbhSotecn8Qe\n" +
                "KQyjVYmBOU431Vga4Iq+HeE/07W5cNstmcuM9qkDMNripirwsOPS8M9NF9liPm8m\n" +
                "Tw+srqhCaXk8XIR3N1A4cECmlARD61IH3CY/O+hMgo2nti+WL7DQJu0IAhbEW/uI\n" +
                "Y2c62qg/dHjsCwShMTmGdMRkFFpGtbN+vXfJ87P4idwJ30cQd3pg0bEnKVPE9VqQ\n" +
                "e5LQj2ZRkB2rY/pR9k3UgEjSUeIaKEZnV/417laB8w++uK3h9l9N5QJWmuV7KsrC\n" +
                "ExMnMTNUmrYWgPU+PZYoleU7SCeGc0VlFfwM8Hngu0s4SNt1meUZlXm47h1IYK4f\n" +
                "B1QPH/HmKKDdOQZG+fSkgiKn0FCaRpiD1abZs6L7Z1xRJC4kpL5NoZl34g6EBX3L\n" +
                "Vaw6IhB/eKFCFqTxzxxaUGACeHcUA0urlselkXf6jD9XPQD9x1q0ByNcJcEQRZQB\n" +
                "qWRrBbGcbyjt/F1jijSA7H93FtEHS7AHwaBOkyvL1vLV9n31Oy5rcu8AgJaTKjLm\n" +
                "1uHBJs5w8dSBiVJUuU33M5j/Csv1nJ/UEqgMVqdgROpxKUfcVG0cit0FoharkjBj\n" +
                "c4+YZl0V37wR2oT5x/dopLh4VFK697M/GGQAavRbBWeQEkLrdornf1b82NN2v3UR\n" +
                "Ql6fPFJqg5yV4F2zhxYHHNFDiZXBqKkGigiTry/N0mFQGy3vhs0YqwJ4CLdKP07y\n" +
                "OuJLUB41LupdW6MZbJPDLqK9PmSwW5NXq/E4wwmPtbBlbkRHirzSZa9GLWJWxx1P\n" +
                "GTCSELmRf9E3CEF+iyth7/9Crh9mcgu8O90XM5J/+059oqZYxGjSyIya5Am4ThuL\n" +
                "F/iw90XZDUoTad8d09FmgJqomdCyao3qXyZJCkGvryziMmlQaPaqv0W0M2luc3Rh\n" +
                "LWNhcmQtcmVzcG9uc2UtcHJvZCA8YW1hbi5nYXJnQGFpcnRlbGJhbmsuY29tPokC\n" +
                "VAQTAQgAPhYhBBrrZt4DNVeEfCZ9G1m0JR0SXvXUBQJky0b9AhsDBQkHhh84BQsJ\n" +
                "CAcCBhUKCQgLAgQWAgMBAh4BAheAAAoJEFm0JR0SXvXU/qcP/R1OqtgrtBOXoNVK\n" +
                "H9X5S4XlpfvWDKoRKLWhyGfNfYvQ2vVem/8ow8i23N23ZTlAPEHuv92BMu7uZzl0\n" +
                "sYcGW7bYP8nJSQ1zHvRhEsgqU40qatiiR3YEKVaivndfmFkVgGqM6C1kjs1f014X\n" +
                "35GG+wTI6L5U9Hxme+qCIGctuvyz3pt/3UHkVzYbZOLt8LOOBYJTOT8GCKT8q5R4\n" +
                "KYf69CnBZcCdFglcBzkxOzBY0PThIN44NnJAqACE/HdW8FPB5jkDXOBcSkXSmLVm\n" +
                "fUwNm6fsShzHjg84Cxjh8DZmgINdwAE19dNtvny5dSQzU2eyXLyndp6rpELYdg3T\n" +
                "MM1YWHvFaWXjNJxut291MJPUeIb0/heU+YyGJX4jeePZGg36AKrPnlWho2vFWklj\n" +
                "aWKv0qjS0P7Wl+t5+SSXnaT7eVldjH5wWhTgl+9URY2kkj4pHIOO1UDRoS6Os01s\n" +
                "XnWww6L4+rWrtfWyKxg76lA7VXiRDn3FuC0P65Zz9p0icXFphr+idxdd75KcS0cl\n" +
                "f2Ttik3C+iXsuDr/IxkDtKcCi18uHKGTp7QTAqKpw7VofJUe0zaU/GpknjlBL66S\n" +
                "Fq6bBjMB01+CnmUjJDxewaIJpXYYBPiAZP95T/8bFHDSAh2FjpahlwaogblqB2iz\n" +
                "5TR9s1v0oGjeOx/Wlyje53P+4G02nQdFBGTLRv0BEADI9Ciukm8Q2y0Hjijr7wWL\n" +
                "JkbmbQyMNOPpaKsNcSG8cbTXZiijV4c2d9LZjy3XzdmkzU6oG1lfugQxhLZe1wZu\n" +
                "p/+z+n9ASqbYp+elk3NI4Q+MuRSwAEu9rQgLibvc88PMk9Yn9GvArm6xCoobBkix\n" +
                "ebFAdIyCrre6VGm5TPV6H1KVdW6/1MTVjVvVaND+IlrxyeoiygdJ4QrzpQpc0BFl\n" +
                "vtIY8H0AKDBbqQwfa2kPvl1gy2X+SoKftemsp4KOPwcVMUkumL+UMsdvFBeZJx5C\n" +
                "dMMoEceGvoGN0d5Mbo+jBxpCqwvAPxzeOY9c6RL8n3/7OhCXbZjFxi4sZcennZuc\n" +
                "Fg4ji/6UGgD3ZjaZTM33MpJaFODy2lLlnkcUMZCXmGAQncEjWxuSBngaowjkGkGn\n" +
                "Anf/Ec2ITAopfUrlZWoLg8sK4gHpGCv1+pTMcMgzT8ixpW4f0Wxb4QQtYJITtkFT\n" +
                "U4Nt89flRLWbFgVaj+cPYVy+WoG8C7KQUpMT9Bi2qA3yDySKw6/4MqcNxvq1i6En\n" +
                "dmFeMyvYTPH3gzagnQUkOfBVUqGL6PejEzREjcOhEAdGqSRgVbNBE88i6HPBjCjX\n" +
                "xXI+QsI9tO65LkCXpPVAO6vVjvIon5RTtWXZuc6F35C3WlKnqxYxCqZ+duN+WMuA\n" +
                "4ahtSeJS7+jS4ddT883V+wARAQAB/gcDAp52wyCOEiQl9xgZbBFqtUOgi9JkmarC\n" +
                "8JQdu5FlUhApYAcHqLL2C67QDd12I92JLonmSf6TjZTH3IZmnfWWKpe4JoyCFkQ8\n" +
                "NkVawp5fUVA4yRdzv/fgti1ojM2DOkZOre0kTzD6BGGQ/zVo6lBV5evH0ucWyLlj\n" +
                "HyHrrM5mQCqD+amWLqc+hjaUbVSDQXqP4GPC9HSr+EANU/bUT/jorvZfcsPtRd4j\n" +
                "oHC/Xc40tDckh0cy9VerpTC3AsNvExdsFTco89Kavpc+ICqOpZBMx/5OaTGS+NEQ\n" +
                "vqcj8RiSs7r6kUwZnRRmQHeq7PJLyVMEcHEUwOddllSeA8LGgBKqgvo6XcSmOnk6\n" +
                "7eS9EyQxCYo14NnlWVVBWYPBTHFeV0jxD7GofUPoh+1MiCTQNFODpcUwwc3yQiXH\n" +
                "+MpSQumEGbnpea1/WaT1w2WTgk77wH0sU3h5cbZd05vM5IH3Bndoj9s5XyoztBv5\n" +
                "2r2jCUW/lzOnnuyofYDKOh9Krd8h1UtGc68cXBBiDy0YkDjRp5K57pAJzNGvulEO\n" +
                "D3DkicYmKkRkq9b3yKOyv0WvZfvekY+a7ehfBLBSTffybVAlNzzH2RLQYuSuQsO6\n" +
                "ZI+kctrqRj5HBKQxfPfZVXx49AnYaUBB4Gj9nYTf6/6vRcA82aa4cueLaNt8KNkr\n" +
                "eTCdk1x/3WVK1MlTCSgzOwsYClElm7Db7h69ScvVH9cPu3i3t4HaFVip0eKDlKN3\n" +
                "h2bLrKL6RnJgSRAF0h+O5huIk1aG+9OTsGDIxOKlS79Fj5Hs8oN0LKggY1M/2up8\n" +
                "+Dry6lLafBxHzJJXVBHYDB09s87SN8VYtaE4yZklX5eODMOGM5/V+diWuU+AxKCe\n" +
                "og7DKpZkQxZm9f8yRrL5x/unSv8Vj2hOwz+olARL/l3JikLeU/SIfIrOQcWfTVcs\n" +
                "u4pG3PN+ZaGJopkG+ljP/11mKj3/tiuW7iZbCVkbvVB4b/0mGTALUDnWdDhj1FRy\n" +
                "V1lI/F/nYwfpYcuJp6DqMlvB81ewS/DA1beZZWHV1sIQIBVcfnONbXSwbucO+Naz\n" +
                "o85D+L6s2WLRsQLevPB/CUrbZCOA3WH0BXwroAUYIbTamr7YtHf2SDN1NZbPSC8H\n" +
                "8wvWZUyEOC4Sw0hufzHEw57MtWZvaEDT59lY+aZW58jaykRrBVrG3oex0Hi7gz2o\n" +
                "3KwsZangrCKdMLn8pE2XwRAKKbOp6f+nWrM/N18febVUsY31BywOWynejKbfR9FN\n" +
                "ptXdDp/vbGhc4JM0adcb0z/H+nowZNcKY1NPmOstfY3fhze5EKpEcbbtAbTsK5Ea\n" +
                "/BYjjhNtfwelOnDSAqsuYR3hUGC4ZNX1Lbo4K455tUF4VQBoCVBUkDu6oKcfY+JW\n" +
                "w3yx13bTXNQhYx1BaewR380odhU/dshpONWMn/D9Kz6rQB+s6T9B4Ba8eR9O8VQx\n" +
                "U1iGL7T5eSS+cAW5B2BLfyBeZbH83pHqvbR45PdcPmrZUhgTrZRRpub/g3ScELYk\n" +
                "Ovt3YkCwxp0CKaRqQAW/tCOT3umngR9OPCttHiSavSdMqdt5a5PiK6Tt/hAduXh9\n" +
                "7rUfOqSjg7DDZvVRCDnzEdmp8Wk2SA78RSJa/K7CMJlMLIpgTiZLAki5d43jlP4z\n" +
                "PW37zOibVT6Bou4k6X1L/MltHnwPgLHSV3H7R3v0cBfATpthwO/IrZjB/cRCiLgq\n" +
                "Xt5zayCaPpVcbWcdAnavpfhiBQY/y5ahGxp6zGfouL7BtHwPFrrkXPO/V87ox4jX\n" +
                "6wCMKb8+x9ZsuRkmHokCPAQYAQgAJhYhBBrrZt4DNVeEfCZ9G1m0JR0SXvXUBQJk\n" +
                "y0b9AhsMBQkHhh84AAoJEFm0JR0SXvXUiqsP/3eBXIhOj45XRyfNtZdevSqyJiX7\n" +
                "0xduFrfFHNJtof9FtUVZlZA7dLeQb1NTWxj4PMRYMfcP8SlWE86XHdxDvAVpC3ev\n" +
                "wS62iIK3YwTVvpY7yCb3WSZ9aT+oWJ+wQLpg9oKdjP0moCdkSo2QA+hBLXmjWFTB\n" +
                "RPbX1uA6xnvRac4QoEyAW9qUcXVlGzJGRw+sy53aUay1V4CEesaP2q3K3NZKgUFh\n" +
                "C00dymPmBhc8nCyfOvIGRgD5hp6zIJlU7pZkx4KDS6CYmJINYwE712xq8zJIu7PZ\n" +
                "13on4SKUoIldjehZCbWYInsgoeqn4lpc5MpEFFpyHxewEQZFXWGTISQZBYqzBG5h\n" +
                "WoxkFrb+2EMyILR+XC0Uv2gqwt5a1o3B/iwNxJZjacipqsy1OPW3Q7cpkvnkg1oj\n" +
                "qjU+K42OiATX6XO+X6odv0XkN1hAbRHwsSprre8Mw4/ck/BsonkNYDYzbQd76Bav\n" +
                "niqs3JrdMBmJHeQO/OvhuObqvBVNt5DIHe6c/gI2Bu/VOCMfPH7blH/G6PKJuEmv\n" +
                "07h8nhPjXUICGcEMuTeFZO8L9HFECoZ4mXHKkahzsKPiuvbAssjXVXuG3sM6lnIR\n" +
                "OjoxnWxXANWMVv/X8fS11yb4EW4w5q9GDhUHT30Qd4X/WGLcCKH3yUmM3EB3THhl\n" +
                "GxYF63EbDydMyayf\n" +
                "=JZH9\n" +
                "-----END PGP PRIVATE KEY BLOCK-----");

        String decryptedKey = textEncryptor.decrypt(encryptedKey);


        System.out.println("********** Encrypted Key *********");

        System.out.println(encryptedKey);

        System.out.println("********** Encrypted Key *********");


        System.out.println("********** Decrypted Key *********");

        System.out.println(decryptedKey);

        System.out.println("********** Decrypted Key *********");


    }

}

