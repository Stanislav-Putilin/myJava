package itstep.learning.services.randstring;

import java.util.Random;

public class PermanentPassword implements RandStringService{
    @Override
    public String getRandString() {
        String keyboardCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{}|;:',.<>?/`~";
        Random random = new Random();

        int length = 10 + random.nextInt(11); // генерирует случайную длину от 10 до 20 символов

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(keyboardCharacters.length());
            char randomChar = keyboardCharacters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}