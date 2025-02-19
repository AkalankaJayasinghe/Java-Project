
package Model;

import java.time.LocalDate;


public class Membership {
    
   private String cardNumber;
   private int expireYear;

    Membership() {
        expireYear = LocalDate.now().getYear() + 2;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getExpireYear() {
        return expireYear;
    }
}