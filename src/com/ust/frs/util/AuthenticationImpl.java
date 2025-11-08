package com.ust.frs.util;

import com.ust.frs.bean.CredentialsBean;
import com.ust.frs.dao.DataStore;

public class AuthenticationImpl implements Authentication {

    @Override
    public boolean authenticate(CredentialsBean credentials) {
        for (CredentialsBean user : DataStore.users) {
            if (user.getUserID().equals(credentials.getUserID())
                    && user.getPassword().equals(credentials.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String authorize(String userType) {
        if (userType.equalsIgnoreCase("A")) return "Administrator";
        if (userType.equalsIgnoreCase("C")) return "Customer";
        return "Invalid";
    }

    @Override
    public boolean changeLoginStatus(CredentialsBean credentials, int status) {
        for (CredentialsBean user : DataStore.users) {
            if (user.getUserID().equals(credentials.getUserID())) {
                user.setLoginStatus(status);
                return true;
            }
        }
        return false;
    }
}
