package com.google.tendengce.service;import com.google.tendengce.entity.Suser;import org.springframework.stereotype.Service;public interface UserService {    public Suser selectByPrimaryKey(int id);    public int addUserInfo(Suser suser);}