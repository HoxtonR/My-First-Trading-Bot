package hoxtonr.project.HighRiskProject.NewCoinGrabber.constant;

public class HuobiApiKey {
    private static String api_key = "99ee0383-d4d3b6d0-861ba03b-bgrveg5tmn";
    private static String secret_key = "ce4b39f4-9342a1af-722572fe-1f9f1";
    private static Long accountId = 7290022L;

   // private static String api_key = "8494ebce-dbuqg6hkte-10e89102-6ce6e";
   // private static String secret_key = "b0413b52-b7e6304b-a3b593b6-c0641";
   // private static Long accountId = 16810182L;

    public static String getApi_key() {
        return api_key;
    }

    public static String getSecret_key() {
        return secret_key;
    }

    public static Long getAccountId() {
        return accountId;
    }
}
