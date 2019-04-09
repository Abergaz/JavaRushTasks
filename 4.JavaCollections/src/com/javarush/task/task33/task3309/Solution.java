package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;

/*
Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тегом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:
toXmlWithComment(firstSecondObject, "second", "it's a comment")

Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
<!--it's a comment-->
<second>some string</second>
<!--it's a comment-->
<second>some string</second>
<!--it's a comment-->
<second><![CDATA[need CDATA because of < and >]]></second>
<!--it's a comment-->
<second/>
</first>


Требования:
1. Метод toXmlWithComment должен быть статическим.
2. Метод toXmlWithComment должен быть публичным.
3. Если во входящем xml отсутствует искомый тег, то добавлять комментарии не нужно.
4. Количество комментариев вставленных в xml должно быть равно количеству тегов tagName.
5. Метод toXmlWithComment должен возвращать xml в виде строки преобразованной в соответствии с условием задачи
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, XMLStreamException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        XMLStreamWriter xsw = XMLOutputFactory.newFactory().createXMLStreamWriter(writer);
        MyContetnHandler myContentHandler = new MyContetnHandler(xsw,tagName,comment);
        marshaller.marshal(obj, xsw);


        return null;
    }

    public static void main(String[] args) {

    }
}