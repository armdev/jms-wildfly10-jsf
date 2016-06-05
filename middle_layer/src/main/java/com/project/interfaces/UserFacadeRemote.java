package com.project.interfaces;

import com.project.dto.UserDTO;

/**
 *
 * @author Home
 */
public interface UserFacadeRemote {

    void save(UserDTO user);

    int count();
}
