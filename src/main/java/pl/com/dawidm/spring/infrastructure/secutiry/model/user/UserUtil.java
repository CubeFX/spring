package pl.com.dawidm.spring.infrastructure.secutiry.model.user;

import java.util.function.Function;

public interface UserUtil {
    Function<UserApp, Long> toId = user -> user.id;
}
