package com.home.webm3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.min;

//@CrossOrigin
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

        String paramPage = req.getParameter("page");
        String paramItemId = req.getParameter("itemId");
        String paramItemCount = req.getParameter("itemCount");
        int iparamItemId=0;
        if ((paramItemId!=null)&&(!paramItemId.equals(""))) {
            try {
                iparamItemId = Integer.parseInt(paramItemId);
            } catch (NumberFormatException nfe){}
        }
        {

            resp.setContentType("application/json; charset=utf-8");
            //Создаем объект ImageDao и находим базу данных
            //ImageDao imageDao=new ImageDao();
            //List<> attrs=new ArrayList<>();//imageDao.selectAll();
            List<CatalogItem> attrs = new ArrayList<>();//imageDao.selectAll();
            //Демо режим
            attrs.add(new CatalogItem("ПервоеЗачениеКн1", "ВтороеЗачениеКн1", "ТретьеЗначКн1", "ЧетвЗначКн1", "ПяЗначКн1"));
            attrs.add(new CatalogItem("ПервоеЗачениеКн2", "ВтороеЗачениеКн2", "ТретьеЗачениеКн2", "ЧетвЗначКн2", "ПяЗначКн2"));
            attrs.add(new CatalogItem("ПервоеЗачениеКн3", "ВтороеЗачениеКн3", "ТретьеЗачениеКн3", "ЧетвЗначКн3", "ПяЗначКн3"));
            attrs.add(new CatalogItem("ПервоеЗачениеКн4", "ВтороеЗачениеКн4", "ТретьеЗачениеКн4", "ЧетвЗначКн4", "ПяЗначКн4"));
            attrs.add(new CatalogItem("ПервоеЗачениеКн5", "ВтороеЗачениеКн5", "ТретьеЗачениеКн5", "ЧетвЗначКн5", "ПяЗначКн5"));

            //по умолчаниею
            int countIdList=9;
            int fromIdList=0;

            if ((paramItemCount!=null)&&(!paramItemCount.equals(""))) {
                try {
                    countIdList=Integer.parseInt(paramItemCount);
                } catch (NumberFormatException nfe){}
            };

            if (paramPage!=null) {
                switch (paramPage) {
                    case "before":
                        if ((paramItemId != null) && (!paramItemId.equals(""))) {
                            if (attrs.size() < countIdList) {
                                countIdList = attrs.size(); //также уже fromIdList=0
                            } else {
                                fromIdList = iparamItemId - countIdList;
                            }
                        }
                        break;
                    case "after":
                        if ((paramItemId != null) && (!paramItemId.equals(""))) {
                            if (attrs.size() < countIdList) {
                                countIdList = attrs.size(); //также уже fromIdList=0
                            } else {
                                fromIdList = iparamItemId + countIdList;
                            }
                        }
                }
            }
            // Избавляемся от элементов каталога, которые не влезают на текущую web-страницу
            // сперва удаляем хвост, затем начало
            attrs.subList(fromIdList+countIdList,attrs.size()-1).clear();
            attrs.subList(0,fromIdList-1).clear();

            // Преобразуем найденный результат в строку формата JSON и передаем его объекту resp
            Gson gson = new GsonBuilder().create();
            // jsonStr - строка в формате json
            String jsonStr = gson.toJson(attrs);

            if (jsonStr.isEmpty()) {
                jsonStr = "{\"attr1\":\"attr111111 пусто\"}";
            }
            // Записываем ответ
            // Строку формата JSON передаем объекту resp
            resp.getWriter().write(jsonStr);

        }

    }
}

