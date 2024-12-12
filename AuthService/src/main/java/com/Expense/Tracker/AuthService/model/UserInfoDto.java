package com.Expense.Tracker.AuthService.model;

import com.Expense.Tracker.AuthService.entities.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo {

    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String email;
}
