package com.DigitaleFactuur.auth;
import com.DigitaleFactuur.models.User;
import io.dropwizard.auth.Authorizer;

public class UserAuthorizer implements Authorizer<User> {

        @Override
        public boolean authorize(User user, String password) {
            return user.getPassword().equals(password);
        }
}

