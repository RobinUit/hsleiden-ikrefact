package com.DigitaleFactuur.auth;

import com.DigitaleFactuur.db.UserDAO;
import com.DigitaleFactuur.models.User;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class UserAuthenticator implements Authenticator<BasicCredentials, User> {

    private UserDAO userDAO;

    public UserAuthenticator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) {
        return userDAO.findByUsername(
                basicCredentials.getUsername());
        }
    }
