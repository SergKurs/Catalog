package com.home.webm3;
//import org.apache.commons.fileupload.servlet.ServletFileUpload


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/image")
public class ServletImageView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        String paramImageId = req.getParameter("id");

        if(paramImageId==null||paramImageId.equals("")){
            System.out.println("Параметра индекса изображения нет.");
            return;
            //break;
        }
        DataImage image = ImageDAO.select(Integer.parseInt(paramImageId));
        if (image==null){
            System.out.println("Под таким индексом изображения нет.");
            return;
        }

        resp.setContentType(image.getContentType());
        File file = new File(image.getPath());


        if (!file.exists()){
            System.out.println("Файла изображения нет.");
            return;
        }

        FileInputStream fileInputStream = new FileInputStream(file);
        OutputStream outputStream = resp.getOutputStream();
        byte[] byteBuffer = new byte[1024];
        int len;
        while(true){
            len = fileInputStream.read(byteBuffer);
            if(len==-1){break;}
            outputStream.write(byteBuffer);
        }
        fileInputStream.close();
        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //Package org.apache.tomcat.util.http.fileupload.servlet
        //DiskFileItemFactory factory = new DiskFileItemFactory();
        // Configure the factory here, if desired.
        //ServletFileUpload upload = new ServletFileUpload(factory);
        // Configure the uploader here, if desired.
        //List fileItems = upload.parseRequest(request);
        //конструктор ServletRequestContext​(HttpServletRequest request)

        System.out.println("doPost Image начало");

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ServletRequestContext requestContext;
        requestContext = new org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext(req);
        //                                                          ServletRequestContext​​(req);
        List<FileItem> listFiles;


        String paramItemId = req.getParameter("itemId");
        int itemId=0;
        if ((paramItemId!=null)&&(!paramItemId.equals(""))) {
            try {
                itemId = Integer.parseInt(paramItemId);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                resp.setContentType("application/json; charset=utf-8");
                resp.getWriter().write("{\"result\": \" Индекс элемента направильный или отсутствует. \"} ");
                System.out.println("doPost Image Индекс элемента направильный или отсутствует.");
                return;

            }
        }


        try {
            listFiles=upload.parseRequest(requestContext);
            System.out.println("doPost Image listFiles.size="+listFiles.size());
        } catch (FileUploadException ex) {
            ex.printStackTrace();
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write("{\"result\": \" Не удалось загрузить файл \"} ");
            System.out.println("doPost Image Не удалось загрузить файл");
            return;
        }

        imagesFromFileItemToDB(listFiles,itemId);


    }

    public static void imagesFromFileItemToDB(List<FileItem> listFiles,int itemId) {


        for(FileItem fileItem : listFiles)
            if (!fileItem.isFormField()) { //если файл, а не просто поле формы


                //FileItem fileItem=listFiles.get(listFiles.size()-1);
                DataImage image = new DataImage();
                image.setImageName(fileItem.getName());
                image.setSize((int) fileItem.getSize());
                image.setContentType(fileItem.getContentType());

                image.setMd5(DigestUtils.md5DigestAsHex(fileItem.get()));
                //image.setPath("./images/" + image.getMd5());
                image.setPath("" + image.getMd5());

                // Сохраняем в базе данных
                System.out.println("doPost Image перед сохр в БД image.toString=" + image.toString());
                ImageDAO.insert(image, itemId);

                File file = new File(image.getPath());
                try {
                    fileItem.write(file);
                } catch (Exception e) {
                    e.printStackTrace();
                    //resp.setContentType("application/json; charset=utf-8");
                    //resp.getWriter().write("{\"result\": \" Не удалось записать на диск \"} ");
                    System.out.println(" Не удалось записать на диск");
                    return;
                }
            }
    }

    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        // 1. Сначала получаем imageId в запросе
        String parametrImageId=req.getParameter("imageId");
        if(parametrImageId==null||parametrImageId.equals("")){
            resp.getWriter().write("{\"result\": \" Индекса изображения нету. \"}");
            System.out.println(" Индекса изображения нету.");
            return;
        }
        int imageId=0;
        try {
            imageId = Integer.parseInt(parametrImageId);
        }catch (NumberFormatException ex){
            resp.getWriter().write("{\"result\": \" Формат индекса изображения ошибочный. \"}");
            System.out.println(" Формат индекса изображения ошибочный.");
            return;
        }
        DataImage image=ImageDAO.select(imageId);
        if(image==null){
            resp.getWriter().write("{\"result\": \" С таким индексом изображения нет \"}");
            System.out.println(" С таким индексом изображения нет");
            return;
        }
        ImageDAO.delete(imageId);

        File file = new File(image.getPath());
        if (file.delete()) {
            resp.getWriter().write("{\"result\": \" Удаление прошло успешно \"}");
            System.out.println(" Удаление прошло успешно");
        }
    }




}

