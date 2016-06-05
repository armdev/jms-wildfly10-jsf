/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.writer.interfaces;

import com.writer.entities.User;

/**
 *
 * @author Home
 */
public interface UserFacadeLocale {

    int count();

    User find(Object id);
}
