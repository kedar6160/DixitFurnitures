package com.mgt.DixitFurnitures.serviceImpls;

import com.mgt.DixitFurnitures.constants.DixitConst;
import com.mgt.DixitFurnitures.dao.UserDao;
import com.mgt.DixitFurnitures.entities.Users;
import com.mgt.DixitFurnitures.services.UserService;
import com.mgt.DixitFurnitures.utils.FurnitureUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}",requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                Users users = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(users)) {
                    userDao.save(getUserFromMap(requestMap));
                    return FurnitureUtils.getResponseEntity("Sucessfully Registered!", HttpStatus.OK);
                } else {
                    return FurnitureUtils.getResponseEntity("Email already Exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return FurnitureUtils.getResponseEntity(DixitConst.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return FurnitureUtils.getResponseEntity("Please Retry",HttpStatus.BAD_REQUEST);
        }

    private boolean validateSignUpMap(Map<String, String> requestMap){
       return requestMap.containsKey("name") && requestMap.containsKey("contact") && requestMap.containsKey("password") ? true : false;
    }

    private Users getUserFromMap(Map<String, String> requestMap){
        Users users= new Users();
        users.setName(requestMap.get("name"));
        users.setEmail(requestMap.get("email"));
        users.setContact(requestMap.get("contact"));
        users.setPassword(requestMap.get("password"));
        users.setRole("user");
        users.setStatus("false");

        return users;
    }
}
