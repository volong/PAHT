package com.paht.model.Interface.impl;

import com.paht.model.Interface.IStaticalDep;

public class StaticalDep implements IStaticalDep {
   private Long id;

   private String fullname;

   private String username;

   private String email;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getFullname() {
        return fullname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
