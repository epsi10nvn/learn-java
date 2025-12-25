package builder;

import domain.Member;

public class MemberBuilder {
    private String memberName;

    public MemberBuilder memberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public Member build() {
        validate();
        return new Member(memberName);
    }

    private void validate() {
        if (memberName == null || memberName.trim().isEmpty()) {
            throw new IllegalStateException("Member name is required");
        }
    }
}

