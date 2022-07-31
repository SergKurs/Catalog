
const URLserver = "Http://localhost:8080";
let indexItemPotLeft = 0;

function f_gopage(subpage)
{
    document.getElementById("subpage1").style.display="none";
    document.getElementById("subpage2").style.display="none";
    document.getElementById("subpage3").style.display="none";
    document.getElementById("subpage4").style.display="none";
    document.getElementById("subpage5").style.display="none";
    document.getElementById("subpage6").style.display="none";

    switch (subpage)
    {
        case 1: //подстраница о нас
            document.getElementById("subpage1").style.display="block";
            break;
        case 2: //подстраница вывода элементов каталога
            document.getElementById("subpage2").style.display="block";
            requestCatalog("",0);
            break;
        case 3: //подстраница контактов
            document.getElementById("subpage3").style.display="block";
            break;
        case 4: //подстраница выводв элемента каталога
            document.getElementById("subpage4").style.display="block";
            break;
        case 5: //подстраница формы ввода данных нового элемента каталога
            document.getElementById("subpage5").style.display="block";
            break;
        case 6: //подстраница редактирования элемента каталога
            document.getElementById("subpage6").style.display="block";
            break;
    }
} //end function

function f_subpage_items(sidePage, idItem)
{
    requestCatalog(sidePage, idItem);
} //end function

function requestCatalog(sidePage, idItem){
    //alert("собираюсь отправить запрос элементов каталога");
    let requestURL = URLserver+'/catalog'+'?sidePage='+sidePage+'&itemId='+idItem;
    let request = new XMLHttpRequest();
    request.open('GET', requestURL,true);
    request.onload = viewCatalog; //
    request.responseType = 'json';
    request.send();
} //end function

function viewCatalog() {
    //alert("начинаю показ каталог ");
    let items = this.response;

    //html-opening всех элементов каталога
    let HTMLitems, HTMLitem, HTMLimages, HTMLimage;
    let images;
    let eltr,eltd;
    if (document.getElementById('iditemall')) {
        document.getElementById('iditemall').remove();
    }
    HTMLitems = document.createElement('div');
    HTMLitems.id = "iditemall";
    let d1 = document.getElementById('idTable');
    d1.insertAdjacentElement('beforeend', HTMLitems);

    //HTMLitems
    let elt = document.createElement('table');
    elt.id = "idtableitems";
    document.getElementById("iditemall")
        .insertAdjacentElement('beforeend', elt);

    items.forEach(function (item,iItem) {
        //обрабатываем элемент каталога

        if (iItem%3===0) {
            eltr = document.createElement('tr');
            eltr.id = 'triditem'+(Math.floor(iItem/3));
            document.getElementById("idtableitems")
                .insertAdjacentElement('beforeend',eltr);
        }

        eltd = document.createElement('td');
        eltd.id = "tdiditem"+iItem;
        //HTMLitems
        document.getElementById("triditem"+(Math.floor(iItem/3)))
            .insertAdjacentElement('beforeend', eltd);

        //html-opening элемента каталога
        let itemId = item['itemId'];
        HTMLitem = document.createElement('div');
        HTMLitem.id = "iditem"+itemId;
        //html-opening всех изображений

        HTMLimages = document.createElement('div');
        HTMLimages.id = "idimagesitemindex"+itemId;
        HTMLimages.style.width = "110px";
        HTMLimages.style.height = "110px";
        HTMLimages.style.border = "1px";
        HTMLimages.style.borderColor = "black";

        images = item['listImages'];
        images.forEach(function (image, iImage) {
            //обработка одного изображения
            let imageId = image['imageId'];
            //html-opening одного изображения

            //html-ending одного изображения

            let el = document.createElement('div');
            el.id = "idimageindex"+itemId+"i"+imageId;
            if (iImage === 0) {el.style.display = "block"} else {el.style.display = "none"}
            el.style.width = "100px";
            el.style.height = "100px";
            HTMLimage = el;

            let elim = document.createElement('img');
            elim.id = "idimageId"+imageId;
            elim.src = URLserver+"/image?id="+imageId;
            elim.style.width = "100px";
            elim.style.height = "100px";
            elim.alt = "Not picture";

            HTMLimage.append(elim);
            HTMLimages.append(HTMLimage);
        }); //конец обработки одного изображения
        //html-ending всех изображений
        HTMLitem.prepend(HTMLimages);

        //html-ending элемента каталога

        let elattr = document.createElement('div');
        let elbr = document.createElement('br');
        if (item['attr0']!==null) {elattr.append("АтрибутСтрока0:"+item['attr0'])}
        elattr.append(elbr.cloneNode());
        if (item['attr1']!==null) {elattr.append("АтрибутСтрока1:"+item['attr1'])}
        elattr.append(elbr.cloneNode());
        if (item['attr2']!==null) {elattr.append("АтрибутСтрока2:"+item['attr2'])}
        elattr.append(elbr.cloneNode());
        if (item['attr3']!==null) {elattr.append("АтрибутСтрока3:"+item['attr3'])}
        elattr.append(elbr.cloneNode());
        if (item['attr4']!==null) {elattr.append("АтрибутСтрока4:"+item['attr4'])}
        elattr.append(elbr.cloneNode());
        if (item['attrInt0']!==null) {elattr.append("АтрибутЧисло0:"+item['attrInt0'])}
        elattr.append(elbr.cloneNode());
        if (item['attrInt1']!==null) {elattr.append("АтрибутЧисло1:"+item['attrInt1'])}
        elattr.append(elbr.cloneNode());
        if (item['attrInt2']!==null) {elattr.append("АтрибутЧисло2:"+item['attrInt2'])}
        elattr.append(elbr.cloneNode());
        if (item['attrInt3']!==null) {elattr.append("АтрибутЧисло3:"+item['attrInt3'])}
        elattr.append(elbr.cloneNode());
        if (item['attrInt4']!==null) {elattr.append("АтрибутЧисло4:"+item['attrInt4'])}

        HTMLitem.append(elattr);
        document.getElementById("tdiditem"+iItem).append(HTMLitem);

    });
    //html-ending всех элементов каталога
} //end function viewCatalog

//














function requestDeleteItemCatalog(idItem){
    alert("Ссобираюсь отправить запрос на удаление элемента каталога"+idItem);
    let requestURL = URLserver+'/catalog'+'?itemId='+idItem;
    let request = new XMLHttpRequest();
    request.open('DELETE', requestURL,true);
    request.onload = onLoadDeleteItemCatalog; //{
    request.send();
} //end function requestDeleteItemCatalog

function onLoadDeleteItemCatalog() {
    //пришел ответ на запрос удаления элемента
    alert("Пришел ответ на запрос удаления элемента"+idItem);

} //end function




/*
                    СВАЛКА ПРИМЕРОВ
--------------------------------------------------------------

Логирование объектов
Не используйте console.log(obj);,
Используйте console.log(JSON.parse(JSON.stringify(obj)));.

Так вы можете быть уверены, что видите значение obj в момент, его логирования.
*/



