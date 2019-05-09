package servlet;

import java.io.BufferedReader;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import dao.SubjectDao;
import net.sf.json.JSONObject;

public class UploadQue extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	//客户端是以UTF-8编码提交的，那么服务器端request对象就以UTF-8编码接收(request.setCharacterEncoding(“UTF-8”))
    			request.setCharacterEncoding("UTF-8");
    			response.setCharacterEncoding("UTF-8");
    			//设置请求以及响应的内容类型以及编码方式
    			response.setContentType("text/html;charset=UTF-8");
    	doGet(request, response);
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		int upid = Integer.parseInt((String)request.getParameter("id"));
		List<List<String>> line = new ArrayList<List<String>>();
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();          
            //2、创建一个文件上传解析器
            ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
       
            //3、判断提交上来的数据是否是上传表单的数据
            if(!fileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
           
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    String value1 = new String(name.getBytes("iso8859-1"),"UTF-8");
                    /*System.out.println(name+"  "+value);
                    System.out.println(name+"  "+value1);*/
                }else{
                    //如果fileitem中封装的是上传文件，得到上传的文件名称，
                    String fileName = item.getName();
                  //  System.out.println(fileName);
                    if(fileName==null||fileName.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                    //得到上传文件的扩展名
                    String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1);
                    if("zip".equals(fileExtName)||"rar".equals(fileExtName)||"tar".equals(fileExtName)||"jar".equals(fileExtName)){
                        /*request.setAttribute("message", "上传文件的类型不符合！！！");
                        request.getRequestDispatcher("/message.jsp").forward(request, response);*/
                        return;
                    }
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                   // System.out.println("上传文件的扩展名为:"+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream is = item.getInputStream();
                    
                    InputStreamReader in = new InputStreamReader(is,"gbk");
                    BufferedReader buff = new BufferedReader(in);
                    String lineTxt = null;
                    List<String> line1 = new ArrayList<String>();
                    while((lineTxt = buff.readLine())!=null){                   	
                    	line1.add(lineTxt);
                    }
                    line.add(line1);
                    //关闭输入流
                    is.close();
                    in.close();
                    //关闭输出流
                    
                }
            }
        } 
        catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();            
        }
        SubjectDao subDao = new SubjectDao();
        for(int i=0;i<line.size();i++){
        	//System.out.println(line.get(i).get(2));        	
        	subDao.addSubjectUpload(upid,line.get(i).get(0),line.get(i).get(1),line.get(i).get(2),line.get(i).get(3),line.get(i).get(4));       		    	
        }
        
        JSONObject json = new JSONObject();
        json.put("res",1);
        PrintWriter pw =response.getWriter();
        pw.print(json);
  }

  
}

