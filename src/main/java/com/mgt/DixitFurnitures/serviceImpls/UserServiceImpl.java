package com.mgt.DixitFurnitures.serviceImpls;

import com.mgt.DixitFurnitures.constants.DixitConst;
import com.mgt.DixitFurnitures.services.UserService;
import com.mgt.DixitFurnitures.utils.FurnitureUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}",requestMap);
        if(validateSignUpMap(requestMap)) {

        }
        else{
            return FurnitureUtils.getResponseEntity(DixitConst.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }
        return null;
        }

    private boolean validateSignUpMap(Map<String, String> requestMap){
       return requestMap.containsKey("name") && requestMap.containsKey("contact") && requestMap.containsKey("password") ? true : false;
    }
}
