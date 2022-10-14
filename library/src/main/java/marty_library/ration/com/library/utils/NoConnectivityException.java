package marty_library.ration.com.library.utils;
import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "데이터 요청에 실패 하였습니다.\n" +
                "사용중인 네트워크 상태를 확인해주세요.";
        // You can send any message whatever you want from here.
    }
}