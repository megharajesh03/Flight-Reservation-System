package com.ust.frs.util;

import com.ust.frs.bean.CredentialsBean;

public interface Authentication {
	boolean authenticate(CredentialsBean credentialsBean);
    String authorize(String userId);
    boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus); 
}
