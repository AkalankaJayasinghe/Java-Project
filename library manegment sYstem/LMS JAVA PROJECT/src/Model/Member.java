package Model;

public class Member {

    private String memberID;
    private String name;
    private String contactInfo;
    private String memberType;
    private String cardNumber;
    private int expireYear;
    private Membership mb1 = new Membership();

    public Member(String name, String contactInfo, String memberType) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.memberType = memberType;
        expireYear = mb1.getExpireYear();
        cardNumber = mb1.getCardNumber();

    }

    public String getMemberType() {
        return memberType;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public int getExpireDate() {
        return expireYear;
    }

    public String getCardNumber() {
        return cardNumber;
    }

}

