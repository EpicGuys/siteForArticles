package com.epicGuys.app.articles.entity;

//this enum has roles for spring security
public enum Role {
    ROLE_USER,  //only can read(see) information and leave comments
    ROLE_WRITER,  //can create posts + user capabilities
    ROLE_ADMIN;  //have all rights(delete, create) users and writers
}
