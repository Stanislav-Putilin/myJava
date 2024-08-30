package itstep.learning.services.randstring;

import java.util.Random;

public class NameFile implements RandStringService{
    @Override
    public String getRandString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 36; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
