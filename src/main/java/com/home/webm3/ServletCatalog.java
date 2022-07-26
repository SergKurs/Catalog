package com.home.webm3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/catalog")
//@WebServlet("/")
public class ServletCatalog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //String par1 = req.getParameter("par1");
        //resp.setContentType("text/html");
        //PrintWriter printWriter = resp.getWriter();
        //printWriter.write("from catalog");
        //printWriter.close();
        //new testJSON().netod1();

        String paramSidePage = req.getParameter("sidePage");
        String paramItemId = req.getParameter("itemId");
        String paramItemCount = req.getParameter("itemCount");
        int iparamItemId=0;
        if ((paramItemId!=null)&&(!paramItemId.equals(""))) {
            try {
                iparamItemId = Integer.parseInt(paramItemId);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }


            resp.setContentType("application/json; charset=utf-8");

            List<DataCatalogItem> listItem = ItemDAO.selectAll();
            if (listItem==null) {
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
            int countIdList=9;
            int fromIdList=0;

            if ((paramItemCount!=null)&&(!paramItemCount.equals(""))) {
                try {
                    countIdList=Integer.parseInt(paramItemCount);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }

            if (paramSidePage!=null) {
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

        DataCatalogItem item = new DataCatalogItem();
        if ((req.getParameter("attr0")!=null)&&(!req.getParameter("attr0").equals(""))) try {
            item.setAttr0(req.getParameter("attr0"));
        } catch (Exception ignored) {
        }
        if ((req.getParameter("attr1")!=null)&&(!req.getParameter("attr1").equals(""))) try {
            item.setAttr1(req.getParameter("attr1"));
        } catch (Exception ignored) {
        }
        if ((req.getParameter("attr2")!=null)&&(!req.getParameter("attr2").equals(""))) try {
            item.setAttr2(req.getParameter("attr2"));
        } catch (Exception ignored) {
        }
        if ((req.getParameter("attr3")!=null)&&(!req.getParameter("attr3").equals(""))) try {
            item.setAttr3(req.getParameter("attr3"));
        } catch (Exception ex) {
        }
        if ((req.getParameter("attr4")!=null)&&(!req.getParameter("attr4").equals(""))) try {
            item.setAttr4(req.getParameter("attr4"));
        } catch (Exception ignored) {
        }

        if ((req.getParameter("attrInt0")!=null)&&(!req.getParameter("attrInt0").equals(""))) try {
            item.setAttrInt0(Integer.parseInt(req.getParameter("attrInt0")));
        } catch (Exception ignored) {
        }
        if ((req.getParameter("attrInt1")!=null)&&(!req.getParameter("attrInt1").equals(""))) try {
            item.setAttrInt1(Integer.parseInt(req.getParameter("attrInt1")));
        } catch (Exception ignored) {
        }
        if ((req.getParameter("attrInt2")!=null)&&(!req.getParameter("attrInt2").equals(""))) try {
            item.setAttrInt2(Integer.parseInt(req.getParameter("attrInt2")));
        } catch (Exception ignored) {
        }
        if ((req.getParameter("attrInt3")!=null)&&(!req.getParameter("attrInt3").equals(""))) try {
            item.setAttrInt3(Integer.parseInt(req.getParameter("attrInt3")));
        } catch (Exception ignored) {
        }
        if ((req.getParameter("attrInt4")!=null)&&(!req.getParameter("attrInt4").equals(""))) try {
            item.setAttrInt4(Integer.parseInt(req.getParameter("attrInt4")));
        } catch (Exception ignored) {
        }
        System.out.println("doPost Item item.toString="+item.toString());

        System.out.println("doPost Item перед insert item");
        ItemDAO.insert(item);
        System.out.println("doPost Item после insert item");
        int itemId = DBUtilityForDAO.getMaxItemIdInItemTable();
        System.out.println("doPost Item getMaxItemIdInItemTable="+itemId);
        System.out.println("doPost Item сейчас будет переадресация на /image");
        getServletContext().getRequestDispatcher("/image?itemId="+itemId).forward(req, resp);
    }
}

