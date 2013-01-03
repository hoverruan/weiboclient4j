package weiboclient4j;

import weiboclient4j.model.VerifyNicknameResult;
import weiboclient4j.params.Nickname;

/**
 * @author Hover Ruan
 */
public class RegisterService extends AbstractService {
    public RegisterService(WeiboClient client) {
        super(client);
    }

    public VerifyNicknameResult verifyNickname(Nickname nickname) throws WeiboClientException {
        return doGet("register/verify_nickname",
                withParams(nickname), VerifyNicknameResult.class);
    }
}
