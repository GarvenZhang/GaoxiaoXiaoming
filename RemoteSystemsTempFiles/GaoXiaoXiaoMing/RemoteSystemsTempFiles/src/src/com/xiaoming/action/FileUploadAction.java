package com.xiaoming.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype") 
public class FileUploadAction extends BaseAction<Object> {

}
