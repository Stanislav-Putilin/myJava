package itstep.learning.services.randstring;

import java.util.Random;

public class TemporaryPassword implements RandStringService{

    @Override
    public String getRandString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
}
