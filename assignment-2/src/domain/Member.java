package domain;

import java.util.UUID;

public class Member {
    private final String id;
    private final String memberName;

    public Member(String memberName) {
        this.id = UUID.randomUUID().toString();
        this.memberName = memberName;
    }

    public String getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void displayInfo() {
        System.out.println("=== Member Info ===");
        System.out.println("Member ID: " + id);
        System.out.println("Member Name: " + memberName);
        System.out.println("===================");
    }
}
