package com.home.webm3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.util.DigestUtils;
//import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/catalog")
//@WebServlet("/")
public class ServletCatalog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String par1 = req.getParameter("par1");
        //resp.setContentType("text/html");
        //PrintWriter printWriter = resp.getWriter();
        //printWriter.write("from catalog");
        //printWriter.close();
        //new testJSON().netod1();

        String paramSidePage = req.getParameter("sidePage");
        String paramItemId = req.getParameter("itemId");
        String paramItemCount = req.getParameter("itemCount");
        int iparamItemId = 0;
        if ((paramItemId != null) && (!paramItemId.equals(""))) {
            try {
                iparamItemId = Integer.parseInt(paramItemId);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }


        resp.setContentType("application/json; charset=utf-8");

        List<DataCatalogItem> listItem = ItemDAO.selectAll();
        if (listItem == null) {
            resp.getWriter().write("[]");
            return;
        }
        //Демо режим
        //attrs = new ArrayList<>();
        //attrs.add(new DataCatalogItem("ПервоеЗачениеКн2", "ВтороеЗачениеКн2", "ТретьеЗачениеКн2", "ЧетвЗначКн2", "ПяЗначКн2",0,1,2,3,4));
        //attrs.add(new DataCatalogItem("ПервоеЗачениеКн2", "ВтороеЗачениеКн2", "ТретьеЗачениеКн2", "ЧетвЗначКн2", "ПяЗначКн2",0,1,2,3,4));
        //attrs.add(new DataCatalogItem("ПервоеЗачениеКн3", "ВтороеЗачениеКн3", "ТретьеЗачениеКн3", "ЧетвЗначКн3", "ПяЗначКн3",0,1,2,3,4));
        //attrs.add(new DataCatalogItem("ПервоеЗачениеКн4", "ВтороеЗачениеКн4", "ТретьеЗачениеКн4", "ЧетвЗначКн4", "ПяЗначКн4",0,1,2,3,4));
        //attrs.add(new DataCatalogItem("ПервоеЗачениеКн5", "ВтороеЗачениеКн5", "ТретьеЗачениеКн5", "ЧетвЗначКн5", "ПяЗначКн5",0,1,2,3,4));

        //по умолчаниею
        int countIdList = 9;
        int fromIdList = 0;

        if ((paramItemCount != null) && (!paramItemCount.equals(""))) {
            try {
                countIdList = Integer.parseInt(paramItemCount);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }

        if (paramSidePage != null) {
            switch (paramSidePage) {
                case "before":
                    if ((paramItemId != null) && (!paramItemId.equals(""))) {
                        if (listItem.size() < countIdList) {
                            countIdList = listItem.size(); //также уже fromIdList=0
                        } else {
                            fromIdList = iparamItemId - countIdList;
                        }
                    }
                    break;
                case "after":
                    if ((paramItemId != null) && (!paramItemId.equals(""))) {
                        if (listItem.size() < countIdList) {
                            countIdList = listItem.size(); //также уже fromIdList=0
                        } else {
                            fromIdList = iparamItemId + countIdList;
                        }
                    }
            }
        }

        //System.out.println("listItem.toString():"+listItem.toString());
        //System.out.println("listItem.size():"+listItem.size());

        // Избавляемся от элементов каталога, которые не влезают на текущую web-страницу
        // сперва удаляем хвост, затем начало
        //listItem.subList(fromIdList+countIdList,listItem.size()-1).clear();
        //listItem.subList(0,fromIdList-1).clear();

        // Преобразуем найденный результат в строку формата JSON
        Gson gson = new GsonBuilder().create();
        // jsonStr - строка в формате json
        String jsonStr = gson.toJson(listItem);
        //System.out.println("строка в формате json"+jsonStr);

        if (jsonStr.isEmpty()) {
            jsonStr = "[{\"attr1\":\"attr111111 пусто\"}]";
        }
        // Записываем ответ
        // Строку формата JSON передаем объекту resp
        resp.getWriter().write(jsonStr);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String par1 = req.getParameter("par1");
        //resp.setContentType("text/html");
        //PrintWriter printWriter = resp.getWriter();
        //printWriter.write("from catalog");
        //printWriter.close();
        //new testJSON().netod1();

        //String paramSidePage = req.getParameter("sidePage");
        //String paramItemId = req.getParameter("itemId");
        //String paramItemCount = req.getParameter("itemCount");
        //int iparamItemId = 0;
        //if ((paramItemId != null) && (!paramItemId.equals(""))) {
        //    try {
        //        iparamItemId = Integer.parseInt(paramItemId);
        //    } catch (NumberFormatException nfe) {
        //        nfe.printStackTrace();
        //    }
        //}
        //


        System.out.println("начало doPost Item");

        System.out.println("doPost Image начало");

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ServletRequestContext requestContext;
        requestContext = new ServletRequestContext(req);
        //                                                          ServletRequestContext​​(req);
        List<FileItem> listFiles;


        try {
            listFiles = upload.parseRequest(requestContext);
            System.out.println("doPost Item listFiles.size=" + listFiles.size());
        } catch (FileUploadException ex) {
            ex.printStackTrace();
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write("{\"result\": \" Не удалось загрузить файл \"} ");
            System.out.println("doPost Item Не удалось загрузить файл");
            return;
        }
        Map<String, String> map = new HashMap<>();

        for (FileItem fileItem : listFiles)
            //если просто поле формы, а не файл
            if (fileItem.isFormField()) map.put(fileItem.getFieldName(),
                    fileItem.getString(String.valueOf(StandardCharsets.UTF_8)));
        DataCatalogItem item = new DataCatalogItem();
        if ((map.get("attr0") != null) && (!map.get("attr0").equals(""))) try {
            item.setAttr0(map.get("attr0"));
        } catch (Exception ignored) {
        }


        if ((map.get("attr1") != null) && (!map.get("attr1").equals(""))) try {
            item.setAttr1(map.get("attr1"));
        } catch (Exception ignored) {
        }
        if ((map.get("attr2") != null) && (!map.get("attr2").equals(""))) try {
            item.setAttr2(map.get("attr2"));
        } catch (Exception ignored) {
        }
        if ((map.get("attr3") != null) && (!map.get("attr3").equals(""))) try {
            item.setAttr3(map.get("attr3"));
        } catch (Exception ignored) {
        }
        if ((map.get("attr4") != null) && (!map.get("attr4").equals(""))) try {
            item.setAttr4(map.get("attr4"));
        } catch (Exception ignored) {
        }

        if ((map.get("attrInt0") != null) && (!map.get("attrInt0").equals(""))) try {
            //System.out.println("doPost Item attrInt0:"+map.get("attrInt0")+"=>"+Integer.parseInt(map.get("attrInt0")));
            item.setAttrInt0(Integer.parseInt(map.get("attrInt0")));
        } catch (Exception ignored) {
        }
        if ((map.get("attrInt1") != null) && (!map.get("attrInt1").equals(""))) try {
            item.setAttrInt1(Integer.parseInt(map.get("attrInt1")));
        } catch (Exception ignored) {
        }
        if ((map.get("attrInt2") != null) && (!map.get("attrInt2").equals(""))) try {
            item.setAttrInt2(Integer.parseInt(map.get("attrInt2")));
        } catch (Exception ignored) {
        }
        if ((map.get("attrInt3") != null) && (!map.get("attrInt3").equals(""))) try {
            item.setAttrInt3(Integer.parseInt(map.get("attrInt3")));
        } catch (Exception ignored) {
        }
        if ((map.get("attrInt4") != null) && (!map.get("attrInt4").equals(""))) try {
            item.setAttrInt4(Integer.parseInt(map.get("attrInt4")));
        } catch (Exception ignored) {
        }

        //System.out.println("doPost Item До повтора listFiles.size=" + listFiles.size());
        //listFiles = upload.parseRequest(requestContext);
        //System.out.println("doPost Item Повтор listFiles.size=" + listFiles.size());


        System.out.println("doPost Item item.toString=" + item.toString());

        System.out.println("doPost Item перед insert item");
        ItemDAO.insert(item);
        System.out.println("doPost Item после insert item");
        int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        System.out.println("doPost Item getMaxItemIdInItemTable=" + itemId);
        ServletImageView.imagesFromFileItemToDB(listFiles,itemId);
        //System.out.println("doPost Item сейчас будет переадресация на /image");
        //getServletContext().getRequestDispatcher("/image?itemId=" + itemId).forward(req2, resp);
        System.out.println("doPost Image сейчас будет переадресация на index.html");
        resp.sendRedirect("index.html");

    }
}

