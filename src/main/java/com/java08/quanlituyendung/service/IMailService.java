package com.java08.quanlituyendung.service;

public interface IMailService {
    boolean sendVerificationMail(String mail, String code);
    boolean sendResetPasswordMail(String mail, String code);

}
