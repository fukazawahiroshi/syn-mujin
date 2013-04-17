package net.bcglex.gproto.controller;

import net.bcglex.gproto.service.EmployeeService;

import org.slim3.controller.Controller;
import org.slim3.controller.MultipartRequestHandler;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;

public class FileUploadController extends Controller {
    @Override
    public Navigation run() throws Exception {
    	MultipartRequestHandler handler=new MultipartRequestHandler(request);
    	handler.handle();
    	FileItem item = requestScope("formFile");
//    	if(item!=null){
        	System.out.println(item.getData().length);
        	EmployeeService.deleteAll();
    		EmployeeService.initData(item.getData());
            return redirect("admin");
//    	} else {
//        	item = requestScope("videoFile");
//    		if(item!=null){
//            	System.out.println(item.getFileName());
//    			item.getData();
//    		}
//    	}
//    	return null;
   }
}
