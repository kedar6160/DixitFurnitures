package com.mgt.DixitFurnitures.controllerImpl;

import com.mgt.DixitFurnitures.constants.DixitConst;
import com.mgt.DixitFurnitures.controller.UserController;
import com.mgt.DixitFurnitures.services.UserService;
import com.mgt.DixitFurnitures.utils.FurnitureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap) {
       try {
         return userService.signUp(requestMap);
       }catch (Exception e){
        e.printStackTrace();
       }
//       return new ResponseEntity<String>("{\" Message \":\" Something went wrong\"}",HttpStatus.INTERNAL_SERVER_ERROR);
    return FurnitureUtils.getResponseEntity(DixitConst.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
