package me.whiteship.demospringsecurityform.account;

public class AccountContext {
    /**
     * java.lang 패키지에서 제공하는 Thread 범위(Scope) 변수.
     * 즉, Thread 수준의 데이터 저장소.
     *
     * - 같은 Thread 내에서만 공유.
     * - 따라서 같은 Thread라면 해당 데이터를 메소드 매개변수로 넘겨줄 필요 없음.
     * - SecurityContextHolder의 기본 전략.
     */

    private static final ThreadLocal<Account> ACCOUNT_THREAD_LOCAL = new ThreadLocal<>();

    public static void setAccountThreadLocal(Account account) {
        ACCOUNT_THREAD_LOCAL .set(account);
    }

    public static Account getAccount() {
        return ACCOUNT_THREAD_LOCAL.get();
    }
}
