package exception.custom_exception;

import exception.AppException;

public class MemberNotFoundException extends AppException {
    private final String memberId;

    public MemberNotFoundException(String memberId) {
        super("MEMBER_NOT_FOUND", String.format("The member with ID '%s' was not found.", memberId));
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
}
