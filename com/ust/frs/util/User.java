package com.ust.frs.util;

import com.ust.frs.bean.CredentialsBean;
import com.ust.frs.bean.ProfileBean;

public interface User {
	String login(CredentialsBean credentialsBean);   
    boolean logout(String userId);
    String changePassword(CredentialsBean credentialsBean, String newPassword);   
    String register(ProfileBean profileBean);
}
